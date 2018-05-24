package ru.priamosudov.xmlcomparator.xml.xmlfile;

import ru.priamosudov.xmlcomparator.exception.ValidationException;

import java.io.InputStream;
import java.util.Scanner;

public class XmlFile {
    private static final String XML_FILE_INPUT_STREAM_NOT_FOUND = "Поток чтения xml-файла отсутствует";
    private InputStream inputStream;
    private String xmlFileContent;
    private boolean isXmlRead = false;

    public XmlFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void read() {
        if (inputStream == null) {
            throw new ValidationException(XML_FILE_INPUT_STREAM_NOT_FOUND);
        }
        Scanner scanner = new Scanner(inputStream).useDelimiter("//A");
        xmlFileContent = scanner.hasNext() ? scanner.next() : "";
        isXmlRead = true;
    }

    public String getXmlFileContent() {
        return xmlFileContent;
    }

    public boolean isXmlRead() {
        return isXmlRead;
    }
}
