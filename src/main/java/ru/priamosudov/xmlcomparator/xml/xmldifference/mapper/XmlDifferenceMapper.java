package ru.priamosudov.xmlcomparator.xml.xmldifference.mapper;

import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.Difference;
import ru.priamosudov.xmlcomparator.xml.xmldifference.compiler.XmlDifferenceMessageCompiler;
import ru.priamosudov.xmlcomparator.xml.xmldifference.model.XmlDifference;
import ru.priamosudov.xmlcomparator.xml.xmldifference.utils.XPathUtils;

import java.util.ArrayList;
import java.util.List;

public class XmlDifferenceMapper {

    public static XmlDifference mapDifferenceToModel(Difference difference, String fileName) {
        XmlDifference xmlDifference = new XmlDifference();
        Comparison comparison = difference.getComparison();
        String xpath = null;
        Object value = null;

        xpath = comparison.getControlDetails().getXPath();
        xmlDifference.setControlXPath(xpath == null ? null : XPathUtils.removeQuotes(xpath));

        value = comparison.getControlDetails().getValue();
        xmlDifference.setControlValue(value == null ? null : value.toString());

        xpath = comparison.getControlDetails().getParentXPath();
        xmlDifference.setControlParentXPath(xpath == null ? null : XPathUtils.removeQuotes(xpath));

        xpath = comparison.getTestDetails().getXPath();
        xmlDifference.setTestXPath(xpath == null ? null : XPathUtils.removeQuotes(xpath));

        value = comparison.getTestDetails().getValue();
        xmlDifference.setTestValue(value == null ? null : value.toString());

        xpath = comparison.getTestDetails().getParentXPath();
        xmlDifference.setTestParentXPath(xpath == null ? null : XPathUtils.removeQuotes(xpath));

        xmlDifference.setDifferenceType(comparison.getType().toString());
        xmlDifference.setMessage(XmlDifferenceMessageCompiler.compileMessage(difference));
        xmlDifference.setFileName(fileName);

        return xmlDifference;
    }

    public static List<XmlDifference> mapDifferenceToModel(List<Difference> differences, String fileName) {
        List<XmlDifference> xmlDifferences = new ArrayList<>(differences.size());

        for (Difference difference: differences) {
            xmlDifferences.add(mapDifferenceToModel(difference, fileName));
        }

        return xmlDifferences;
    }
}
