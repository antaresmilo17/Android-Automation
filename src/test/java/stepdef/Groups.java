package stepdef;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.common.CommonMethods;
import com.tigertext.automation.common.StaticData;
import com.tigertext.automation.config.DriverConfig;
import com.tigertext.automation.config.TestConfig;
import com.tigertext.automation.pageObjects.MessagingScreen;
import com.util.WebDriverController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.*;
import java.security.acl.Group;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Groups {
    MobileAPI mobileWebdriver = new MobileAPI();
    CommonMethods commonMethods = new CommonMethods();
    Login loginMethods = new Login();
    MessagingScreen messagingPO = new MessagingScreen(WebDriverController.appium_driver);
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
    SimpleDateFormat militaryDateFormat = new SimpleDateFormat("HH:mm");
    Date date = new Date();
    String time;
    Logger logger = Logger.getLogger(Groups.class.getName());

    @Given("^I have an existing recipient \"([^\"]*)\"$")
    public void iHaveAnExistingGroup(String group) throws Throwable {
        commonMethods.selectExistingMessageRecepient(group);
    }

    @When("^I (do not )?enter a valid \"([^\"]*)\" message$")
    public void iEnterAValidMessage(String state, String messageType) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        if (scriptState) {
            if(!messageType.isEmpty()) {
                if (!"blank".equals(messageType)) {
                    commonMethods.typeValidMessage(scriptState, messageType);
                }
            }
        }
    }

    @When("^I (do not )?send a valid \"([^\"]*)\" message$")
    public void iSendAValidMessage(String state, String messageType) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        if (scriptState) {
            commonMethods.typeValidMessage(scriptState, messageType);
            iClickOnTheSendMessageButton();
        }
    }

    @When("^I open the message options menu$")
    public void iOpenTheMessageOptionsMenu() throws Throwable {
      iOpenTheDetailsOfaMessage("");
    }

    @When("^I should not see the \"([^\"]*)\" option$")
    public void iShouldNotSeeTheMessageOption(String menuOption) throws Throwable {
        mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationOptionsDetailsButton(),15);
        mobileWebdriver.assertPageObjectIsNotPresent(messagingPO.getConversationOptionsRecallButton());
    }

    @When("^I click on the send message button$")
    public void iClickOnTheSendMessageButton() throws Throwable{
        try {
            messagingPO.getConversationSendButton().click();
            time = dateFormat.format(date);
            if(DriverConfig.config().getString("remote.military").equals("true"))
            {
                time = commonMethods.switchTimeFormat("military",time);
            }
            mobileWebdriver.data.addDictionary("sentTime", time);
        }catch (NoSuchElementException e){
            logger.log(Level.INFO,e.toString());
        }
    }

    @When("^I should (not )?see the latest attachment message and status$")
    public void iShouldSeeTheLatestAttachmentMessageAndStatus(String state) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        String attachmentType = mobileWebdriver.data.getDictionary("attachmentType");
        String documentType = mobileWebdriver.data.getDictionary("documentType");
        String message = mobileWebdriver.data.getDictionary("message");
        String messageRecipient = mobileWebdriver.data.getDictionary("messageRecipient");

        if("everyone".equals(messageRecipient.toLowerCase())) {
            message = "[Everyone]: " + message;
        }
        if(attachmentType.contains("priority")) {
            if(scriptState) {
                mobileWebdriver.assertPageObjectIsPresent(messagingPO.getConversationPriorityMessageTvHeaderName());
            }
            else {
                mobileWebdriver.assertPageObjectIsNotPresent(messagingPO.getConversationPriorityMessageTvHeaderName());
                attachmentType = attachmentType.replace("priority ", "");
            }
        }
        if (scriptState) {
           verifySentMessages(attachmentType,documentType,message);
           verifyMessageTimeStamp();
        } else {
            verifyMessagesAreNotPresent(attachmentType,message);
        }

        mobileWebdriver.navigateBack();
        //if statement below is necessary to get rid of the secure camera pop up
        //automation is unable to find the "NO, THANKS" button through any means
        if(attachmentType.equals("picture") || attachmentType.equals("priority picture")) {
            Thread.sleep(2000);
            commonMethods.tapUsingCoords(314, 961);
        }
