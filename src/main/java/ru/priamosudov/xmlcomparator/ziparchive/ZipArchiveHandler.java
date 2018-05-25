package ru.priamosudov.xmlcomparator.ziparchive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.priamosudov.xmlcomparator.xml.xmldifference.dao.XmlDifferenceDao;
import ru.priamosudov.xmlcomparator.xml.xmldifference.service.XmlDifferenceService;
import ru.priamosudov.xmlcomparator.ziparchive.task.ZipFileTask;

import java.io.File;
import java.util.concurrent.ExecutorService;

@Component
public class ZipArchiveHandler {
    private ExecutorService zipServiceThreadPool;
    private XmlDifferenceService xmlDifferenceService;

    @Autowired
    @Qualifier("zipArchiveServiceThreadPool")
    public void setZipServiceThreadPool(ExecutorService executorService) {
        this.zipServiceThreadPool = executorService;
    }

    @Autowired
    public void setXmlDifferenceDao(XmlDifferenceService xmlDifferenceService) {
        this.xmlDifferenceService = xmlDifferenceService;
    }

    public void addZipFile(File file) {
        ZipFileTask zipFileTask = new ZipFileTask(file, xmlDifferenceService);
        zipServiceThreadPool.execute(zipFileTask);
    }
}
