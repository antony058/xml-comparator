package ru.priamosudov.xmlcomparator.ziparchive.task;

import ru.priamosudov.xmlcomparator.file.AbstractFile;
import ru.priamosudov.xmlcomparator.xml.difference.service.XmlDifferenceService;
import ru.priamosudov.xmlcomparator.xml.comparator.XmlComparator;
import ru.priamosudov.xmlcomparator.ziparchive.ZipArchive;

import java.io.File;

public class ZipFileTask implements Runnable {
    private File file;
    private XmlDifferenceService xmlDifferenceService;

    public ZipFileTask(File file, XmlDifferenceService xmlDifferenceService) {
        this.file = file;
        this.xmlDifferenceService = xmlDifferenceService;
    }

    @Override
    public void run() {
        ZipArchive zipArchive = new ZipArchive(file);
        zipArchive.findAndReadXmlFiles();
        AbstractFile[] files = zipArchive.getXmlFiles();

        XmlComparator xmlComparator = new XmlComparator(files[0], files[1]);
        xmlComparator.compareXml();

        xmlDifferenceService.saveXmlDifference(xmlComparator.getDifferences(), file.getName());
    }
}
