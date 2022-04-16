package test.composite.filesystem;

import main.composite.filesystem.Directory;
import main.composite.filesystem.File;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTests {

    @Test
    void fetchSizeFile() {
        // Arrange
        final var fileSize = 10L;
        final var fileName = "myFile.txt";
        final var myFile = new File(fileName, fileSize);

        // Act
        final var actualSize = myFile.fetchSize();

        // Assert
        assertEquals(fileSize, actualSize);
    }

    @Test
    void fetchSizeEmptyDir() {
        // Arrange
        final var directory = new Directory("myEmptyDir");

        // Act
        final var actualSize =directory.fetchSize();

        // Assert
        assertEquals(0L, actualSize);
    }

    @Test
    void fetchSizeDirectoryOneLevel() {
        // Arrange
        final var file1 = new File("file1", 5L);
        final var file2 = new File("file2", 10L);
        final var file3 = new File("file3", 20L);
        final var directory = new Directory("myDir");
        directory.add(file1);
        directory.add(file2);
        directory.add(file3);

        // Act
        final var actualSize = directory.fetchSize();

        // Assert
        assertEquals(5L+10L+20L, actualSize);
    }

    @Test
    void fetchSizeDirectoryTwoLevels() {
        // Arrange
        final var file1 = new File("file1", 5L);
        final var file2 = new File("file2", 10L);
        final var file3 = new File("file3", 20L);
        final var directory1 = new Directory("dir1");
        final var directory2 = new Directory("dir2");
        directory1.add(file1);
        directory1.add(directory2);
        directory2.add(file2);
        directory2.add(file3);

        // Act
        final var actualSize = directory1.fetchSize();

        // Assert
        assertEquals(5L+10L+20L, actualSize);
    }

    @Test
    void fetchSizeMultiLevelFileSystem() {
        // Arrange
        final var file1 = new File("file1", 1L);
        final var file2 = new File("file2", 2L);
        final var file3 = new File("file3", 3L);
        final var file4 = new File("file4", 4L);
        final var file5 = new File("file5", 5L);

        final var directory1 = new Directory("dir1");
        final var directory2 = new Directory("dir2");
        final var directory3 = new Directory("dir3");
        final var directory4 = new Directory("dir4");
        final var directory5 = new Directory("dir5");

        directory1.add(file1);
        directory1.add(directory2);
        directory1.add(file2);
        directory2.add(directory3);
        directory3.add(file2);
        directory3.add(file3);
        directory1.add(directory4);
        directory1.add(directory5);
        directory5.add(file4);
        directory5.add(file5);

        // Act
        final var actualSize = directory1.fetchSize();

        // Assert
        assertEquals(1L+2L*2+3L+4L+5L, actualSize);
    }

    @Test
    void addFileAndDirectoryWithSameName() {
        // Arrange
        final var directory1 = new Directory("dir1");
        final var file1 = new File("sameName", 5L);
        final var directory2 = new Directory("sameName");

        // Act
        directory1.add(file1);
        final var actualResult= directory1.add(directory2);

        // Assert
        assertTrue(actualResult);
    }

    @Test
    void addTwoFilesWithSameName() {
        // Arrange
        final var directory1 = new Directory("dir1");
        final var file1 = new File("sameName", 5L);
        final var file2 = new File("sameName", 10L);

        // Act
        directory1.add(file1);
        final var actualResult= directory1.add(file2);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void addTwoDirectoriesWithSameName() {
        // Arrange
        final var directory1 = new Directory("dir1");
        final var directory2 = new Directory("sameName");
        final var directory3 = new Directory("sameName");

        // Act
        directory1.add(directory2);
        final var actualResult= directory1.add(directory3);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void directCycle() {
        // Arrange
        final var directory1 = new Directory("dir1");
        final var directory2 = new Directory("dir2");

        // Act
        directory1.add(directory2);
        final var actualResult= directory2.add(directory1);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void multiLevelCycle() {
        // Arrange
        final var directory1 = new Directory("dir1");
        final var directory2 = new Directory("dir2");
        final var directory3 = new Directory("dir3");

        // Act
        directory1.add(directory2);
        directory2.add(directory3);
        final var actualResult= directory3.add(directory1);

        // Assert
        assertFalse(actualResult);
    }
}
