package stepdef;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.common.CommonMethods;
import com.tigertext.automation.enums.ForumActionOptions;
import com.tigertext.automation.pageObjects.ForumScreen;
import com.tigertext.automation.pageObjects.MessagingScreen;
import com.util.WebDriverController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Forums {
    MobileAPI mobileWebdriver = new MobileAPI();
    CommonMethods commonMethods = new CommonMethods();
    MessagingScreen messagingPO = new MessagingScreen(WebDriverController.appium_driver);
    ForumScreen forumsPO = new ForumScreen(WebDriverController.appium_driver);
    Logger logger = Logger.getLogger(Forums.class.getName());
    ForumActionOptions forumActionOptions;

    @Given("^I navigate to the Forums page$")
    public void iNavigateToTheForumsPage() throws Throwable {
        mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getGroupsTab(),60);
        messagingPO.getGroupsTab().click();
        mobileWebdriver.waitForPageObjectToBeClickable(forumsPO.getGroupsForumTvRooms());
        forumsPO.getGroupsForumTvRooms().click();
    }

    @Given("^I should see all forum sections$")
    public void iShouldSeeAllForumSections() throws Throwable {
        mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(
                ForumScreen.DYNAMICFORUMSTABPATH,"FORUMTAB","Forums"),15);
        MobileElement forumTabExplore = mobileWebdriver.returnDynamicElement(
                ForumScreen.DYNAMICFORUMSTABPATH,"FORUMTAB","Explore");
        MobileElement forumTabMyForums = mobileWebdriver.returnDynamicElement(
                ForumScreen.DYNAMICFORUMSTABPATH,"FORUMTAB","My Forums");
        mobileWebdriver.assertPageObjectIsPresent(forumTabExplore);
        mobileWebdriver.assertPageObjectIsPresent(forumTabMyForums);
    }

    @Given("^I create a new forum$")
    public void iCreateANewForum() throws Throwable {
        mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getComposeNewMessageButton());
        messagingPO.getComposeNewMessageButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(forumsPO.getNewForumButton());
        forumsPO.getNewForumButton().click();
    }

    @And("^I enter forum topic title \"([^\"]*)\" and forum description \"([^\"]*)\"$")
    public void iEnterForumTitleAndDescription(String forumTitle, String forumDescription) throws Throwable {
        String fTitle = "";
        if("random".equals(forumTitle.toLowerCase())) {
            fTitle = mobileWebdriver.returnRandomText();
        }
        else {
            fTitle = forumTitle;
        }
        mobileWebdriver.data.addDictionary("forumName",fTitle);
        mobileWebdriver.waitForPageObjectToBeClickable(forumsPO.getNewForumEditName());
        forumsPO.getNewForumEditName().sendKeys(fTitle);
        forumsPO.getNewForumDescriptionName().click();
        forumsPO.getNewForumDescriptionName().sendKeys(forumDescription);
        mobileWebdriver.waitForPageObjectToBeClickable(forumsPO.getNewForumCompleteButton());
        forumsPO.getNewForumCompleteButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationGroupName(),15);
        messagingPO.getConversationGroupName().click();
        mobileWebdriver.navigateBack();
    }

    @When("^I search for the forum$")
    public void iSearchForTheForum() throws Throwable {
        String forumName = mobileWebdriver.data.getDictionary("forumName");
        int swipeCounter = 15;
        boolean forumFound = false;
        mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(
                ForumScreen.DYNAMICFORUMSTABPATH,"FORUMTAB","Explore"),15);
        for(int i = 0; i <swipeCounter; i++)
        {
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getContactDisplayName(),20);
            try{
                mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(forumsPO.DYNAMICFORUMSTABPATH,"FORUMTAB",forumName));
                forumFound = true;
            }catch (Exception e){
                mobileWebdriver.swipeDirection("up");
            }
            if(forumFound)
                break;
        }
    }

    @Then("^The forum is (not )?displayed$")
    public void theForumIsDisplayed(String state) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        String forumName = mobileWebdriver.data.getDictionary("forumName");
        MobileElement forumElement;
        if(scriptState) {
            mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(
                    ForumScreen.DYNAMICFORUMSTABPATH,"FORUMTAB","Explore"),15);
            Assert.assertTrue(commonMethods.assertTextIsInElementList(forumsPO.getForumTitles(),forumName));
        }else
            Assert.assertFalse(commonMethods.assertTextIsInElementList(forumsPO.getForumTitles(),forumName));
    }

    @Then("^I navigate to the \"([^\"]*)\" section$")
    public void iNavigateToTheForumSection(String forumSection) throws Throwable {
        try {
            iNavigateToTheForumsPage();
        } catch (Exception e){
            logger.log(Level.INFO,e.toString());
        }
        if ("my forums".equals(forumSection.toLowerCase())) {
            mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(ForumScreen.DYNAMICFORUMSTABPATH, "FORUMTAB", "My Forums"), 60);
            mobileWebdriver.returnDynamicElement(ForumScreen.DYNAMICFORUMSTABPATH, "FORUMTAB", "My Forums").click();
        }
        else {
            mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(ForumScreen.DYNAMICFORUMSTABPATH, "FORUMTAB", "Explore"), 60);
            mobileWebdriver.returnDynamicElement(ForumScreen.DYNAMICFORUMSTABPATH, "FORUMTAB", "Explore").click();
        }
    }

    @Then("^I join a forum$")
    public void iJoinAForum() throws Throwable {
        int swipeCounter = 14;
        boolean forumFound = false;
        mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(
                ForumScreen.DYNAMICFORUMSTABPATH,"FORUMTAB","Forums"),15);
        for(int i = 0; i <swipeCounter; i++) {
            try {
                List<MobileElement> listOfForums = forumsPO.getForumTitles();
                List<MobileElement> listOfJoinButtons = forumsPO.getForumJoinButtons();
                //get location of join button
                int locationOfForum = commonMethods.returnLocationOfElementInList(listOfJoinButtons, "Join");
                logger.info("total number of join/joined buttons"+ listOfJoinButtons.size());
                logger.info("location of the join button: " + locationOfForum);

                mobileWebdriver.waitForPageObjectToBeClickable(forumsPO.getForumJoinButtons().get(locationOfForum), 60);

                //get name of forum to join based on the location of the join button above (it will always join the Celebrations forum unless new forums are added in which forumName will have to be updated)
                //the selection of the forum name is incosistent depending on the scroll will have to come back and play around with it
//                String nameOfForumJoined = mobileWebdriver.returnText(listOfForums.get(locationOfForum));
//                logger.info("total number of forums"+ listOfForums.size());
//                logger.info("name of forum joined:" + nameOfForumJoined);

                mobileWebdriver.data.addDictionary("forumName", "Celebrations");


                String userLoggedIn = mobileWebdriver.data.getDictionary("messageSender");
                forumsPO.getForumJoinButtons().get(locationOfForum).click();
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationGroupName(), 60);
                messagingPO.getConversationGroupName().click();
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationGroupName(),60);
                //mobileWebdriver.assertTextIsPresent(messagingPO.getConversationGroupName(), nameOfForumJoined);
                //commonMethods.verifyCorrectBangMessageIsDisplayed(userLoggedIn,"join");
                mobileWebdriver.navigateBack();
                forumFound = true;
            }catch (AssertionError e ){commonMethods.scrollUsingCoords(100,1222,100,500);}

            if(forumFound)
                break;
        }
    }

    @Then("^I am able to \"([^\"]*)\" a forum$")
    public void iAmAbleToJoinAForum(String forumAction) throws Throwable {
        if("join".equals(forumAction.toLowerCase())){
            mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(
                    ForumScreen.DYNAMICFORUMSTABPATH,"FORUMTAB","Explore"),15);
            theForumIsDisplayed("true");
            commonMethods.assertTextIsInElementList(forumsPO.getForumJoinButtons(),"Joined");
            iNavigateToTheForumSection("my forums");
            mobileWebdriver.waitForPageObjectToBeClickable(forumsPO.getForumTitle(),30);
            List<MobileElement> joinedForums = forumsPO.getForumTitles();
            for(MobileElement joinedForum:joinedForums) {
                String forumName = mobileWebdriver.returnText(joinedForum);
                if(!"Auto QA Forum".equals(forumName) && !"Auto Change Forum".equals(forumName) && !"Auto QA Leave Forum".equals(forumName)) {
                   mobileWebdriver.data.addDictionary("forumName",forumName);
                   iSelectTheOptionFromTheSelectActionMenu("Leave");
                } else { continue;}
            }
        }
        else{
            mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(
                    ForumScreen.DYNAMICFORUMSTABPATH,"FORUMTAB","Explore"),60);
            List<MobileElement> listOfForums = forumsPO.getForumTitles();
            List<MobileElement> listOfJoinButtons = forumsPO.getForumJoinButtons();
            String nameOfForumUserLeft = mobileWebdriver.data.getDictionary("forumName");
            int forumLocation = commonMethods.returnLocationOfElementInList(listOfForums,nameOfForumUserLeft);
            mobileWebdriver.waitForPageObjectToBeClickable(listOfJoinButtons.get(forumLocation),60);
            mobileWebdriver.assertTextIsPresent(listOfJoinButtons.get(forumLocation), "Join");
        }
    }

    @And("^I select the \"([^\"]*)\" option from the Select Action menu$")
    public void iSelectTheOptionFromTheSelectActionMenu(String actionOption) throws Throwable {
        OpenForumLongPressMenu();
        selectMyForumsActionOption(actionOption);
        // add whats below after connectivity issues for forum are fixed for Android
        // mobileWebdriver.waitForPageObjectToBeClickable(forumsPO.getForumSnackBarText(),20);
        // mobileWebdriver.assertTextIsPresent(forumsPO.getForumSnackBarText(),"Conversation deleted");
    }

    public void OpenForumLongPressMenu() throws Throwable {
        mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getContactDisplayName(),60);
        mobileWebdriver.waitForPageObjectToBeClickable(forumsPO.getForumTitle(),60);
        List<MobileElement> listOfForums = forumsPO.getForumTitles();
        String nameOfForum = mobileWebdriver.data.getDictionary("forumName");
        logger.info("forumName: " + nameOfForum);
        int locationOfForum = commonMethods.returnLocationOfElementInList(listOfForums,nameOfForum);
        mobileWebdriver.data.addDictionary("forumLocation",Integer.toString(locationOfForum));
        mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getContactDisplayNames().get(locationOfForum),60);
        mobileWebdriver.longPressElement(messagingPO.getContactDisplayNames().get(locationOfForum));
    }

    @When("^I mute the conversation for \"([^\"]*)\"$")
    public void iMuteConversation(String duration) throws Throwable {
        selectForumAlertOption(duration);
    }

    @Then("^I should see the conversation is \"([^\"]*)\"$")
    public void iShouldSeeTheConverstaionIsMutedUnmuted(String option) throws Throwable {
        int locationOfForum = Integer.parseInt(mobileWebdriver.data.getDictionary("forumLocation"));
        mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(
                ForumScreen.DYNAMICFORUMSTABPATH,"FORUMTAB","Forums"),20);
        mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getContactDisplayNames().get(locationOfForum), 30);
        if ("muted".equals(option.toLowerCase())) {
            mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationMuteIcons().get(0), 50);
            mobileWebdriver.assertPageObjectIsPresent(messagingPO.getConversationMuteIcons().get(0));
        }
        else {
            mobileWebdriver.waitForPageObjectToBeClickable(forumsPO.getForumAvatarImage().get(locationOfForum), 50);
            mobileWebdriver.assertPageObjectIsNotPresent(messagingPO.getConversationMuteIcon());
            //Assert.assertNull(messagingPO.getConversationMuteIcons());
        }
    }

    @When("^Selected user \"([^\"]*)\" was \"([^\"]*)\" to the forum by the user$")
    public void iSelectUserToJoinTheForum(String user, String state) throws Throwable {
        //commonMethods.verifyCorrectBangMessageIsDisplayed(user,state);
        mobileWebdriver.navigateBack();
        mobileWebdriver.navigateBack();
        iLeaveAnExistingForum();
    }

    @When ("^I select forum alert \"([^\"]*)\" option$")
    public void selectForumAlertOption(String forumAlertOption) throws Throwable{
        mobileWebdriver.waitForPageObjectToBeClickable(forumsPO.getForumMyForumsSelectActionTitle(),60);
        MobileElement alertOption;

        //prepares forum to be muted or unmuted by doing the following:
        //if we unmuting, it makes sure that is not already unmuted and if it is it mutes it
        //if we are muting, it makes sure that is not already muted and it it is it unmutes it
        if("unmute".equals(forumAlertOption.toLowerCase()))
        {
            try{
                mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE",forumAlertOption),60);
                alertOption = mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE",forumAlertOption);
                alertOption.click();
            }catch(Exception e){
                mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE","Mute"),60);
                alertOption = mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE","Mute");
                alertOption.click();
                iMuteConversation("15 Minutes");
                iShouldSeeTheConverstaionIsMutedUnmuted("muted");
                iSelectTheOptionFromTheSelectActionMenu("Unmute");
            }
        }
        else if ("mute".equals(forumAlertOption.toLowerCase()))
        {
            try{
                mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE",forumAlertOption),60);
                alertOption = mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE",forumAlertOption);
                alertOption.click();
            }catch(Exception e){
                mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE","Unmute"),60);
                alertOption = mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE","Unmute");
                alertOption.click();
                OpenForumLongPressMenu();
                mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE",forumAlertOption),60);
                alertOption = mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE",forumAlertOption);
                alertOption.click();
            }

        }
        else
        {
            alertOption = mobileWebdriver.returnDynamicElement(forumsPO.SELECTACTIONLABLEPATH,"ACTIONLABLE",forumAlertOption);
            alertOption.click();
        }

    }

    @When ("^I select forum action \"([^\"]*)\" option$")
    public void selectMyForumsActionOption(String actionOption) throws Throwable{
        forumActionOptions = ForumActionOptions.valueOf(actionOption.replace(" ", "").toUpperCase());
        selectForumAlertOption(actionOption);
        switch(forumActionOptions){
            case LEAVE:
                mobileWebdriver.waitForPageObjectToBeClickable(messagingPO.getConversationSettingsDialogLeaveOptionButton(),60);
                messagingPO.getConversationSettingsDialogLeaveOptionButton().click();
                break;
            case ADDMEMBERS:
                commonMethods.addMemberToConversation("Android3");
                mobileWebdriver.waitForElementAndClick(messagingPO.getConversationGroupName(),60);
                break;
            case MARKALLASREAD:
                break;
            case MUTE:
                break;
            case UNMUTE:
                break;
            default:
                break;
        }
    }

    @When ("^I have an existing forum \"([^\"]*)\"$")
    public void iHaveAnExisitingForum(String forumName) throws Throwable{
        mobileWebdriver.data.addDictionary("forumName",forumName);
        commonMethods.selectExistingMessageRecepient(forumName);
    }

    @Then ("^I leave existing forum$")
    public void iLeaveAnExistingForum() throws Throwable{
        iNavigateToTheForumSection("my forums");
        iSearchForTheForum();
        iSelectTheOptionFromTheSelectActionMenu("Leave");
    }
}
