package com.tigertext.automation.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StaticData {
    public static HashMap<String, String> errorMessageStaticData = new HashMap<String, String>()
    {{
        put("invalid format", "Oops! Company email addresses are required. Please try again.");
        put("invalid password", "Login Failed");
    }};

    public static final String messageIdRegex = "Message\\sID:\\s[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}";
    public static final String senderTokenRegex = "Sender\\stoken:\\s[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}";
    public static final String messageDateRegex = "Created:\\s[0-9]{4}-[0-9]{2}-[0-9]{2}";
    public static final String sentStatusText = "Sent";
    public static final String forwardedStatusText = "You Forwarded:";
    public static final String deliveredStatusText = "Delivered";
    public static final String failedStatusText = "Send Failed";
    public static final String coldSweatEmoji = "\uD83D\uDE05";
    public static final String validMessage = "This is a test message from the automation.";
    public static final String invalidData = "/invalid";
    public static final String linkMessage = "https://www.google.com";
    public static final String defaultPassword = "Tigerqa1!";
    public static final String endEmailString = "@tigertext.com";
    public static final String defaultFirstName = "test";
    public static final String defaultLastName = "qa";
    public static final String blankMessage = "";
    public static final String militaryFormat = "military";
}