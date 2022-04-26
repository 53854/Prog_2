package org.hawhamburg.ioserver;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class FileController {

    private final FileService fileService;
    private final Path resourcesDirectory = Paths.get("src", "main", "resources");
    public FileController(final FileService fileService) {
        this.fileService = fileService;
    }


    @GetMapping("files/")
    public ResponseEntity<Set<String>> getFileNames() {
        // TODO: Implement me
        Set<String> content = Stream.of(resourcesDirectory.toFile().listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<Set<String>>(content, headers, HttpStatus.OK);
    }

    @GetMapping("files/{fileName}")
    public ResponseEntity<String> readFile(@PathVariable final String fileName) {
        try {
            final var content = fileService.readFile(fileName);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body(content);

        } catch (final NoSuchFileException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (final IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("files/{fileName}")
    public ResponseEntity<String> putFile(@PathVariable final String fileName, @RequestBody final String text) {
        try {
            fileService.writeFile(fileName, text);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (final IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("files/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable final String fileName){
        try {
            Files.delete(resourcesDirectory.resolve(fileName));
            return ResponseEntity.ok().build();
        } catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
