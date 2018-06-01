package ru.priamosudov.xmlcomparator.ziparchive;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import ru.priamosudov.xmlcomparator.exception.ValidationException;
import ru.priamosudov.xmlcomparator.file.AbstractFile;
import ru.priamosudov.xmlcomparator.xml.comparator.XmlComparator;

import java.io.File;
import java.io.FileNotFoundException;

public class ZipArchiveTest {

    @Test
    public void compareXmlFilesWithOneAndTwoLinkagesTest() throws FileNotFoundException {
        File file = ResourceUtils.getFile(this.getClass().getResource("/xml_files_with_1_and_2_linkages.zip"));

        Assert.assertEquals(2, compareXmlAndGetDiffSize(file));
    }

    @Test(expected = ValidationException.class)
    public void compareXmlFilesNotValidFileTest() throws FileNotFoundException {
        File file = ResourceUtils.getFile(this.getClass().getResource("/xml_files_not_valid.zip"));

        compareXmlAndGetDiffSize(file);
    }

    @Test
    public void compareXmlFilesWithDifferentLinkages() throws FileNotFoundException {
        File file = ResourceUtils.getFile(this.getClass().getResource("/xml_files_different_linkages.zip"));

        Assert.assertEquals(5, compareXmlAndGetDiffSize(file));
    }

    private int compareXmlAndGetDiffSize(File file) {
        ZipArchive zipArchive = new ZipArchive(file);
        zipArchive.findAndReadXmlFiles();

        AbstractFile[] abstractFiles = zipArchive.getXmlFiles();
        XmlComparator xmlComparator = new XmlComparator(abstractFiles[0], abstractFiles[1]);
        xmlComparator.compareXml();

        return xmlComparator.getDifferences().size();
    }
}