//        mobileWebdriver.waitForPageObjectToBeClickable(loginMethods.loginPO.getInboxTab(),60);
//        loginMethods.loginPO.getInboxTab().click();


       // loginMethods.iGoThroughTheLogOutProcess("log out");
        //loginMethods.iShouldSeeTheGetStarterdScreen();

    }

    private void verifySentMessages(String attachmentType,String documentType,String message)throws Throwable{
        mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationMessageStatus().get(0),30);
        commonMethods.assertTextIsInElementLoop(messagingPO.getConversationMessageStatus().get(messagingPO.getConversationMessageStatus().size() - 1), StaticData.sentStatusText);
        if ("picture".equals(attachmentType) || "saved picture".equals(attachmentType)) {
            mobileWebdriver.assertPageObjectIsPresent(messagingPO.getConversationImageViewPreview().get(messagingPO.getConversationImageViewPreview().size() - 1));
        }
        if("recorded audio".equals(attachmentType)) {
            mobileWebdriver.assertPageObjectIsPresent(messagingPO.getConversationAudioProgress().get(messagingPO.getConversationAudioProgress().size() - 1));
        }
        if("location".equals(attachmentType)) {
            mobileWebdriver.assertTextIsPresent(messagingPO.getConversationMessageText().get(messagingPO.getConversationMessageText().size() - 1),
                    message);
        }
        if(attachmentType.contains("document")){
            String documentTypeSentText = mobileWebdriver.returnText(messagingPO.getConversationDocumentPreviewTitle().get(messagingPO.getConversationDocumentPreviewTitle().size()-1)).toLowerCase();
            Assert.assertTrue(documentTypeSentText.equals(documentType));
        }
        mobileWebdriver.assertPageObjectIsPresent(messagingPO.getConversationMessageTimeStamp().get(messagingPO.getConversationMessageTimeStamp().size() - 1));
    }

    private void verifyMessageTimeStamp()throws Throwable{
        int minuteTries = 5;
        String messageTimeStamp = "";
        String originalSentTime = mobileWebdriver.data.getDictionary("sentTime");
        String sentTime = originalSentTime;
        String newTime = "";
        logger.log(Level.INFO,"sent time: " +sentTime);

        try {
            messageTimeStamp = mobileWebdriver.returnText(messagingPO.getConversationMessageTimeStamp()
                    .get(messagingPO.getConversationMessageTimeStamp().size() - 1)).replace(" ", ":");

            System.out.println("MESSAGE TIMESTAMP: " + messageTimeStamp);

            if(!DriverConfig.config().getString("remote.military").equals("true"))
            {
                try {
                    messageTimeStamp = messageTimeStamp.replace(".", "");
                    messageTimeStamp = messageTimeStamp.toUpperCase();
                    logger.info("messageTimeStamp was updated to remove '.' and change pm to PM");
                } catch (Exception e) {
                    logger.info("no '.' found on time of day");
                }
            }
            else
            {
                sentTime = commonMethods.updateMilitaryTimeWithOffsetHours(sentTime,Integer.valueOf(DriverConfig.config().getString("remote.timeoffset")));
            }
            Assert.assertTrue(messageTimeStamp.equals(sentTime));
        } catch (AssertionError e) {

                for (int i = 0; i < minuteTries; i++) {
                    logger.log(Level.INFO, "try number: " + i);
                    try {

                        if(!DriverConfig.config().getString("remote.military").equals("true"))
                        {
                            newTime = commonMethods.returnTimePlusMinute(sentTime, 1);

                        }
                        else
                        {

                            newTime = commonMethods.updateMilitaryTimeMinutes(sentTime,1);
                        }

                        sentTime = newTime;
                        Assert.assertTrue("The timestamp should say: " + messageTimeStamp + " but it says: "
                                + newTime, messageTimeStamp.equals(newTime));
                        break;
                    } catch (AssertionError e1) {
                        if (minuteTries - 1 == i) {
                            break;
                        } else {
                            continue;
                        }
                    }
                }

            Assert.assertTrue("The timestamp should say: " + messageTimeStamp
                    + " but it says: " + newTime, messageTimeStamp.equals(newTime));

        }

    }

    private void verifyMessagesAreNotPresent(String attachmentType, String message)throws  Throwable{

        if("location".equals(attachmentType))
            if(!messagingPO.getConversationMessageText().isEmpty()) {
                mobileWebdriver.assertTextIsNotPresent(messagingPO.getConversationMessageText().get(messagingPO.getConversationMessageText().size() - 1), message);
            }
            else {
                Assert.assertTrue(messagingPO.getConversationMessageText().isEmpty());
            }
        else if(!attachmentType.isEmpty()) {
            if ("picture".equals(attachmentType) || "saved picture".equals(attachmentType)) {
                if (!messagingPO.getConversationImageViewPreview().isEmpty()) {
                    mobileWebdriver.assertPageObjectIsNotPresent(messagingPO.getConversationImageViewPreview().get(messagingPO.getConversationImageViewPreview().size() - 1));
                }
                else {
                    Assert.assertTrue(messagingPO.getConversationImageViewPreview().isEmpty());
                }
            }
            else if ("recorded audio".equals(attachmentType)) {
                if (!messagingPO.getConversationAudioProgress().isEmpty()) {
                    mobileWebdriver.assertPageObjectIsNotPresent(messagingPO.getConversationAudioProgress().get(messagingPO.getConversationAudioProgress().size() - 1));
                }
                else {
                    Assert.assertTrue(messagingPO.getConversationAudioProgress().isEmpty());
                }
            }
            else if (attachmentType.contains("document")) {
                Assert.assertTrue(messagingPO.getConversationDocumentPreviewTitle().isEmpty());
            }
        }
    }

    @When("^Invalid message is not sent and rejected$")
    public void invalidMessageIsNotSentAndRejected() throws Throwable {
        iShouldSeeTheLatestMessageAndStatus("false");
    }

    @When("^I should (not )?see the latest message and status$")
    public void iShouldSeeTheLatestMessageAndStatus(String state) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        String message = mobileWebdriver.data.getDictionary("message");
        String messageOption = mobileWebdriver.data.getDictionary("messageOption");
        String sentTime = mobileWebdriver.data.getDictionary("sentTime");
        String messageSender = mobileWebdriver.data.getDictionary("messageSender");
        String messageRecipient = mobileWebdriver.data.getDictionary("messageRecipient");
        String messageTimeStamp = "";

        if(messageOption.contains("priority")) {
            if(scriptState) {
                mobileWebdriver.assertPageObjectIsPresent(messagingPO.getConversationPriorityMessageTvHeaderName());
            }
            else {
                mobileWebdriver.assertPageObjectIsNotPresent(messagingPO.getConversationPriorityMessageTvHeaderName());
            }
        }
        if (scriptState) {
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationMessageText().get(0),25);
            if("everyone".equals(messageRecipient.toLowerCase())) {
                message = "[Everyone]: " + message;
            }

            //mobileWebdriver.assertTextIsPresent(messagingPO.getConversationMessageText().get(messagingPO.getConversationMessageText().size() - 1), message);
            CorrectTextMessageIsDisplayeed(message);

            logger.log(Level.INFO,"time: "+sentTime);
            verifyMessageTimeStamp();
            if(!"api".equals(messageSender)) {
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationMessageStatus().get(0), 15);
                commonMethods.assertTextIsInElementLoop(messagingPO.getConversationMessageStatus().get(messagingPO.getConversationMessageStatus().size() - 1), StaticData.sentStatusText);
            }
            else
            {
                try{
                    mobileWebdriver.assertTextIsPresent(messagingPO.getConversationMessageStatus().get(messagingPO.getConversationMessageStatus().size() - 1), StaticData.sentStatusText);
                }
                catch(AssertionError e){
                    mobileWebdriver.assertTextIsPresent(messagingPO.getConversationMessageStatus().get(messagingPO.getConversationMessageStatus().size() - 1), StaticData.deliveredStatusText);

                }
            }
            if("invalid".equals(mobileWebdriver.data.getDictionary("messageType"))) {
                message = message + " unable to process your request";
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationMessageText().get(0), 15);
                mobileWebdriver.assertTextIsPresent(messagingPO.getConversationMessageText().get(messagingPO.getConversationMessageText().size() - 2),
                        message);
                commonMethods.assertTextIsInElementLoop(messagingPO.getConversationMessageStatus().get(messagingPO.getConversationMessageStatus().size() - 2), StaticData.failedStatusText);

            }
        } else {
            if(!messageOption.isEmpty()) {
                if (!messagingPO.getConversationMessageText().isEmpty()) {
                    mobileWebdriver.assertTextIsNotPresent(messagingPO.getConversationMessageText().get(messagingPO.getConversationMessageText().size() - 1), message);
                }
                else {
                    Assert.assertTrue(messagingPO.getConversationMessageText().isEmpty());
                }
            }
        }

    }

    private void CorrectTextMessageIsDisplayeed(String message) throws Throwable {
        try {
            logger.info("Checking last message sent");
            mobileWebdriver.assertTextIsPresent(messagingPO.getConversationMessageText().get(messagingPO.getConversationMessageText().size() - 1), message);
        }
        catch(Exception e) {
            logger.info("Checking messages displayed");
            boolean messageFound = false;
            String textfound = "";
            List<MobileElement> test = messagingPO.getConversationMessageText();

            for (MobileElement messageTxt:test) {
                if(message.equals(messageTxt))
                {
                    mobileWebdriver.assertTextIsPresent(messageTxt,message);
                    messageFound = true;
                    textfound = messageTxt.getText();
                    break;
                }
                else
                {
                    continue;
                }
            }

            if(!messageFound)
            {
                Assert.fail("The text in the element is currently displaying: '" + textfound + "', but we are expecting: '" + message + "'!");

            }

        }
    }

    @When("^I select \"([^\"]*)\" from the message options$")
    public void iOpenTheDetailsOfaMessage(String messageOption) throws Throwable {
        try {
            mobileWebdriver.waitForElementAndClick(messagingPO.getConversationGroupName(),15);
            mobileWebdriver.data.addDictionary("messageOption", messageOption);
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationMessageBubble().get(messagingPO.getConversationMessageBubble().size()-1),15);
            List<WebElement> messageBubbleTexts = WebDriverController.appium_driver.findElementsByXPath(messagingPO.DYNAMICCONVERSATIONMESSAGETEXTBUBBLE);
            mobileWebdriver.longPressElement(messageBubbleTexts.get(messageBubbleTexts.size() - 1));
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationOptionsDetailsButton(),15);
            if(!messageOption.isEmpty()) {
                commonMethods.selectSentMessageOptions(messageOption);
            }
        }catch (NoSuchElementException e){
            logger.log(Level.INFO,e.toString());
        }
    }

    @When("^I should (not )?see the id, date, and sender of the message$")
    public void iShouldSeeTheIdDateAndSenderOfTheMessage(String state) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        if(scriptState) {
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationMessageDetailsDialog());
            String messageDialogText = messagingPO.getConversationMessageDetailsDialog().getText();
            String messageId = mobileWebdriver.returnParsedText(StaticData.messageIdRegex, messageDialogText);
            mobileWebdriver.data.addDictionary("messageId", messageId);
            if (messageId.isEmpty()) {
                Assert.fail("Message ID does not exist in the Message Details Dialog!");
            }
            String senderToken = mobileWebdriver.returnParsedText(StaticData.senderTokenRegex, messageDialogText).replace("Sender token: ","");
            mobileWebdriver.data.addDictionary("senderToken", senderToken);
            String userSenderToken = TestConfig.Environment.getUserToken(mobileWebdriver.data.getDictionary("userType"));
            Assert.assertTrue(userSenderToken.equals(senderToken));
            String messageDate = mobileWebdriver.returnParsedText(StaticData.messageDateRegex, messageDialogText);
            mobileWebdriver.data.addDictionary("messageDate", messageDate);
            if (messageDate.isEmpty()) {
                Assert.fail("Message Date does not exist in the Message Details Dialog!");
            }
        }
    }

    @When("^I forward the message to \"([^\"]*)\"$")
    public void iForwardTheMessageToUser(String user) throws Throwable {
        try {
            iOpenTheDetailsOfaMessage("forward");
            MobileElement dynamicUserEntry = mobileWebdriver.returnDynamicElement(messagingPO.DYNAMICADDUSERSELECTOR,"USERNAME",user);
            mobileWebdriver.waitForPageObjectToBeClickable(dynamicUserEntry);
            mobileWebdriver.waitForElementAndClick(dynamicUserEntry);
            mobileWebdriver.waitForElementAndClick(messagingPO.getNewGroupDoneAddingUsersButton());
        }catch (NoSuchElementException e){
            logger.log(Level.INFO,e.toString());
        }
    }

    @When("^I select \"([^\"]*)\" to forward the message to$")
    public void iSelectToForwardTheMessageTo(String user) throws Throwable {
        try {
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getForwardRecipientsPills());
            messagingPO.getForwardRecipientsPills().sendKeys(user);
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getContactDisplayName());
            String userEntry = mobileWebdriver.returnText(messagingPO.getContactDisplayName());
            if(userEntry.equals(user)) {
                mobileWebdriver.waitForElementAndClick(messagingPO.getContactDisplayName());
            }
            mobileWebdriver.waitForElementAndClick(messagingPO.getNewGroupDoneAddingUsersButton());
        }catch (NoSuchElementException e){
            logger.log(Level.INFO,e.toString());
        }
    }

    @Then("^the message should (not )?be sent to \"([^\"]*)\"$")
    public void theMessageShouldBeSentTo(String state, String user) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        if (scriptState) {
            String message = mobileWebdriver.data.getDictionary("message");
            String messageRecipient = mobileWebdriver.data.getDictionary("messageRecipient");
            if("everyone".equals(messageRecipient.toLowerCase())) {
                message = "[Everyone]: " + message;
            }
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationGroupName(), 15);
            List<MobileElement> conversationMessages = messagingPO.getConversationMessageText();
            List<MobileElement> youForwardedMessageText = messagingPO.getConversationForwardedTvDisplayName();
            mobileWebdriver.assertTextIsPresent(conversationMessages.get(conversationMessages.size() - 1), message);
            mobileWebdriver.assertPageObjectIsPresent(youForwardedMessageText.get(youForwardedMessageText.size() - 1));
            mobileWebdriver.assertTextIsPresent(youForwardedMessageText.get(youForwardedMessageText.size() - 1), StaticData.forwardedStatusText);
            mobileWebdriver.assertTextIsPresent(messagingPO.getConversationGroupName(), user);
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationFrwdOriginalPerson());
            mobileWebdriver.assertTextIsPresent(messagingPO.getConversationFrwdOriginalPerson(),TestConfig.Environment.getUserFirstName(mobileWebdriver.data.getDictionary("messageSender")));
        } else {
            try {
                mobileWebdriver.assertDynamicElementTextIsNotPresent(messagingPO.DYNAMICCONVERSATIONMESSAGEFORWARDSELECTEDCONTACT,"USERNAME",user,user);
            }catch (TimeoutException e){
                logger.log(Level.INFO,e.toString());
            }
        }
    }

    @When("^I open the message read by page for the latest message$")
    public void iOpenTheMessageReadByPageForTheLatestMessage() throws Throwable {
        MobileElement latestConversationMessageStatus = messagingPO.getConversationMessageStatus().get(messagingPO.getConversationMessageStatus().size()-1);
        mobileWebdriver.waitForPageObjectToBeClickable(latestConversationMessageStatus);
        mobileWebdriver.waitForElementAndClick(latestConversationMessageStatus);
    }

    @Then("^I should see all of the users message status$")
    public void iShouldSeeAllOfTheUsersMessageStatus() throws Throwable {
        mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationTGroupReadStatusToolbar());
        List <MobileElement> conversationGroupMemberReadStatus = messagingPO.getConversationGroupMemberReadStatus();
        for (MobileElement groupMemberStatus:conversationGroupMemberReadStatus) {
            mobileWebdriver.assertPageObjectIsPresent(groupMemberStatus);
        }
    }

    @And("^I select the \"([^\"]*)\" option$")
    public void iSelectTheQuickPickOption(String quickPickOption) throws Throwable {
        mobileWebdriver.waitForElementAndClick(messagingPO.getConversationPriorityMessageButton(),120);
        mobileWebdriver.waitForElementAndClick(messagingPO.getConversationSendButton());
        commonMethods.selectQuickPick(quickPickOption);
    }

    @When("^I send the \"([^\"]*)\" option$")
    public void iSendTheQuickPickOption(String quickPickOption) throws Throwable {
        try {
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationSendButton());
            mobileWebdriver.waitForElementAndClick(messagingPO.getConversationSendButton());
            time = dateFormat.format(date);
            mobileWebdriver.data.addDictionary("sentTime",time);
        }catch (NoSuchElementException e){
            logger.log(Level.INFO,e.toString());
        }
    }

    @Then("^I should not see the recalled message$")
    public void iShouldNotSeeTheRecalledMessage() throws Throwable {
        List<MobileElement> conversationMessages = messagingPO.getConversationMessageText();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationMessages.get(conversationMessages.size()-1));
        mobileWebdriver.assertTextIsNotPresent(conversationMessages.get(conversationMessages.size()-1),mobileWebdriver.data.getDictionary("message"));
    }

    @When("^I select a \"([^\"]*)\" message$")
    public void iSelectAattachmentTypeMessage(String attachmentType) throws Throwable {
        String documentType = "";
        mobileWebdriver.data.addDictionary("attachmentType",attachmentType);
        mobileWebdriver.waitForElementAndClick(messagingPO.getConversationGroupName());
        if(attachmentType.contains("priority")) {
            mobileWebdriver.waitForElementAndClick(messagingPO.getConversationPriorityMessageButton(),60);
            mobileWebdriver.assertPageObjectIsPresent(messagingPO.getConversationPriorityMessagePill());

            try{
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationPriorityMessageCloseDialogButton(),60);
                messagingPO.getConversationPriorityMessageCloseDialogButton().click();
            }
            catch(Exception e)
            {
                logger.info("priority message dialog not displayed");
            }


            attachmentType = attachmentType.replace("priority ","");
        }
        if(attachmentType.contains("document")){
            documentType = attachmentType.replace("document","").replace(" ","");
            attachmentType = attachmentType.replace(documentType,"").replace(" ","");
            mobileWebdriver.data.addDictionary("documentType",documentType);
        }
        commonMethods.selectMessageAttachmentType(attachmentType);

    }

    @Then("^I (do not )?send a \"([^\"]*)\" message$")
    public void iSendAattachmentTypeMessage(String state, String attachmentType) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        if(attachmentType.contains("priority")) {
            attachmentType = attachmentType.replace("priority ","");
        }
        if (scriptState){
           commonMethods.sendMessageAttachmentType(attachmentType);
           time = dateFormat.format(date);
           mobileWebdriver.data.addDictionary("sentTime",time);
        }
    }

    @And("^I navigate back to the Inbox Screen")
    public void iNavigateBackToTheInboxScreen() throws Throwable {
        WebDriverController.appium_driver.navigate().back();
    }

    @When("^I (do not )?send a \"([^\"]*)\" message to \"([^\"]*)\" through the API")
    public void iSendAMessageToUserThroughTheAPI(String state, String messageType, String user) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        String priority = "0";
        String userToken = "";
        String message = commonMethods.selectMessageType(messageType);
        try {
            mobileWebdriver.waitForElementAndClick(messagingPO.getConversationGroupName());
        }catch (TimeoutException e){
            logger.log(Level.INFO,e.toString());
        }
        if (scriptState) {
            if("auto test existing".equals(user.toLowerCase())) {
                userToken = TestConfig.Environment.getGroupToken();
            }
            else {
                userToken = TestConfig.Environment.getUserToken(user);
            }
            if("priority message".equals(messageType)) {
                priority = "1";
            }
            time = dateFormat.format(date);
            mobileWebdriver.data.addDictionary("message", message);
            mobileWebdriver.data.addDictionary("messageType", messageType);
            mobileWebdriver.data.addDictionary("messageSender","api");
            commonMethods.sendMessageToUserAPI("AndroidQa1",userToken, TestConfig.Environment.getOrgToken(), message, priority);
            mobileWebdriver.data.addDictionary("sentTime",time);
        }
    }

    @Then("^I should (not )?see the push notification on the device$")
    public void iShouldSeeThePushNotificationOnTheDevice(String state) throws Throwable{
        String userName = "";
        mobileWebdriver.swipeDirection("down notification");
        if(mobileWebdriver.elementExist(messagingPO.getTigerTextInboxNotification())) {
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getTigerTextInboxNotification(), 15);
            String[] notificationText = mobileWebdriver.returnText(messagingPO.getTigerTextInboxNotification()).split(" ");
            userName = notificationText[notificationText.length-1];
            messagingPO.getTigerTextInboxNotification().click();
        } else {
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getTigerTextNotification(), 15);
            userName=mobileWebdriver.returnText(messagingPO.getTigerTextNotification());
            messagingPO.getTigerTextNotification().click();
        }
        String conversationUser = mobileWebdriver.returnText(messagingPO.getConversationGroupName());
        Assert.assertTrue("",userName.equals(conversationUser));
        iShouldSeeTheLatestMessageAndStatus("true");
    }

    @When("^I clear user conversations$")
    public void iClearUserConversations() throws Throwable{
        try {
            while (mobileWebdriver.elementExist(messagingPO.getConversationMessageBubble().get(messagingPO.getConversationMessageBubble().size()-1)))
                iOpenTheDetailsOfaMessage("recall");
        }catch (NoSuchElementException | ArrayIndexOutOfBoundsException e){
            logger.log(Level.INFO,e.toString());
        }
    }

    @Then("^I see no user conversations$")
    public void iSeeNoUserConversations() throws Throwable{
        Assert.assertTrue(messagingPO.getConversationMessageBubble().isEmpty());
//        loginMethods.iGoThroughTheLogOutProcess("log out");
//        loginMethods.iShouldSeeTheGetStarterdScreen();
    }


    @When("^testMilitaryTime$")
    public void test() throws Throwable
    {
        time = dateFormat.format(date);
        String militaryTime = commonMethods.switchTimeFormat("military",time);
        String updatedMilitaryTimeHours = commonMethods.updateMilitaryTimeWithOffsetHours(militaryTime,7);

        String time1 = commonMethods.updateMilitaryTimeMinutes(updatedMilitaryTimeHours,1);
        String time2 = commonMethods.updateMilitaryTimeMinutes(updatedMilitaryTimeHours,60);
        String time3 = commonMethods.updateMilitaryTimeMinutes(updatedMilitaryTimeHours,45);

        System.out.println("ORIGINAL TIME: " + time);
        System.out.println("MILITARY TIME: " + militaryTime);
        System.out.println("UPDATED MILITARY TIME WITH HOUR OFFSET: " + updatedMilitaryTimeHours);
        System.out.println("TIME1 MINUTE OFFSET: " + time1);
        System.out.println("TIME2 MINUTE OFFSET: " + time2);
        System.out.println("TIME3 MINUTE OFFSET: " + time3);


    }

}

