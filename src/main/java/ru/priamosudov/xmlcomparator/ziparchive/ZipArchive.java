package ru.priamosudov.xmlcomparator.ziparchive;

import ru.priamosudov.xmlcomparator.directory.validation.DirectoryValidator;
import ru.priamosudov.xmlcomparator.xml.xmlfile.XmlFile;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipArchive {
    private final File file;
    private XmlFile[] xmlFiles = new XmlFile[2];

    public ZipArchive(File file) {
        this.file = file;
    }

    public void findAndReadXmlFiles() {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(file);
            Enumeration entries = zipFile.entries();

            int i = 0;
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();

                if (zipEntry.getName().endsWith(".xml")) {
                    xmlFiles[i] = new XmlFile(zipFile.getInputStream(zipEntry));
                    i++;
                }
            }

            DirectoryValidator.checkXmlFilesAmount(xmlFiles);
            readXmlFilesBeforeZipFileClose();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readXmlFilesBeforeZipFileClose() {
        for (XmlFile xmlFile: xmlFiles) {
            xmlFile.read();
        }
    }

    public XmlFile[] getXmlFiles() {
        return xmlFiles;
    }
}
