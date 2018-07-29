package com.tigertext.automation.enums;

public enum MessageTypes {

    RANDOM("random"),
    EMOJI("emoji"),
    LINK("link"),
    PRIORITYMESSAGE("priority message"),
    INVALID("invalid"),
    BLANK("blank");

    public String typeOfMessage;

    MessageTypes(String documentType) {
        typeOfMessage = documentType;
    }

    public String getDocumentType() {
        return typeOfMessage;
    }

    @Override
    public String toString() { return this.typeOfMessage;}
}
