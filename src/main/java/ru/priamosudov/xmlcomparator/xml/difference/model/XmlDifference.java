package ru.priamosudov.xmlcomparator.xml.difference.model;

import javax.persistence.*;

@Entity
@Table(name = "difference")
public class XmlDifference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "control_xpath")
    private String controlXPath;

    @Column(name = "control_value")
    private String controlValue;

    @Column(name = "control_parent_xpath")
    private String controlParentXPath;

    @Column(name = "test_xpath")
    private String testXPath;

    @Column(name = "test_value")
    private String testValue;

    @Column(name = "test_parent_xpath")
    private String testParentXPath;

    @Column(name = "difference_type")
    private String differenceType;

    @Column(name = "message")
    private String message;

    @Column(name = "file_name")
    private String fileName;

    public String getControlXPath() {
        return controlXPath;
    }

    public void setControlXPath(String controlXPath) {
        this.controlXPath = controlXPath;
    }

    public String getControlValue() {
        return controlValue;
    }

    public void setControlValue(String controlValue) {
        this.controlValue = controlValue;
    }

    public String getControlParentXPath() {
        return controlParentXPath;
    }

    public void setControlParentXPath(String controlParentXPath) {
        this.controlParentXPath = controlParentXPath;
    }

    public String getTestXPath() {
        return testXPath;
    }

    public void setTestXPath(String testXPath) {
        this.testXPath = testXPath;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public String getTestParentXPath() {
        return testParentXPath;
    }

    public void setTestParentXPath(String testParentXPath) {
        this.testParentXPath = testParentXPath;
    }

    public String getDifferenceType() {
        return differenceType;
    }

    public void setDifferenceType(String differenceType) {
        this.differenceType = differenceType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + controlParentXPath.hashCode();
        result = result * prime + testParentXPath.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        XmlDifference xmlDifference = (XmlDifference) obj;
        if (!xmlDifference.controlParentXPath.equals(controlParentXPath)) {
            return false;
        }
        if (!xmlDifference.testParentXPath.equals(testParentXPath)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return message;
    }
}
