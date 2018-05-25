package ru.priamosudov.xmlcomparator.xml.xmldifference.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xmlunit.diff.Difference;
import ru.priamosudov.xmlcomparator.xml.xmldifference.dao.XmlDifferenceDao;
import ru.priamosudov.xmlcomparator.xml.xmldifference.mapper.XmlDifferenceMapper;
import ru.priamosudov.xmlcomparator.xml.xmldifference.model.XmlDifference;
import ru.priamosudov.xmlcomparator.xml.xmldifference.service.XmlDifferenceService;

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
