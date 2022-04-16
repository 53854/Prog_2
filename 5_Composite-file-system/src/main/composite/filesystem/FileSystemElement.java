package main.composite.filesystem;

public abstract class FileSystemElement {

    protected final String name;

    protected FileSystemElement(String name) {
        this.name = name;
    }

    public abstract Long fetchSize();

    public abstract boolean contains(FileSystemElement fileSystemElement);

}
