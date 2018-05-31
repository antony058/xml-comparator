package ru.priamosudov.xmlcomparator.file.xml;

import ru.priamosudov.xmlcomparator.exception.ValidationException;
import ru.priamosudov.xmlcomparator.file.AbstractFile;

import java.io.InputStream;
import java.util.Scanner;

public class XmlFile extends AbstractFile {
    private static final String XML_FILE_INPUT_STREAM_NOT_FOUND = "Поток чтения ru.priamosudov.xmlcomparator.xml-файла отсутствует";

    public XmlFile(InputStream inputStream) {
        super(inputStream);
    }

    public void read() {
        if (inputStream == null) {
            throw new ValidationException(XML_FILE_INPUT_STREAM_NOT_FOUND);
        }
        Scanner scanner = new Scanner(inputStream).useDelimiter("//A");
        fileContent = scanner.hasNext() ? scanner.next() : "";
        isFileRead = true;
    }
}
