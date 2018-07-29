package com.tigertext.automation.common;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.config.DriverConfig;
import com.tigertext.automation.config.TestConfig;
import com.tigertext.automation.enums.*;
import com.tigertext.automation.pageObjects.AppRatingPopUp;
import com.tigertext.automation.pageObjects.ForumScreen;
import com.tigertext.automation.pageObjects.LoginScreen;
import com.tigertext.automation.pageObjects.MessagingScreen;
import com.util.WebDriverController;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonMethods {
    MobileAPI mobileWebdriver = new MobileAPI();
    LoginScreen loginPO = new LoginScreen(WebDriverController.appium_driver);
    AppRatingPopUp appRatingPopUpPO = new AppRatingPopUp(WebDriverController.appium_driver);
    MessagingScreen messagingPO = new MessagingScreen(WebDriverController.appium_driver);
    ForumScreen forumsPO = new ForumScreen(WebDriverController.appium_driver);
    AttachmentTypes typeOfAttachmentsEnum;
    DocumentTypes typeOfDocumentsEnum;
    MessageTypes typeOfMessagesEnum;
    MessageOptions typeOfMessageOptionsEnum;
    QuickPickOptions typeOfQuickPickEnum;
    Logger logger = Logger.getLogger(CommonMethods.class.getName());

    public void enterEmail(String email){
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getUserNameTextField(), 120);
        loginPO.getUserNameTextField().sendKeys(email);
        loginPO.getNextButton().click();
    }

    public void enterPassword(String password){
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getUserPasswordTextField(), 120);
        loginPO.getUserPasswordTextField().sendKeys(password);
        loginPO.getPasswordNextButton().click();
    }

    public void createPassword(String password){
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getUserPasswordTextField(), 120);
        loginPO.getUserPasswordTextField().sendKeys(password);
        loginPO.getReEnterPasswordPath().sendKeys(password);
        loginPO.getPasswordNextButton().click();

    }
    public void enterOneLoginCredentials(String user){
        String email = TestConfig.Environment.getUserEmail(user);
        String password = TestConfig.Environment.getUserPassword(user);
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getOneLoginEmailTextField(), 140);
        loginPO.getOneLoginEmailTextField().sendKeys(email);
        loginPO.getOneLoginPasswordTextField().sendKeys(password);
        loginPO.getOneLoginLogInButton().click();
    }

    public void enterOneLoginEmail(String email){
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getOneLoginEmailTextField(), 120);
        loginPO.getOneLoginEmailTextField().sendKeys(email);
    }

    public void enterOneLoginPassword(String password){
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getOneLoginPasswordTextField(), 120);
        loginPO.getOneLoginPasswordTextField().sendKeys(password);
        loginPO.getOneLoginLogInButton().click();
    }

    public void sendMessageToUserAPI(String senderUserName, String recipientCustomerToken, String orgToken, String message, String priority){
        String senderApiKey = TestConfig.Environment.getApiKey(senderUserName);
        String senderSecretKey = TestConfig.Environment.getSecret(senderUserName);
        String[] command = {
                "curl",
                TestConfig.Environment.getDevApi() + "message",
                "-H",
                "content-type: application/json",
                "-u",
                senderApiKey + ":" + senderSecretKey,
                "--data-binary",
                "{\"recipient\":\"" + recipientCustomerToken + "\",\"body\":\"" + message + "\",\"sender_organization\":\""+ orgToken +"\",\"recipient_organization\":\""+ orgToken +"\",\"priority\":"+ priority +",\"ttl\":43200}"};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process p;
        try {
            p = processBuilder.start();
            BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String returnTimePlusMinute(String currentTime, Integer modifiedMinutes){
        logger.log(Level.INFO,"currenttime: "+currentTime);
        logger.log(Level.INFO,"modified mins: "+modifiedMinutes);
        String[] minutes = currentTime.split(":");
        Integer newMinute = Integer.parseInt(minutes[1]) + modifiedMinutes;
        Integer newHours = Integer.parseInt(minutes[0]);
        String timeOfDay = minutes[2];
        String minuteSeparator = ":";
        String newTime = "";

        if(newMinute < 10 || newMinute >= 60) {
            minuteSeparator = ":0";
        }

        if(newMinute >= 10)
        {
            minuteSeparator = ":";

        }

        if(newMinute >= 60)
        {
            logger.log(Level.INFO,"MINUTES ARE 60 OR 60+");

            newHours += 1;
            logger.log(Level.INFO,"ADDING +1 TO HOURS: "+ newHours);

            newMinute = 0;
        }

        logger.log(Level.INFO,"REPLACING MINUTES");
        if(newMinute.equals(0))
        {
            logger.log(Level.INFO,"minutes are 0");
            minuteSeparator = ":00";
            newTime = currentTime.replaceFirst(":\\d{2}", minuteSeparator);
        }
        else {
            newTime = currentTime.replaceFirst(":\\d{2}", minuteSeparator + newMinute.toString());
        }
        logger.log(Level.INFO,"new time:"+newTime);

        logger.log(Level.INFO,"REPLACING HOURS");

        if(newHours < 10)
        {
            logger.log(Level.INFO,"HOURS ARE LESS THAN 10");
            newTime = newTime.replaceFirst("^\\d{2}", "0" + newHours.toString());
            logger.log(Level.INFO,"HOURS: " + newTime );
        }
        else
        {
            logger.log(Level.INFO,"HOURS ARE MORE THAN 10" );
            newTime = newTime.replaceFirst("^\\d{2}", newHours.toString());
            logger.log(Level.INFO,"HOURS: " + newTime);


            if(newHours.equals(12))
            {
                logger.log(Level.INFO,"HOUR IS 12");
                if(timeOfDay.toLowerCase().equals("am")) {
                    logger.log(Level.INFO, "changing time of day to PM");

                    changeTimeOfDayOnSentMessage(newTime,"PM");
                }
                else
                {
                    logger.log(Level.INFO, "changing time of day to AM");
                    changeTimeOfDayOnSentMessage(newTime,"AM");

                }

            }

        }
        logger.log(Level.INFO,"new time:"+newTime);

        return newTime;
    }

    public String returnTimePlusHours(String currentTime, Integer modifiedHours){
        String[] time = currentTime.split(":");
        logger.log(Level.INFO,"hours: "+ time[0]);
        logger.log(Level.INFO,"time of day: "+ time[2]);
        Integer newHours = Integer.valueOf(time[0]) + modifiedHours;
        logger.log(Level.INFO,"new hours + modified hours: "+ newHours);
        String newTime = "";
        boolean changeTime = false;

        if(newHours>12)
        {
            int difference = newHours-12;
            logger.log(Level.INFO,"hour difference: "+difference);
            newHours = 0 + difference;
            logger.log(Level.INFO,"fixed hours: "+ newHours);
            if(newHours<12 || newHours.equals(12))
            {
                if (time[2].equals("AM")) {
                    logger.log(Level.INFO, "changing time of day PM");
                    newTime = changeTimeOfDayOnSentMessage("PM", currentTime);
                } else if (time[2].equals("PM")) {
                    logger.log(Level.INFO, "changing time of day to AM");
                    newTime = changeTimeOfDayOnSentMessage("AM", currentTime);
                }
            }

        }

        if(newHours<10)
        {
            newTime = currentTime.replaceFirst("^\\d{1,2}", "0"+newHours.toString());
            logger.log(Level.INFO, "newHours < 10:" + newTime);

        }
        else if(newHours>=10){
            newTime = currentTime.replaceFirst("^\\d{1,2}", newHours.toString());
            logger.log(Level.INFO, "newHours > 10:" + newTime);

        }

        return newTime;
    }

    private String changeTimeOfDayOnSentMessage(String timeOfDay, String newTime) {

        String time = "";

        logger.log(Level.INFO, "before change: "+newTime);
        time = newTime.replaceFirst("[A-Z]{2}",timeOfDay);
        logger.log(Level.INFO, "time: "+time);
        logger.log(Level.INFO, "returning time: "+time);

        return  time;
    }

    public void assertTextIsInElementLoop(MobileElement element, String text){
        int loopAmount = 15;
        int loopExit = 20;
        for(int x = 0; x <loopAmount; x++) {
            String elementText = element.getText();
            if (elementText.equals(text)) {
                x = loopExit;
            } else {
                if (x == loopAmount) {
                    org.junit.Assert.fail("The text: " + text + " does not appear in the element " + element.getId());
                }
            }
        }
    }

    public boolean assertTextIsInElementList(List<MobileElement> elementList, String text)throws Throwable{
        boolean textFound = false;
        for(MobileElement mobileElement:elementList){
            String textInElement = mobileWebdriver.returnText(mobileElement);
            if(!textInElement.equals(text)) {
                continue;
            }
            else {
                textFound = true;
                break;
            }
        }
        return textFound;
    }

    public int returnLocationOfElementInList(List<MobileElement> elementList, String nameOfForum)throws Throwable{
        int location = 0;
        boolean found = false;
        for(MobileElement mobileElement:elementList){
            String textInElement = mobileWebdriver.returnText(mobileElement);
            logger.info("name of forum: "+ textInElement);
            logger.info("forum passed name: "+ nameOfForum);
            location += 1;
            if(!textInElement.equals(nameOfForum)) {
                continue;
            }
            else {
                found = true;
                break;
            }

        }
        if(!found)
            Assert.fail("Unable to find location of the element. Element not found!");

        return location-1;
    }

    public void selectExistingMessageRecepient(String recipient)throws Throwable {
        mobileWebdriver.data.addDictionary("messageRecipient",recipient);
        mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(messagingPO.CONVERSATIONGROUPDISPLAYNAME, "GROUPNAME", recipient),120);
        mobileWebdriver.assertDynamicElementTextIsPresent(messagingPO.CONVERSATIONGROUPDISPLAYNAME, "GROUPNAME",
                recipient, recipient, "The group : " + recipient + " does not exist.");
        mobileWebdriver.clickDynamicElement(messagingPO.CONVERSATIONGROUPDISPLAYNAME, "GROUPNAME", recipient);
    }

    public void acceptingTheNotificationAlerts(){
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getActivateButton());
        loginPO.getActivateButton().click();
        mobileWebdriver.waitForElementAndClick(loginPO.getAllowAlertButton());
        mobileWebdriver.waitForElementAndClick(loginPO.getAllowAlertButton());
    }

    public void addMemberToConversation(String user) throws Throwable {
        try {
            logger.info("adding member: "+ user);
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getForwardRecipientsPills(),60);
            messagingPO.getForwardRecipientsPills().sendKeys(user);
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getContactDisplayName(),60);
            String userEntry = mobileWebdriver.returnText(messagingPO.getContactDisplayName());
            if(userEntry.equals(user)) {
                mobileWebdriver.waitForElementAndClick(messagingPO.getContactDisplayName(), 60);
            }
            mobileWebdriver.waitForElementAndClick(messagingPO.getNewGroupDoneAddingUsersButton(),60);
        }catch (Exception e){}
    }

    public void selectMessageAttachmentType(String attachmentType) throws Throwable{
        typeOfAttachmentsEnum = AttachmentTypes.valueOf(attachmentType.replace(" ","").toUpperCase());
        switch(typeOfAttachmentsEnum){
            case PICTURE:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationCameraOptionAttachmentButton());
                try {
                    mobileWebdriver.waitForElementAndClick(loginPO.getAllowAlertButton(), 60);
                    mobileWebdriver.waitForElementAndClick(loginPO.getAllowAlertButton(), 60);
                }catch (Exception e){}
                try
                {
                    mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getNextButton(),60);
                    loginPO.getNextButton().click();
                }
                catch (Exception e){}
                mobileWebdriver.waitForElementAndClick(messagingPO.getCameraShutterButton(),120);
                mobileWebdriver.waitForElementAndClick(messagingPO.getCameraOkayButton(),60);
                break;
            case RECORDEDAUDIO:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationAudioAttachmentButton());
                mobileWebdriver.waitForElementAndClick(messagingPO.getRecorderRecordingButton());
                try {
                    mobileWebdriver.waitForElementAndClick(loginPO.getAllowAlertButton());
                }catch (Exception e){}
                mobileWebdriver.navigateBack();
                break;
            case LOCATION:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationLocationOptionAttachmentButton());
                try {
                    mobileWebdriver.waitForElementAndClick(loginPO.getAllowAlertButton());
                }catch(Exception e){}
                mobileWebdriver.waitForElementAndClick(messagingPO.getGoogleSelectMarkerLocation());
                mobileWebdriver.waitForElementAndClick(messagingPO.getGoogleLocationConfirmButton());
                String locationTextMessage = mobileWebdriver.returnText(messagingPO.getConversationTextField());
                mobileWebdriver.data.addDictionary("message",locationTextMessage);
                break;
            case MESSAGE:
                typeValidMessage(true, attachmentType);
                break;
            case SAVEDPICTURE:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationGalleryOptionAttachmentButton());
                try {
                    mobileWebdriver.waitForElementAndClick(loginPO.getAllowAlertButton());
                }catch(Exception e){}
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationGalleryImage().get(0));
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationSendAttachmentDoneButton());
                break;
            case DOCUMENT:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationOptionFileAttachmentButton());
                mobileWebdriver.waitForElementAndClick(loginPO.getAllowAlertButton(),60);
                selectMessageDocumentType(mobileWebdriver.data.getDictionary("documentType"));
                break;
            default:
                break;
        }
    }

    public void typeValidMessage(Boolean scriptState, String messageType) throws Throwable {
        String message = "";
        mobileWebdriver.waitForElementAndClick(messagingPO.getConversationGroupName());
        if (scriptState) {
            message = selectMessageType(messageType);
            if("priority message".equals(messageType)) {
                messagingPO.getConversationPriorityMessageButton().click();
            }
            mobileWebdriver.data.addDictionary("message", message);
            mobileWebdriver.data.addDictionary("messageType", messageType);
            messagingPO.getConversationTextField().sendKeys(message);
        }
    }

    public String selectMessageType(String messageType)throws  Throwable{
        typeOfMessagesEnum = MessageTypes.valueOf(messageType.replace(" ","").toUpperCase());
        String message = "";
        switch (typeOfMessagesEnum){
            case RANDOM:
                message = mobileWebdriver.returnRandomText();
                break;
            case EMOJI:
                message = StaticData.coldSweatEmoji;
                break;
            case LINK:
                message = StaticData.linkMessage;
                break;
            case PRIORITYMESSAGE:
                message = StaticData.validMessage;
                mobileWebdriver.data.addDictionary("messageOption", messageType);
                break;
            case BLANK:
                message = StaticData.blankMessage;
                mobileWebdriver.data.addDictionary("messageOption", messageType);
                break;
            case INVALID:
                message = StaticData.invalidData;
                mobileWebdriver.data.addDictionary("messageOption", messageType);
                break;
            default:
                message = StaticData.validMessage;
                break;
        }
        return  message;
    }

    public void selectMessageDocumentType(String documentType)throws Throwable{
        typeOfDocumentsEnum = DocumentTypes.valueOf(documentType.toUpperCase());
        switch (typeOfDocumentsEnum){
            case PDF:
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getOpenFromFolderPDFfile());
                mobileWebdriver.longPressElement(messagingPO.getOpenFromFolderPDFfile());
                mobileWebdriver.waitForElementAndClick(messagingPO.getOpenFromFolderMenuOpenButton());
                break;
            case WORD:
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getOpenFromFolderDocfile());
                mobileWebdriver.longPressElement(messagingPO.getOpenFromFolderDocfile());
                mobileWebdriver.waitForElementAndClick(messagingPO.getOpenFromFolderMenuOpenButton());
                break;
            case POWERPOINT:
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getOpenFromFolderPPfile());
                mobileWebdriver.longPressElement(messagingPO.getOpenFromFolderPPfile());
                mobileWebdriver.waitForElementAndClick(messagingPO.getOpenFromFolderMenuOpenButton());
                break;
            case EXCEL:
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getOpenFromFolderXLSfile());
                mobileWebdriver.longPressElement(messagingPO.getOpenFromFolderXLSfile());
                mobileWebdriver.waitForElementAndClick(messagingPO.getOpenFromFolderMenuOpenButton());
                break;
            default:
                break;
        }

    }

    public void sendMessageAttachmentType(String attachmentType)throws  Throwable{
        if(!attachmentType.contains("document")) {
            typeOfAttachmentsEnum = AttachmentTypes.valueOf(attachmentType.replace(" ", "").toUpperCase());
        }
        else{
            typeOfAttachmentsEnum = AttachmentTypes.valueOf(attachmentType.replace(" ","").substring(0,8).toUpperCase());
        }
        if(attachmentType.toLowerCase().contains("document")) {
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationDocFileName(), 60);
        }
        switch(typeOfAttachmentsEnum){
            case PICTURE: ;
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationImagePreview(),60);
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationSendButton(),60);
                mobileWebdriver.waitForElementAndClick(messagingPO.getImageQualitySendButton(),60);
                break;
            case RECORDEDAUDIO:
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationRecording(),60);
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationSendButton(),60);
                break;
            case SAVEDPICTURE:
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationImagePreview(),60);
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationSendButton(),60);
                mobileWebdriver.waitForElementAndClick(messagingPO.getImageQualitySendButton(),60);
                break;
            default:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationSendButton(),60);
                break;
        }
    }

    public void selectSentMessageOptions(String messageOption)throws Throwable{
        typeOfMessageOptionsEnum = MessageOptions.valueOf(messageOption.replace(" ","").toUpperCase());
        switch (typeOfMessageOptionsEnum) {
            case DETAILS:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationOptionsDetailsButton(),60);
                break;
            case FORWARD:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationOptionsForwardButton(),60);
                break;
            case RESEND:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationOptionsResendButton(),60);
                break;
            case RECALL:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationOptionsRecallButton(),60);
                break;
            case RESENDASPRIORITY:
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationOptionsResendAsPriorityMessageButton(),60);
                break;
            default:
                break;
        }
    }

    public void selectQuickPick(String quickPickOption)throws Throwable{
        typeOfQuickPickEnum = QuickPickOptions.valueOf(quickPickOption.toUpperCase());

        switch(typeOfQuickPickEnum){
            case FIRST:
                mobileWebdriver.data.addDictionary("message",mobileWebdriver.returnText(messagingPO.getConversationQuickReplyMessage().get(0)));
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationQuickReplyMessage().get(0));
                break;
            case SECOND:
                mobileWebdriver.data.addDictionary("message",mobileWebdriver.returnText(messagingPO.getConversationQuickReplyMessage().get(1)));
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationQuickReplyMessage().get(1));
                break;
            case THIRD:
                mobileWebdriver.data.addDictionary("message",mobileWebdriver.returnText(messagingPO.getConversationQuickReplyMessage().get(2)));
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationQuickReplyMessage().get(2));
                break;
            default:
                break;
        }
    }

    public void searchForNameInPage(String name) throws Throwable
    {
        int swipeCounter = 15;
        boolean forumFound = false;
        for(int i = 0; i <swipeCounter; i++)
        {
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getContactDisplayName(),60);
            try{
                mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(forumsPO.DYNAMICFORUMSTABPATH,"FORUMTAB",name));
                forumFound = true;
            }catch (Exception e){
                mobileWebdriver.swipeDirection("up");
            }
            if(forumFound)
                break;
        }

    }

    public void selectServerConnection(String serverName){
        if("usa".equals(serverName.toLowerCase())) {
            mobileWebdriver.waitForElementAndClick(loginPO.getServerSelectUS(), 60);
        }
        else if("singapore".equals(serverName.toLowerCase())) {
            mobileWebdriver.waitForElementAndClick(loginPO.getServerSelectSingapore(), 60);
        }
        else {
            mobileWebdriver.waitForElementAndClick(loginPO.getCancelServerSelectButton(), 60);
        }
    }


    public void scrollUsingCoords(int startX, int startY,int endX, int endY) {
        TouchAction touchAction4 = new TouchAction(WebDriverController.appium_driver);
        touchAction4.press(startX,startY).waitAction()
                .moveTo(endX,endY)
                .release().perform();
        WebDriverController.appium_driver.performTouchAction(touchAction4);
    }

    public void tapUsingCoords(int startX, int startY) {
        TouchAction touchAction4 = new TouchAction(WebDriverController.appium_driver);
        touchAction4.press(startX,startY).waitAction()
                .release().perform();
        WebDriverController.appium_driver.performTouchAction(touchAction4);
    }

    public void logOutOfApp() throws Throwable {
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getSettingsButtonPath(),120);
        loginPO.getSettingsButtonPath().click();
        mobileWebdriver.assertTextIsPresent(messagingPO.getConversationGroupName(),"Settings");

        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getAndroidHelpButton(),60);
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getAndroidSecurityButton(),60);

        scrollUsingCoords(100,1000,100,500);
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getAndroidLogOutButton(),60);
        loginPO.getAndroidLogOutButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getAndroidLogOutAlertButton(),60);
        loginPO.getAndroidLogOutAlertButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getGetStartedButton(), 60);
        mobileWebdriver.assertPageObjectIsPresent(loginPO.getGetStartedButton());
    }

    public void closeRatingApp()
    {
        mobileWebdriver.waitForPageObjectToBeClickable(appRatingPopUpPO.getCloseRatingAppButton(),60);
        appRatingPopUpPO.getCloseRatingAppButton().click();
        Assert.assertFalse(mobileWebdriver.elementExist(appRatingPopUpPO.getCloseRatingAppButton()));
    }

    public String switchTimeFormat(String format, String time) throws Throwable
    {

        System.out.println("SWITCHING TO TIME FORMAT: " + format );
        String returnUpdatedFormatTime = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        SimpleDateFormat militaryDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();

        if(format.toLowerCase().equals(StaticData.militaryFormat))
        {
            System.out.println("TIME TO FORMAT: "+ time);
            returnUpdatedFormatTime = militaryDateFormat.format((dateFormat.parse(time)));
            System.out.println("SWITCHED TO MILITARY TIME: "+ returnUpdatedFormatTime);
        }
        else
        {
            System.out.println("TIME TO FORMAT: "+ time);
            returnUpdatedFormatTime = dateFormat.format(date);
            System.out.println("SWITCHED TO REGULAR TIME: "+ returnUpdatedFormatTime);

        }

        return returnUpdatedFormatTime;


    }

    public String updateMilitaryTimeWithOffsetHours(String time, int offSet) throws  Throwable
    {

        //offset is the number of hours added to current time
        int timeOffSet = offSet;
        String givenMilitaryTime = time;


        if(timeOffSet!=0)
        {
            System.out.println("ADDING TIMEOFFSET: " + timeOffSet);
            System.out.println("GIVENTIME: " + givenMilitaryTime);
            String[] militaryTime = givenMilitaryTime.split(":");
            String militaryHour = militaryTime[0];
            String militaryMinutes = militaryTime[1];
            int newMilitaryHours = Integer.valueOf(militaryHour) + timeOffSet;
            System.out.println("MILITARY HOUR/MINS: " + militaryHour + "/" + militaryMinutes);
            System.out.println("NEW MILITARY HOURS: " + newMilitaryHours);

            if(newMilitaryHours >= 24)
            {
                if(newMilitaryHours == 24) {
                    givenMilitaryTime = givenMilitaryTime.replaceFirst("^[0-9]{2}", "00");
                }
                else
                {
                    int remainingHours = newMilitaryHours - 24;
                    System.out.println("MILITARY HOURS > than 24: " + remainingHours);


                    if(remainingHours < 10) {
                        System.out.println("MILITARY HOURS < than 10: " + remainingHours);
                        String newMilitaryTime = "0" + String.valueOf(remainingHours);
                        givenMilitaryTime = givenMilitaryTime.replaceFirst("^[0-9]{2}", newMilitaryTime);
                    }
                    else
                    {
                        givenMilitaryTime = givenMilitaryTime.replaceFirst("^[0-9]{2}", String.valueOf(remainingHours));
                    }
                }
            }
            else
            {
                givenMilitaryTime = givenMilitaryTime.replaceFirst("^[0-9]{2}", String.valueOf(newMilitaryHours));
            }

            System.out.println("RETURNING UPDATED MILITARY WITH OFFSET: " + givenMilitaryTime);
        }

        return givenMilitaryTime;
    }


    public String updateMilitaryTimeMinutes(String time, int minuteOffSet) throws  Throwable
    {

        //offset is the number of hours added to current time
        int minuteTimeOffSet = minuteOffSet;
        String givenMilitaryTime = time;

        if(minuteOffSet!=0)
        {
            System.out.println("ADDING MINUTETIMEOFFSET: " + minuteOffSet);
            String[] militaryTime = givenMilitaryTime.split(":");
            String militaryHour = militaryTime[0];
            String militaryMinutes = militaryTime[1];
            int newMilitaryMinutes = Integer.valueOf(militaryMinutes) + minuteOffSet;
            System.out.println("MILITARY HOUR/MINS: " + militaryHour + "/" + militaryMinutes);
            System.out.println("NEW MILITARY MINUTES: " + newMilitaryMinutes);

            if(newMilitaryMinutes > 59)
            {

                System.out.println("MILITARY MINUTES ARE MORE THAN 59");

                newMilitaryMinutes -= 60;
                System.out.println("NEW MILITARY MINUTES: " + newMilitaryMinutes);

                if(newMilitaryMinutes == 60)
                {
                    if(newMilitaryMinutes <10)
                    {
                        givenMilitaryTime = givenMilitaryTime.replaceFirst(":[0-9]{2}", ":0" + newMilitaryMinutes);
                    }
                    else
                    {
                        givenMilitaryTime = givenMilitaryTime.replaceFirst(":[0-9]{2}", ":" + newMilitaryMinutes);

                    }
                    System.out.println("MILITARY MINUTES ARE 60, NEW MINUTES ARE: " + givenMilitaryTime);
                }
                else
                {

                    if(newMilitaryMinutes <10)
                    {
                        givenMilitaryTime = givenMilitaryTime.replaceFirst(":[0-9]{2}", ":0" + newMilitaryMinutes);
                    }
                    else
                    {
                        givenMilitaryTime = givenMilitaryTime.replaceFirst(":[0-9]{2}", ":" + newMilitaryMinutes);

                    }
                    System.out.println("MILITARY MINUTES ARE 60, NEW MINUTES ARE: " + givenMilitaryTime);

                }


                givenMilitaryTime = updateMilitaryTimeWithOffsetHours(givenMilitaryTime, 1);

            }
            else
            {
                givenMilitaryTime = givenMilitaryTime.replaceFirst(":[0-9]{2}", ":" + String.valueOf(newMilitaryMinutes));
            }

            System.out.println("RETURNING UPDATED MILITARY WITH OFFSETMINUTES: " + givenMilitaryTime);
        }

        return givenMilitaryTime;
    }


}