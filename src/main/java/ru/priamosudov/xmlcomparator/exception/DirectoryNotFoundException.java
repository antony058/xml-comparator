package ru.priamosudov.xmlcomparator.exception;

public class DirectoryNotFoundException extends RuntimeException {
    private static final String DIRECTORY_NOT_FOUND = "Каталог не найден";

    public DirectoryNotFoundException() {
        super(DIRECTORY_NOT_FOUND);
    }

    public DirectoryNotFoundException(String message) {
        super(message);
    }
}
