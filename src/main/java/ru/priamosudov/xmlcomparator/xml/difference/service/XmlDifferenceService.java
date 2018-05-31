package ru.priamosudov.xmlcomparator.xml.difference.service;

import org.xmlunit.diff.Difference;

import java.util.List;

public interface XmlDifferenceService {
    void saveXmlDifference(List<Difference> differences, String fileName);
}
