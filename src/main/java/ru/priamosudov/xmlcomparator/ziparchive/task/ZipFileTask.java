package ru.priamosudov.xmlcomparator.ziparchive.task;

import java.io.File;

public class ZipFileTask implements Runnable {
    private File file;

    public ZipFileTask(File file) {
        this.file = file;
    }

    @Override
    public void run() {

    }
}
