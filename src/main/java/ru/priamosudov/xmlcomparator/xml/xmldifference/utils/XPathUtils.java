package ru.priamosudov.xmlcomparator.xml.xmldifference.utils;

public class XPathUtils {
    public static String removeQuotes(String xpath) {
        return xpath.replaceAll("\\[[\\d]\\]", "");
    }
}
