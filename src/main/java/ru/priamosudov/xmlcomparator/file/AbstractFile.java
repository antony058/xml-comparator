package ru.priamosudov.xmlcomparator.file;

import java.io.InputStream;

public abstract class AbstractFile {
    protected InputStream inputStream;
    protected String fileContent;
    protected boolean isFileRead;

    public AbstractFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileContent() {
        return fileContent;
    }

    public boolean isFileRead() {
        return isFileRead;
    }

    public abstract void read();
}
