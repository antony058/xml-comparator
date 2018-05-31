package ru.priamosudov.xmlcomparator.directory.validation;

import ru.priamosudov.xmlcomparator.exception.ValidationException;
import ru.priamosudov.xmlcomparator.file.AbstractFile;

import java.io.File;

public class DirectoryValidator {
    private static final String DIRECTORY_NOT_FOUND = "Каталог не найден";
    private static final String ZIP_FILES_NOT_FOUND = "ZIP-файлы не найдены";
    private static final String FILES_WRONG_AMOUNT = "В архиве должно быть 2 файла для сравнения";

    public static void checkFilesValid(File... files) {
        if (files == null) {
            throw new ValidationException(DIRECTORY_NOT_FOUND);
        } else if (files.length == 0) {
            throw new ValidationException(ZIP_FILES_NOT_FOUND);
        }
    }

    public static void checkXmlFilesAmount(AbstractFile... files) {
        if (files == null || files.length != 2) {
            throw new ValidationException(FILES_WRONG_AMOUNT);
        }
    }
}
