package ru.priamosudov.xmlcomparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import org.xmlunit.XMLUnitException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.*;
import ru.priamosudov.xmlcomparator.exception.ValidationException;

import java.util.Iterator;

@Component
public class XmlComparator {
    private static final String NOT_VALID_XML_FILE = "Не валидный xml-файл";

    @Autowired
    public XmlComparator() {
//        XMLUnit.setIgnoreWhitespace(true);
    }

    public boolean compareXml() {
        String sourceXml = "<account><age>12</age><name><first></first></name></account>";
        String controlXml = "<account><age>12</age><name><last><subLast></subLast><f></f></last></name></account>";
        Diff diff;
        try {
            diff = DiffBuilder
                    .compare(sourceXml)
                    .withTest(controlXml)
                    .checkForSimilar()
                    .ignoreComments()
                    .ignoreWhitespace()
                    .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.and(ElementSelectors.byName)))
                    .build();
        } catch (XMLUnitException e) {
            throw new ValidationException(NOT_VALID_XML_FILE.concat(". ").concat(e.getMessage()));
        }

        if (diff.hasDifferences()) {
            Iterator<Difference> iterator = diff.getDifferences().iterator();

            while (iterator.hasNext()) {
                Difference difference = iterator.next();
                System.out.println(difference);
            }

            return false;
        }

        return true;
    }
}
