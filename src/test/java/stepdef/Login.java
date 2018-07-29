package stepdef;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.common.StaticData;
import com.tigertext.automation.pageObjects.LoginScreen;
import com.tigertext.automation.pageObjects.MessagingScreen;
import com.util.WebDriverController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.tigertext.automation.config.TestConfig;
import com.tigertext.automation.common.CommonMethods;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.internal.Coordinates;

import java.util.List;
import java.util.logging.Logger;

import static com.tigertext.automation.common.StaticData.errorMessageStaticData;

public class Login {
    MobileAPI mobileWebdriver = new MobileAPI();
    LoginScreen loginPO = new LoginScreen(WebDriverController.appium_driver);
    CommonMethods commonMethods = new CommonMethods();
    MessagingScreen messagingPO = new MessagingScreen(WebDriverController.appium_driver);
    Logger logger = Logger.getLogger(Login.class.getName());

    String email = "";
    String password = "";

    @Given("^I am on the TT get started screen$")
    public void onTheTTGetStartedScreen() throws Throwable {
        try {
            mobileWebdriver.waitForElementAndClick(loginPO.getGetStartedButton(), 30);
        }catch (Exception e)
        {
            logger.info("ALREADY LOGGED IN");
        }
    }

    @Given("^I select the \"([^\"]*)\" find Org button$")
    public void iSelectTheFindOrgButton(String buttonName) throws Throwable {
        try {
            if ("not now".equals(buttonName.toLowerCase()))
                mobileWebdriver.waitForElementAndClick(loginPO.getNotNowButton(), 25);
            else {
                mobileWebdriver.waitForElementAndClick(loginPO.getLetsCheckButton(), 25);
                try {
                    mobileWebdriver.waitForElementAndClick(loginPO.getAllowAlertButton(), 25);
                } catch (Exception e) {
                }
            }
        }catch (Exception e)
        {
            logger.info("ALREADY LOGGED IN");
        }
    }

    @Given("^I (do not )?enter new credentials$")
    public void iEnterNewCredentials(String state) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        String user = mobileWebdriver.returnRandomText();
        String email = user + StaticData.endEmailString;
        String password = StaticData.defaultPassword;
        mobileWebdriver.data.addDictionary("userType", user);
        mobileWebdriver.data.addDictionary("email", email);
        mobileWebdriver.data.addDictionary("password", password);

