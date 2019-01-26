package ru.temnenkov.nandasm;

public final class FileParts {
    private final String name;
    private final String extension;

    private FileParts(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public static FileParts parse(String fileName) {
        String extension = "";
        String name;

        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i + 1);
            name = fileName.substring(0, i);
        } else {
            name = fileName;
        }
        return new FileParts(name, extension);
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "FileParts{" +
                "name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
