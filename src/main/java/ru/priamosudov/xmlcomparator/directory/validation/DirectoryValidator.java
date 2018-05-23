package ru.priamosudov.xmlcomparator.directory.validation;

import ru.priamosudov.xmlcomparator.exception.ValidationException;

import java.io.File;

public class DirectoryValidator {
    private static final String DIRECTORY_NOT_FOUND = "Каталог не найден";
    private static final String ZIP_FILES_NOT_FOUND = "ZIP-файлы не найдены";

    public static void checkFilesValid(File... files) {
        if (files == null) {
            throw new ValidationException(DIRECTORY_NOT_FOUND);
        } else if (files.length == 0) {
            throw new ValidationException(ZIP_FILES_NOT_FOUND);
        }
    }
}
