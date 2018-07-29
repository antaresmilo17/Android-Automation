package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginScreen {
    public LoginScreen(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String USERNAMETEXTFIELDPATH = "new UiSelector().resourceId(\"com.tigertext:id/s4_regflow_edit_txt_username_edit\")";
    public static final String FOUNDMATCHUSERNAMETEXTFIELDPATH = "new UiSelector().resourceId(\"com.tigertext:id/regflow_account_list_entry_select_text_account\")";
    public static final String GETSTARTEDBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/regflow_loading_getstarted_button\")";
    public static final String USERPASSWORDTEXTFIELDPATH = "new UiSelector().resourceId(\"com.tigertext:id/s6_regflow_edit_txt_password_edit\")";
    public static final String USERPASSWORDTEXTFIELDPATH2 = "new UiSelector().resourceId(\"com.tigertext:id/s7_regflow_edit_txt_password_edit\")";
    public static final String NEXTBUTTONPATH = "Next";
    public static final String PASSWORDNEXTBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/s6_regflow_btn_login\")";
    public static final String PASSWORDNEXTBUTTONPATH2 = "new UiSelector().resourceId(\"com.tigertext:id/s7_regflow_btn_continue\")";
    public static final String ENVIRONMENTLABLEPATH = "//*[@class='android.widget.Spinner']";
    public static final String ALLOWALERTBUTTONPATH = "new UiSelector().resourceId(\"com.android.packageinstaller:id/permission_allow_button\")";
    public static final String ACTIVATEBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/s8_regflow_btn_start\")";
    public static final String VERIFYYOUREMAILBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/template_header\")";

    public static final String NOTNOWBUTTONPATH = "Not Now";
    public static final String LETSCHECKBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/lets_check_button\")";
    public static final String INBOXTABPATH = ".//*[@text='Inbox']";
    public static final String GROUPSTABPATH = ".//*[@text='Groups']";
    public static final String OKALERTBUTTONPATH = "new UiSelector().resourceId(\"android:id/button1\")";
    public static final String ANDROIDSETTINGSBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/inbox_toolbar_settings_tt\")";
    public static final String ANDROIDLOGOUTBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/settings_logout_area\")";
    public static final String ANDROIDHELPBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/settings_help_area\")";
    public static final String ANDROIDLOGOUTBUTTONALERTPATH = "new UiSelector().resourceId(\"android:id/button1\")";
    public static final String ANDROIDCONTACTSBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/settings_contacts_area\")";
    public static final String ANDROIDSECURITYBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/settings_security_area\")";
    public static final String STARTMESSAGINGBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/s9a_regflow_btn_next\")";
    public static final String STARTMESSAGINGBUTTONPATH2 = "new UiSelector().resourceId(\"com.tigertext:id/s9_regflow_btn_next\")";

    public static final String FORGOTPASSWORDLINKPATH = "new UiSelector().resourceId(\"com.tigertext:id/s6_regflow_btn_forgotpaswd\")";
    public static final String RESENDPASSWORDLINKPATH = "new UiSelector().resourceId(\"com.tigertext:id/resend_reset_password\")";
    public static final String SETPASSWORDTITLEPATH = ".//*[@text='Set Password']";
    public static final String LOGINFAILEDERRORPATH = "new UiSelector().resourceId(\"android:id/message\")";
    public static final String LOGINFAILEDOKBUTTONPATH = "new UiSelector().resourceId(\"android:id/button1\")";
    public static final String CREATEAPASSWORDTEXTFIELDPATH = "new UiSelector().resourceId(\"com.tigertext:id/s7_regflow_edit_txt_password_edit\")";
    public static final String NONEOFTHEABOVEGOOGLESMARTLOCKOPTIONPATH = ".//*[@text='NONE OF THE ABOVE']";
    public static final String PRIVATEPOLICYANDTERMSFOOTERPATH = "new UiSelector().resourceId(\"com.tigertext:id/s7_regflow_privacy_policy_terms_text\")";
    public static final String FORGOTPASSWORDCHECKDETAILSTEXTPATH = "new UiSelector().resourceId(\"com.tigertext:id/reset_password_explanation\")";
    public static final String SERVERSELECTUSPATH = ".//*[@text='USA']";

    public static final String SERVERSELECTSINGAPOREPATH = ".//*[@text='Singapore']";
    public static final String USERCORPORATELOGINLOGINBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/login\")";
    public static final String ONELOGINEMAILTEXTFIELDPATH = "new UiSelector().resourceId(\"user_email_input\")";
    public static final String ONELOGINPASSWORDTEXTFIELDPATH = "new UiSelector().resourceId(\"user_password_input\")";
    public static final String ONELOGINLOGINBUTTONPATH = "new UiSelector().resourceId(\"user_submit\")";
    public static final String ONELOGINLOGOPATH = "new UiSelector().resourceId(\"login-logo\")";
    public static final String CANCELSERVERSELECTBUTTONPATH = "new UiSelector().resourceId(\"android:id/button2\")";
    public static final String CONVERSATIONINBOXCONTAINERPATH = "new UiSelector().resourceId(\"com.tigertext:id/inbox_row_container\")";
    public static final String SETTINGSBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/inbox_settings\")";
    public static final String SETTINGSINBOXBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/inbox_toolbar_settings_tt\")";
    public static final String USEDIFFERENTACCOUNTLINKPATH = "new UiSelector().resourceId(\"com.tigertext:id/s5_regflow_btn_use_another_account\")";
    public static final String WORKUSERNAMELINKPATH = "new UiSelector().resourceId(\"com.tigertext:id/s3_regflow_btn_use_work_username\")";
    public static final String REENTERPASSWORDPATH = "new UiSelector().resourceId(\"com.tigertext:id/s7_regflow_edit_txt_password_confirm_edit\")";

    public static final String SETUPPROFILEFIRSTNAMETEXTFIELDPATH = "new UiSelector().resourceId(\"com.tigertext:id/s9_regflow_edit_txt_first_name_edit\")";
    public static final String SETUPPROFILELASTNAMETEXTFIELDPATH = "new UiSelector().resourceId(\"com.tigertext:id/s9_regflow_edit_txt_last_name_edit\")";
;

    @AndroidFindAll({@AndroidBy(uiAutomator = GETSTARTEDBUTTONPATH)})
    private MobileElement getStartedButton;
    @AndroidFindAll({@AndroidBy(uiAutomator = SETTINGSINBOXBUTTONPATH), @AndroidBy(uiAutomator = SETTINGSBUTTONPATH)})
    private MobileElement settingsButtonPath;
    @AndroidFindBy(uiAutomator = USERNAMETEXTFIELDPATH)
    private MobileElement userNameTextField;
    @AndroidFindAll({@AndroidBy(uiAutomator= USERPASSWORDTEXTFIELDPATH),@AndroidBy(uiAutomator= USERPASSWORDTEXTFIELDPATH2)})
    private MobileElement userPasswordTextField;
    @AndroidFindBy(uiAutomator= REENTERPASSWORDPATH)
    private MobileElement reEnterPasswordPath;
    @AndroidFindBy(accessibility= NEXTBUTTONPATH)
    private MobileElement nextButton;
    @AndroidFindAll({@AndroidBy(uiAutomator= PASSWORDNEXTBUTTONPATH), @AndroidBy(uiAutomator= PASSWORDNEXTBUTTONPATH2)})
    private MobileElement passwordNextButton;
    @AndroidFindBy(xpath = ENVIRONMENTLABLEPATH)
    private MobileElement environmentLabel;
    @AndroidFindBy(uiAutomator= ALLOWALERTBUTTONPATH)
    private MobileElement allowAlertButton;
    @AndroidFindBy(uiAutomator= ACTIVATEBUTTONPATH)
    private MobileElement activateButton;

    @AndroidFindBy(uiAutomator= VERIFYYOUREMAILBUTTONPATH)
    private MobileElement verifyYourEmailButton;
    @AndroidFindBy(accessibility= NOTNOWBUTTONPATH)
    private MobileElement notNowButton;
    @AndroidFindBy(uiAutomator= LETSCHECKBUTTONPATH)
    private MobileElement letsCheckButton;
    @AndroidFindBy(uiAutomator= USEDIFFERENTACCOUNTLINKPATH)
    private MobileElement useDifferentAccountLink;
    @AndroidFindBy(uiAutomator= WORKUSERNAMELINKPATH)
    private MobileElement workUsernameLink;

    @AndroidFindBy(xpath= INBOXTABPATH)
    private MobileElement inboxTab;
    @AndroidFindBy(xpath= GROUPSTABPATH)
    private MobileElement groupsTab;
    @AndroidFindBy(uiAutomator= OKALERTBUTTONPATH)
    private MobileElement okAlertButton;
    @AndroidFindBy(uiAutomator= ANDROIDSETTINGSBUTTONPATH)
    private MobileElement androidSettingsButton;
    @AndroidFindBy(uiAutomator= ANDROIDLOGOUTBUTTONPATH)
    private MobileElement androidLogOutButton;
    @AndroidFindBy(uiAutomator= ANDROIDLOGOUTBUTTONALERTPATH)
    private MobileElement androidLogOutAlertButton;
    @AndroidFindBy(uiAutomator= ANDROIDCONTACTSBUTTONPATH)
    private MobileElement androidContactsButton;
    @AndroidFindBy(uiAutomator= ANDROIDHELPBUTTONPATH)
    private MobileElement androidHelpButton;
    @AndroidFindBy(uiAutomator= ANDROIDSECURITYBUTTONPATH)
    private MobileElement androidSecurityButton;
    @AndroidFindAll({@AndroidBy(uiAutomator= STARTMESSAGINGBUTTONPATH),@AndroidBy(uiAutomator= STARTMESSAGINGBUTTONPATH2)})
    private MobileElement startMessagingButton;
    @AndroidFindBy(uiAutomator= FORGOTPASSWORDLINKPATH)
    private MobileElement forgotPassworkLink;

    @AndroidFindBy(uiAutomator= RESENDPASSWORDLINKPATH)
    private MobileElement resendPassworkLink;
    @AndroidFindBy(xpath= SETPASSWORDTITLEPATH)
    private MobileElement setPasswordTitle;
    @AndroidFindBy(uiAutomator= LOGINFAILEDERRORPATH)
    private MobileElement loginFailedError;
    @AndroidFindBy(uiAutomator= LOGINFAILEDOKBUTTONPATH)
    private MobileElement loginFailedOkButton;
    @AndroidFindBy(uiAutomator= CREATEAPASSWORDTEXTFIELDPATH)
    private MobileElement createAPasswordTextField;
    @AndroidFindBy(xpath= NONEOFTHEABOVEGOOGLESMARTLOCKOPTIONPATH)
    private MobileElement noneOfTheAboveGoogleSmartLockOption;
    @AndroidFindBy(uiAutomator= PRIVATEPOLICYANDTERMSFOOTERPATH)
    private MobileElement privacyPolicyAndTermsFooter;
    @AndroidFindBy(uiAutomator= FORGOTPASSWORDCHECKDETAILSTEXTPATH)
    private MobileElement forgotPassworkCheckDetailsText;
    @AndroidFindBy(xpath= SERVERSELECTUSPATH)
    private MobileElement serverSelectUS;
    @AndroidFindBy(xpath= SERVERSELECTSINGAPOREPATH)
    private MobileElement serverSelectSingapore;

    @AndroidFindBy(uiAutomator= USERCORPORATELOGINLOGINBUTTONPATH)
    private MobileElement useCorporateLoginLoginButton;
    @AndroidFindBy(uiAutomator= ONELOGINLOGOPATH)
    private MobileElement oneLogInLogoIcon;
    @AndroidFindBy(uiAutomator = ONELOGINEMAILTEXTFIELDPATH)
    private MobileElement oneLoginEmailTextField;
    @AndroidFindBy(uiAutomator= ONELOGINPASSWORDTEXTFIELDPATH)
    private MobileElement oneLoginPasswordTextField;
    @AndroidFindBy(uiAutomator= ONELOGINLOGINBUTTONPATH)
    private MobileElement oneLoginLogInButton;
    @AndroidFindBy(uiAutomator= CANCELSERVERSELECTBUTTONPATH)
    private MobileElement cancelServerSelectButton;
    @AndroidFindBy(uiAutomator= CONVERSATIONINBOXCONTAINERPATH)
    private MobileElement conversationInboxContainer;
    @AndroidFindBy(uiAutomator= SETUPPROFILEFIRSTNAMETEXTFIELDPATH)
    private MobileElement setUpProfileFirstNameTextFieldPath;
    @AndroidFindBy(uiAutomator= SETUPPROFILELASTNAMETEXTFIELDPATH)
    private MobileElement setUpProfileLastNameTextFieldPath;

    public MobileElement getOneLogInLogoIcon() {
        return oneLogInLogoIcon;
    }
    public MobileElement getWorkUsernameLink() {
        return workUsernameLink;
    }
    public MobileElement getLetsCheckButton() {
        return letsCheckButton;
    }
    public MobileElement getSettingsButtonPath() {
        return settingsButtonPath;
    }
    public MobileElement getVerifyYourEmailButton() {
        return verifyYourEmailButton;
    }
    public MobileElement getUserPasswordTextField() {
        return userPasswordTextField;
    }
    public MobileElement getUserNameTextField() {
        return userNameTextField;
    }
    public MobileElement getOkAlertButton() {
        return okAlertButton;
    }
    public MobileElement getAndroidLogOutAlertButton() {
        return androidLogOutAlertButton;
    }
    public MobileElement getPasswordNextButton() {
        return passwordNextButton;
    }
    public MobileElement getAndroidLogOutButton() {
        return androidLogOutButton;
    }
    public MobileElement getStartMessagingButton() {
        return startMessagingButton;
    }
    public MobileElement getAndroidContactsButton() {
        return androidContactsButton;
    }

    public MobileElement getSetUpProfileFirstNameTextFieldPath() {
        return setUpProfileFirstNameTextFieldPath;
    }
    public MobileElement getSetUpProfileLastNameTextFieldPath() {
        return setUpProfileLastNameTextFieldPath;
    }
    public MobileElement getAndroidSettingsButton() {
        return androidSettingsButton;
    }
    public MobileElement getUseCorporateLoginLoginButton() {
        return useCorporateLoginLoginButton;
    }
    public MobileElement getAndroidSecurityButton() {
        return androidSecurityButton;
    }
    public MobileElement getNotNowButton() {
        return notNowButton;
    }
    public MobileElement getNextButton() {
        return nextButton;
    }
    public MobileElement getInboxTab() {
        return inboxTab;
    }
    public MobileElement getGroupsTab() {
        return groupsTab;
    }
    public MobileElement getGetStartedButton() {
        return getStartedButton;
    }
    public MobileElement getEnvironmentLabel() {
        return environmentLabel;
    }
    public MobileElement getAllowAlertButton() {
        return allowAlertButton;
    }

    public MobileElement getForgotPassworkLink() {
        return forgotPassworkLink;
    }
    public MobileElement getActivateButton() {
        return activateButton;
    }
    public MobileElement getSetPasswordTitle() {
        return setPasswordTitle;
    }
    public MobileElement getServerSelectUS() {
        return serverSelectUS;
    }
    public MobileElement getServerSelectSingapore() {
        return serverSelectSingapore;
    }
    public MobileElement getResendPassworkLink() {
        return resendPassworkLink;
    }
    public MobileElement getPrivacyPolicyAndTermsFooter() {
        return privacyPolicyAndTermsFooter;
    }
    public MobileElement getOneLoginPasswordTextField() {
        return oneLoginPasswordTextField;
    }
    public MobileElement getOneLoginLogInButton() {
        return oneLoginLogInButton;
    }
    public MobileElement getOneLoginEmailTextField() {
        return oneLoginEmailTextField;
    }

    public MobileElement getNoneOfTheAboveGoogleSmartLockOption() {
        return noneOfTheAboveGoogleSmartLockOption;
    }
    public MobileElement getLoginFailedOkButton() {
        return loginFailedOkButton;
    }
    public MobileElement getLoginFailedError() {
        return loginFailedError;
    }
    public MobileElement getForgotPassworkCheckDetailsText() {
        return forgotPassworkCheckDetailsText;
    }
    public MobileElement getCreateAPasswordTextField() {
        return createAPasswordTextField;
    }
    public MobileElement getConversationInboxContainer() {
        return conversationInboxContainer;
    }
    public MobileElement getCancelServerSelectButton() {
        return cancelServerSelectButton;
    }
    public MobileElement getUseDifferentAccountLink() {
        return useDifferentAccountLink;
    }
    public MobileElement getReEnterPasswordPath() {
        return reEnterPasswordPath;
    }
    public MobileElement getAndroidHelpButton() {
        return androidHelpButton;
    }
}
