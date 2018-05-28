package ru.priamosudov.xmlcomparator.xml.xmlfile;

import org.xmlunit.XMLUnitException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;
import org.xmlunit.diff.ElementSelectors;
import ru.priamosudov.xmlcomparator.exception.ValidationException;
import ru.priamosudov.xmlcomparator.file.AbstractFile;

import java.util.ArrayList;
import java.util.List;

public class XmlComparator {
    private static final String NOT_VALID_XML_FILE = "Не валидный xml-файл";
    private static final String DIFFERENCE_TYPE_CHILD_NODE_LIST_LENGTH = "child nodelist length";
    private final AbstractFile xmlFileDev;
    private final AbstractFile xmlFileProm;
    private final List<Difference> differences;

    public XmlComparator(AbstractFile xmlFileDev, AbstractFile xmlFileProm) {
        this.xmlFileDev = xmlFileDev;
        this.xmlFileProm = xmlFileProm;
        differences = new ArrayList<>();
    }

    public void compareXml() {
        readXmlIfHasNotBeenRead(xmlFileDev);
        readXmlIfHasNotBeenRead(xmlFileProm);

        Diff diff = buildDiffAndCompare();

        if (diff.hasDifferences()) {

            for (Difference difference : diff.getDifferences()) {
                String diffType = difference.getComparison().getType().getDescription().toLowerCase();
                if (diffType.equals(DIFFERENCE_TYPE_CHILD_NODE_LIST_LENGTH)) {
                    continue;
                }
                differences.add(difference);
                System.out.println(difference);
            }
        }
    }

    public List<Difference> getDifferences() {
        return differences;
    }

    private void readXmlIfHasNotBeenRead(AbstractFile xmlFile) {
        if (!xmlFile.isFileRead()) {
            xmlFile.read();
        }
    }

    private Diff buildDiffAndCompare() {
        Diff diff;
        try {
            diff = DiffBuilder
                    .compare(xmlFileDev.getFileContent())
                    .withTest(xmlFileProm.getFileContent())
                    .checkForSimilar()
                    .ignoreComments()
                    .ignoreWhitespace()
                    .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.and(ElementSelectors.byName)))
                    .build();
        } catch (XMLUnitException e) {
            throw new ValidationException(NOT_VALID_XML_FILE.concat(". ").concat(e.getMessage()));
        }

        return diff;
    }
}
