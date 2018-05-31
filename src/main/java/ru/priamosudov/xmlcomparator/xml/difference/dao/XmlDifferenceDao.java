package ru.priamosudov.xmlcomparator.xml.difference.dao;

import ru.priamosudov.xmlcomparator.xml.difference.model.XmlDifference;

public interface XmlDifferenceDao {
    void save(XmlDifference xmlDifference);
}
