package main.composite.filesystem;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Directory extends FileSystemElement {

    private final Set<FileSystemElement> contents;

    public Directory(final String name) {
        super(name);
        this.contents = new HashSet<>();
    }

    @Override
    public Long fetchSize() {
        return this.contents.stream().mapToLong(FileSystemElement::fetchSize).sum();
    }

    @Override
    public boolean contains(final FileSystemElement fileSystemElement) {
        if (this.equals(fileSystemElement)) return true;
        return this.contents.stream().anyMatch(c -> c.contains(fileSystemElement));
    }

    public boolean add(final FileSystemElement fileSystemElement) {
        if(fileSystemElement.contains(this)) return false;
        return this.contents.add(fileSystemElement);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Directory directory = (Directory) o;

        return Objects.equals(name, directory.name) && Objects.equals(contents, directory.contents);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        return result;
    }
}
