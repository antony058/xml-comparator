package ru.priamosudov.xmlcomparator.xml.xmldifference.model;

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
}
