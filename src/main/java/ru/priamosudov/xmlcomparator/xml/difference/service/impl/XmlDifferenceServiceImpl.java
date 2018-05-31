package ru.priamosudov.xmlcomparator.xml.difference.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xmlunit.diff.Difference;
import ru.priamosudov.xmlcomparator.xml.difference.dao.XmlDifferenceDao;
import ru.priamosudov.xmlcomparator.xml.difference.mapper.XmlDifferenceMapper;
import ru.priamosudov.xmlcomparator.xml.difference.model.XmlDifference;
import ru.priamosudov.xmlcomparator.xml.difference.service.XmlDifferenceService;

import java.util.List;

@Service
public class XmlDifferenceServiceImpl implements XmlDifferenceService {
    private final XmlDifferenceDao xmlDifferenceDao;

    @Autowired
    public XmlDifferenceServiceImpl(XmlDifferenceDao xmlDifferenceDao) {
        this.xmlDifferenceDao = xmlDifferenceDao;
    }

    @Override
    @Transactional
    public void saveXmlDifference(List<Difference> differences, String fileName) {
        List<XmlDifference> xmlDifferences = XmlDifferenceMapper.mapDifferenceToModel(differences, fileName);

        for (XmlDifference xmlDifference: xmlDifferences) {
            xmlDifferenceDao.save(xmlDifference);
        }
    }
}
