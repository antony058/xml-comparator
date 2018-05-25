package ru.priamosudov.xmlcomparator.ziparchive.task;

import ru.priamosudov.xmlcomparator.xml.xmldifference.service.XmlDifferenceService;
import ru.priamosudov.xmlcomparator.xml.xmlfile.XmlComparator;
import ru.priamosudov.xmlcomparator.xml.xmlfile.XmlFile;
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
        XmlFile[] xmlFiles = zipArchive.getXmlFiles();

        XmlComparator xmlComparator = new XmlComparator(xmlFiles[0], xmlFiles[1]);
        xmlComparator.compareXml();

        xmlDifferenceService.saveXmlDifference(xmlComparator.getDifferences(), file.getName());
    }
}
