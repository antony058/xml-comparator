package ru.priamosudov.xmlcomparator.xml.comparator;

import org.w3c.dom.Element;
import org.xmlunit.XMLUnitException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.*;
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
    private final ElementSelector elementSelector;

    public XmlComparator(AbstractFile xmlFileDev, AbstractFile xmlFileProm) {
        this.xmlFileDev = xmlFileDev;
        this.xmlFileProm = xmlFileProm;
        differences = new ArrayList<>();

        elementSelector = ElementSelectors.conditionalBuilder()
                .whenElementIsNamed("Linkages")
                .thenUse(ElementSelectors.byName)
                .elseUse(ElementSelectors.byNameAndText)
                .build();
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
                    .withDifferenceEvaluator(((comparison, outcome) -> {
                        if (outcome == ComparisonResult.DIFFERENT &&
                                comparison.getType() == ComparisonType.CHILD_NODELIST_SEQUENCE) {
                            return ComparisonResult.EQUAL;
                        }

                        return outcome;
                    }))
                    .withNodeMatcher(new DefaultNodeMatcher(new LinkagesElementSelector(), ElementSelectors.byName))
                    .build();
        } catch (XMLUnitException e) {
            throw new ValidationException(NOT_VALID_XML_FILE.concat(". ").concat(e.getMessage()));
        }

        return diff;
    }

    private class LinkagesElementSelector implements ElementSelector {

        @Override
        public boolean canBeCompared(Element controlElement, Element testElement) {
            if (controlElement.getTagName().equals(testElement.getTagName()) &&
                    controlElement.getChildNodes().getLength() == testElement.getChildNodes().getLength()) {
                return true;
            }

            return false;
        }
    }
}
