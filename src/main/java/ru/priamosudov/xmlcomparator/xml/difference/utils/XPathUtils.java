package ru.priamosudov.xmlcomparator.xml.difference.utils;

public class XPathUtils {
    public static String removeQuotes(String xpath) {
        return xpath.replaceAll("\\[(\\d+)\\]", "");
    }
}
