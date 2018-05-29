package ru.priamosudov.xmlcomparator.xml.xmldifference.dao.impl;

import org.springframework.stereotype.Repository;
import ru.priamosudov.xmlcomparator.xml.xmldifference.dao.XmlDifferenceDao;
import ru.priamosudov.xmlcomparator.xml.xmldifference.model.XmlDifference;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class XmlDifferenceDaoImpl implements XmlDifferenceDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(XmlDifference xmlDifference) {
        em.persist(xmlDifference);
    }
}
