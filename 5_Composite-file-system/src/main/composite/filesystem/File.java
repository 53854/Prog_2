package main.composite.filesystem;

import java.util.Objects;

public class File extends FileSystemElement {

    private final Long size;

    public File(String name, Long size) {
        super(name);
        this.size = size;
    }

    @Override
    public Long fetchSize() {
        return this.size;
    }

    @Override
    public boolean contains(FileSystemElement fileSystemElement) {
        return this.equals(fileSystemElement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        return Objects.equals(name, file.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
