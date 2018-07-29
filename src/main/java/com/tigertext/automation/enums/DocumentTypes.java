package com.tigertext.automation.enums;

public enum DocumentTypes {

    WORD("word"),
    PDF("pdf"),
    POWERPOINT("powerpoint"),
    EXCEL("excel");

    public String typeOfDocument;

    DocumentTypes(String documentType) {
        typeOfDocument = documentType;
    }

    public String getDocumentType() {
        return typeOfDocument;
    }

    @Override
    public String toString() { return this.typeOfDocument;}
}
