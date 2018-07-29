package com.tigertext.automation.enums;

public enum ForumActionOptions {

    LEAVE("Leave"),
    ADDMEMBERS("Add Members"),
    MARKALLASREAD("Mark all as Read"),
    MUTE("Mute"),
    UNMUTE("Unmute");

    public String typeOfQuickPick;

    ForumActionOptions(String documentType) {
        typeOfQuickPick = documentType;
    }

    public String getDocumentType() {
        return typeOfQuickPick;
    }

    @Override
    public String toString() { return this.typeOfQuickPick;}
}
