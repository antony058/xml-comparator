package ru.priamosudov.xmlcomparator.file;

import com.prowidesoftware.swift.model.mt.AbstractMT;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.priamosudov.xmlcomparator.exception.ValidationException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

public class SwiftFile extends AbstractFile {
    private final String SWIFT_FILE_INPUT_STREAM_NOT_FOUND = "Поток чтения swift-файла отсутствует";
    private static final String FIELD_NAME_FOR_CHANGE = "field";

    private AbstractMT abstractMT;

    public SwiftFile(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public void read() {
        if (inputStream == null) {
            throw new ValidationException(SWIFT_FILE_INPUT_STREAM_NOT_FOUND);
        }

        try {
            abstractMT = AbstractMT.parse(inputStream);
            fileContent = abstractMT.getSwiftMessage().toXml();

            changeFieldsName();
        } catch (IOException e) {
            throw new ValidationException(e.getMessage());
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        isFileRead = true;
    }

    private void changeFieldsName() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new InputSource(new StringReader(fileContent)));

        NodeList nodeList = document.getElementsByTagName(FIELD_NAME_FOR_CHANGE);
        for (int i=0;i<nodeList.getLength();i++) {
            changeFileName(document, nodeList.item(i));
        }

        fileContent = castDocumentToString(document);
    }

    private void changeFileName(Document document, Node node) {
        String nameAttrValue = node.getFirstChild().getNextSibling().getFirstChild().getNodeValue();
        node.removeChild(node.getFirstChild());

        document.renameNode(node, null, FIELD_NAME_FOR_CHANGE + nameAttrValue);
    }

    private String castDocumentToString(Document document) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.transform(new DOMSource(document), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }
}