        if(scriptState) {
            commonMethods.enterEmail(email);
            commonMethods.createPassword(password);
        }
    }

    @Given("^I select \"([^\"]*)\" link$")
    public void iSelectFoundMatchLink(String linkName) throws Throwable {
        if("different account".equals(linkName)) {
            try {
                mobileWebdriver.waitForElementAndClick(loginPO.getUseDifferentAccountLink(), 25);
            }catch (Exception e){}
        }
        else
            mobileWebdriver.waitForElementAndClick(loginPO.getWorkUsernameLink());
    }

    @Given("^I am logged in as \"([^\"]*)\"$")
    public void iamLoggedInAsUser(String user) throws Throwable {
        try {
            mobileWebdriver.data.addDictionary("messageSender", user);
            onTheTTGetStartedScreen();
            iSelectTheFindOrgButton("Not Now");
            iEnterCredentialsForUser("true", user);
            iAcceptTheNotificationAlerts();
            iCloseTheRateApp();
            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getInboxTab(), 30);
            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getConversationInboxContainer(), 25);
            loginPO.getGroupsTab().click();
            mobileWebdriver.assertPageObjectIsPresent(loginPO.getInboxTab());
            loginPO.getInboxTab().click();
        }catch (Exception e)
        {
            logger.info("ALREADY LOGGED IN");

        }
    }

    @Given("^I (do not )?enter the valid credentials for \"([^\"]*)\"$")
    public void iEnterCredentialsForUser(String state, String user) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);

        try {

            if (scriptState) {
                email = TestConfig.Environment.getUserEmail(user);
                password = TestConfig.Environment.getUserPassword(user);
            } else {
                if ("invalid password".equals(user)) {
                    email = TestConfig.Environment.getEmail();
                    password = TestConfig.Environment.getInvalidUserPassword(user);
                } else
                    email = TestConfig.Environment.getInvalidUserEmail(user);
            }
            mobileWebdriver.data.addDictionary("userType", user);
            mobileWebdriver.data.addDictionary("email", email);
            mobileWebdriver.data.addDictionary("password", password);
            commonMethods.enterEmail(email);

            if (scriptState) {
                commonMethods.enterPassword(password);
            }
        }catch (Exception e)
        {
            logger.info("ALREADY LOGGED IN");

        }
    }

    @Given("^I (do not )?enter the original password for user \"([^\"]*)\" by closing the forgot password page$")
    public void iEnterTheOriginalPasswordByClosingForgotPassword(String state, String user) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);

        if(scriptState)
            password = TestConfig.Environment.getUserPassword(user);
        else
            password = TestConfig.Environment.getInvalidPassword();

        mobileWebdriver.data.addDictionary("password", password);
        //Hits back to dismiss the keyboard and then navigates back to the previous page.
        WebDriverController.appium_driver.navigate().back();
        WebDriverController.appium_driver.navigate().back();
        commonMethods.enterPassword(password);
    }

    @And("^I click the forgot password link$")
    public void iClickTheForgotPassworkLinks() throws Throwable {
        mobileWebdriver.waitForElementAndClick(loginPO.getForgotPassworkLink(),60);
    }

    @And("^I (do not )?enter the valid email address for \"([^\"]*)\"$")
    public void iEnterTheEmailForUser(String state, String user) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);

        if(scriptState){
            email = TestConfig.Environment.getUserEmail(user);
        }
        mobileWebdriver.data.addDictionary("userType", user);
        mobileWebdriver.data.addDictionary("email", email);
        commonMethods.enterEmail(email);
    }

    @When("^I accept the notification alerts$")
    public void iAcceptTheNotificationAlerts(){
        try {
            commonMethods.acceptingTheNotificationAlerts();
        } catch (Exception e) {
            logger.info("ALREADY LOGGED IN");

        }
    }

    @When("^I Close the Rating Application$")
    public void iCloseTheRateApp(){
        try
        {
            logger.info("LOOKING FOR RATING APP");
            commonMethods.closeRatingApp();
        }
        catch (Exception e)
        {

            logger.info("RATING APP NOT DISPLAYED");

        }

    }

    @When("^I set up my profile$")
    public void iSetUpMyProfile()throws Throwable{
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getSetUpProfileFirstNameTextFieldPath(),25);
        loginPO.getSetUpProfileFirstNameTextFieldPath().sendKeys(StaticData.defaultFirstName);
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getSetUpProfileLastNameTextFieldPath(),25);
        loginPO.getSetUpProfileLastNameTextFieldPath().sendKeys(StaticData.defaultLastName);
        mobileWebdriver.navigateBack();
        loginPO.getStartMessagingButton().click();
    }

    @When("^I connect to the \"([^\"]*)\" server$")
    public void iConnectToTheServer(String serverName){
        commonMethods.selectServerConnection(serverName);
    }

    @When("^I go through the settings \"([^\"]*)\" process$")
    public void iGoThroughTheLogOutProcess(String settingsProcess)throws Throwable {
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getInboxTab(), 80);
        loginPO.getInboxTab().click();

        commonMethods.logOutOfApp();

    }


    @When("^I select the \"([^\"]*)\" settings option$")
    public void iSelectTheSettingsOption(String settingOption) throws  Throwable {
        commonMethods.searchForNameInPage(settingOption);
        List<MobileElement> optionList = messagingPO.getConversationGroupNames();
        int optionLocation = commonMethods.returnLocationOfElementInList(optionList,settingOption);
        optionList.get(optionLocation).click();
        mobileWebdriver.waitForElementAndClick(loginPO.getAndroidLogOutAlertButton());
    }

    @When("^I should see the get started screen$")
    public void iShouldSeeTheGetStarterdScreen() throws Throwable{
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getGetStartedButton(), 60);
        mobileWebdriver.assertPageObjectIsPresent(loginPO.getGetStartedButton());
    }

    @When("^I (do not )?go through the \"([^\"]*)\" authentication")
    public void iGoThroughTheAccountAuthentication(String state, String user) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);

        if (scriptState) {
            if ("SAML".equals(user)) {
                mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getUseCorporateLoginLoginButton(), 60);
                loginPO.getUseCorporateLoginLoginButton().click();
                commonMethods.enterOneLoginCredentials(user);
                iAcceptTheNotificationAlerts();

            } else if ("USA and Singapore".equals(user)) {
                commonMethods.selectServerConnection("USA");
                user = TestConfig.Environment.getUserPassword(user);
                commonMethods.enterPassword(user);
                commonMethods.acceptingTheNotificationAlerts();

            } else {
            }
        } else {
            if ("SAML".equals(user)) {
                mobileWebdriver.waitForElementAndClick(loginPO.getUseCorporateLoginLoginButton(), 15);
                commonMethods.enterOneLoginEmail(TestConfig.Environment.getInvalidEmail());
                commonMethods.enterOneLoginPassword(TestConfig.Environment.getInvalidPassword());

            } else if ("USA and Singapore".equals(user)) {
                commonMethods.selectServerConnection("cancel");

            } else {
            }
        }
    }

    //THENS
    @Then("^I should see the forgot password page$")
    public void iShouldSeeTheForgotPasswordPage() throws Throwable {
        String email = mobileWebdriver.data.getDictionary("email");
        String expectedText = "Please check " + email + " for details";
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getResendPassworkLink(),60);
        mobileWebdriver.assertPageObjectIsPresent(loginPO.getResendPassworkLink());
        mobileWebdriver.assertPageObjectIsPresent(loginPO.getSetPasswordTitle());
        mobileWebdriver.assertPageObjectIsPresent(loginPO.getForgotPassworkCheckDetailsText());
        mobileWebdriver.assertTextIsPresent(loginPO.getForgotPassworkCheckDetailsText(), expectedText);
    }

    @Then("^I should (not )?see the roster$")
    public void iShouldSeeTheRoster(String state) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        String userType = mobileWebdriver.data.getDictionary("userType");

        if (mobileWebdriver.elementExist(loginPO.getStartMessagingButton(), 5))
            loginPO.getStartMessagingButton().click();

        if(scriptState) {
            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getInboxTab(), 30);
            loginPO.getInboxTab().click();
            mobileWebdriver.assertPageObjectIsPresent(loginPO.getInboxTab());
        }else{
            if ("invalid user".equals(userType)) {
                mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getPrivacyPolicyAndTermsFooter(), 20);
                mobileWebdriver.assertPageObjectIsPresent(loginPO.getCreateAPasswordTextField());

            } else if ("invalid format".equals(userType)) {
                mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getLoginFailedError(), 20);
                mobileWebdriver.assertTextIsPresent(loginPO.getLoginFailedError(), errorMessageStaticData.get(userType));
                loginPO.getLoginFailedOkButton().click();

            } else if ("invalid password".equals(userType)) {
                commonMethods.enterPassword(mobileWebdriver.data.getDictionary("password"));
                mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getLoginFailedError(), 20);
                mobileWebdriver.assertTextIsPresent(loginPO.getLoginFailedError(), errorMessageStaticData.get(userType));
                loginPO.getLoginFailedOkButton().click();

            } else {
                mobileWebdriver.assertPageObjectIsNotPresent(loginPO.getInboxTab());

            }
        }
        //commonMethods.logOutOfApp();

    }
}