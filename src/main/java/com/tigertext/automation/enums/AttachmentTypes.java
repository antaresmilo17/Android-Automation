package com.tigertext.automation.enums;

public enum AttachmentTypes {

    PICTURE("picture"),
    RECORDEDAUDIO("recorded audio"),
    LOCATION("location"),
    MESSAGE("message"),
    SAVEDPICTURE("saved picture"),
    DOCUMENT("document");

    public String typeOfDocument;

    AttachmentTypes(String documentType) {
        typeOfDocument = documentType;
    }

    public String getDocumentType() {
        return typeOfDocument;
    }

    @Override
    public String toString() { return this.typeOfDocument;}
}
