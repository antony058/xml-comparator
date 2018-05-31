package ru.priamosudov.xmlcomparator.xml.difference.compiler;

import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.Difference;

public class XmlDifferenceMessageCompiler {
    private static final String DIFF_TYPE_TEXT_VALUE = "TEXT_VALUE";
    private static final String DIFF_TYPE_CHILD_LOOKUP = "CHILD_LOOKUP";

    public static String compileMessage(Difference difference) {
        String differenceType = difference.getComparison().getType().toString();
        StringBuilder builder = new StringBuilder();

        switch (differenceType) {
            case DIFF_TYPE_TEXT_VALUE: {
                builder.append("Содержимое узлов различается. ");
                builder.append("Текст узла контрольного файла: ");
                builder.append(difference.getComparison().getControlDetails().getValue().toString());
                builder.append(". Текст узла тестового файла: ");
                builder.append(difference.getComparison().getTestDetails().getValue().toString());

                return builder.toString();
            }
            case DIFF_TYPE_CHILD_LOOKUP: {
                Comparison comparison = difference.getComparison();

                if (comparison.getControlDetails().getValue() == null) {
                    builder.append("В контрольном файле отсутствует узел \'");
                    builder.append(comparison.getTestDetails().getValue()).append("\'");
                } else if (comparison.getTestDetails().getValue() == null) {
                    builder.append("В тестовом файле отсутствует узел \'");
                    builder.append(comparison.getControlDetails().getValue()).append("\'");
                }

                return builder.toString();
            }
            default: {
                return difference.toString();
            }
        }
    }
}
