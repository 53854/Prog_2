package org.hawhamburg.ioserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IOServerApplicationTests {

	private static final Path resourcesDirectory = Paths.get("src", "main", "resources");

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getFiles() {
		// Arrange
		final var fileName1 = "test.txt";
		final var fileContent1 = "this is my test string1.";
		final var fileName2 = "test2.txt";
		final var fileContent2 = "this is my test string2.";
		createFile(fileName1, fileContent1);
		createFile(fileName2, fileContent2);

		final var url = "http://localhost:" + port + "/files/";

		// Act
		try {
			final var response = restTemplate.getForEntity(url, Set.class);

		// Assert
			assertEquals(HttpStatus.OK, response.getStatusCode());
			assertEquals(Set.of(fileName1, fileName2, ".gitignore"), response.getBody());
		} finally {
		// Clean up
			deleteFile(fileName1);
			deleteFile(fileName2);
		}
	}

	@Test
	public void getFile() {
		// Arrange
		final var fileName = "test.txt";
		final var fileContent = "this is my test string.";
		createFile(fileName, fileContent);

		final var url = "http://localhost:" + port + "/files/" + fileName;

		// Act
		final var response = restTemplate.getForEntity(url, String.class);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(fileContent, response.getBody());

		// Clean up
		deleteFile(fileName);
	}

	@Test
	public void getFileNoSuchFile() {
		// Arrange
		final var fileName = "iDoNotExist.txt";
		final var url = "http://localhost:" + port + "/files/" + fileName;

		// Act
		final var response = restTemplate.getForEntity(url, String.class);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertFalse(response.hasBody());
	}

	@Test
	public void putFile() {
		// Arrange
		final var fileName = "test.txt";
		final var fileContent = "this is my test string.";

		final var url = "http://localhost:" + port + "/files/" + fileName;
		final var httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_PLAIN);
		final var entity = new HttpEntity<>(fileContent, httpHeaders);

		// Act
		final var response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(Files.exists(resourcesDirectory.resolve(fileName)));
		assertEquals(fileContent, readFile(fileName));

		// Clean up
		deleteFile(fileName);
	}

	@Test
	public void putFileInternalServerError() {
		// Arrange
		final var invalidFileName = "invalid<filename";
		final var fileContent = "content of invalid file";

		final var url = "http://localhost:" + port + "/files/" + invalidFileName;
		final var httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_PLAIN);
		final var entity = new HttpEntity<>(fileContent, httpHeaders);

		// Act
		final var response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	private void createFile(final String fileName, final String fileContent) {
		try {
			Files.writeString(resourcesDirectory.resolve(fileName), fileContent);
		} catch (final IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	private String readFile(final String fileName) {
		try {
			return Files.readString(resourcesDirectory.resolve(fileName));
		} catch (final IOException e) {
			e.printStackTrace();
			fail();
			return e.toString();
		}
	}

	private void deleteFile(final String fileName) {
		try {
			Files.delete(resourcesDirectory.resolve(fileName));
		} catch (final IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
