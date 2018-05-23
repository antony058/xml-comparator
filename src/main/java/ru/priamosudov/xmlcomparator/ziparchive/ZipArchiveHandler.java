package ru.priamosudov.xmlcomparator.ziparchive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.priamosudov.xmlcomparator.ziparchive.task.ZipFileTask;

import java.io.File;
import java.util.concurrent.ExecutorService;

@Component
public class ZipArchiveHandler {
    private ExecutorService zipServiceThreadPool;

    @Autowired
    @Qualifier("zipArchiveServiceThreadPool")
    public void setZipServiceThreadPool(ExecutorService executorService) {
        this.zipServiceThreadPool = executorService;
    }

    public void addZipFile(File file) {
        ZipFileTask zipFileTask = new ZipFileTask(file);
        zipServiceThreadPool.execute(zipFileTask);
    }
}
