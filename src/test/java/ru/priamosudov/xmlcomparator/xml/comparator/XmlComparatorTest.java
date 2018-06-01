package ru.priamosudov.xmlcomparator.xml.comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import ru.priamosudov.xmlcomparator.exception.ValidationException;
import ru.priamosudov.xmlcomparator.file.AbstractFile;
import ru.priamosudov.xmlcomparator.file.xml.XmlFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XmlComparatorTest {

    @Test
    public void compareXmlFilesWithDifferenceLinkagesTest() throws FileNotFoundException {
        File file1 = ResourceUtils.getFile(this.getClass().getResource("/file_different_linkages_1.xml"));
        File file2 = ResourceUtils.getFile(this.getClass().getResource("/file_different_linkages_2.xml"));

        AbstractFile abstractFile1 = new XmlFile(new FileInputStream(file1));
        AbstractFile abstractFile2 = new XmlFile(new FileInputStream(file2));

        XmlComparator xmlComparator = new XmlComparator(abstractFile1, abstractFile2);
        xmlComparator.compareXml();

        Assert.assertEquals(xmlComparator.getDifferences().size(), 5);
    }

    @Test
    public void compareXmlFilesWithOneDifferentLinkageTest() throws FileNotFoundException {
        File file1 = ResourceUtils.getFile(this.getClass().getResource("/file_with_one_diff_linkage_1.xml"));
        File file2 = ResourceUtils.getFile(this.getClass().getResource("/file_with_one_diff_linkage_2.xml"));

        AbstractFile abstractFile1 = new XmlFile(new FileInputStream(file1));
        AbstractFile abstractFile2 = new XmlFile(new FileInputStream(file2));

        XmlComparator xmlComparator = new XmlComparator(abstractFile1, abstractFile2);
        xmlComparator.compareXml();

        Assert.assertEquals(2, xmlComparator.getDifferences().size());
    }

    @Test(expected = ValidationException.class)
    public void compareXmlFilesNotValidXmlFileTest() throws FileNotFoundException {
        File file1 = ResourceUtils.getFile(this.getClass().getResource("/not_valid_xml_file_1.xml"));
        File file2 = ResourceUtils.getFile(this.getClass().getResource("/not_valid_xml_file_2.xml"));

        AbstractFile abstractFile1 = new XmlFile(new FileInputStream(file1));
        AbstractFile abstractFile2 = new XmlFile(new FileInputStream(file2));

        XmlComparator xmlComparator = new XmlComparator(abstractFile1, abstractFile2);
        xmlComparator.compareXml();
    }
}
