package ru.priamosudov.xmlcomparator;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.XMLUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

@Component
public class XmlComparator {

    @Autowired
    public XmlComparator() {
        XMLUnit.setIgnoreWhitespace(true);
    }

    public boolean compareXml() throws IOException, SAXException {
        String sourceXml = "<account><age>12</age><name></name></account>";
        String controlXml = "<account><name></name><age>12</age></account>";
        Diff diff = new Diff(sourceXml, controlXml);

        if (!diff.similar()) {
            DetailedDiff detailedDiff = new DetailedDiff(diff);
            List differences = detailedDiff.getAllDifferences();

            for (Object object: differences) {
                Difference difference = (Difference) object;
                System.out.println(difference);
            }

            return false;
        }

        return true;
    }
}
