package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AppRatingPopUp {
    public AppRatingPopUp(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String RATINGAPPCLOSEBUTTON = "new UiSelector().resourceId(\"com.tigertext:id/close_rate_app\")";
    public static final String RATINGAPPYESBUTTON = "new UiSelector().resourceId(\"com.tigertext:id/satisfied_image\")";
    public static final String RATINGAPPNOBUTTON = "new UiSelector().resourceId(\"com.tigertext:id/dissatisfied_image\")";
    public static final String RATINGAPPMESSAGE = "new UiSelector().resourceId(\"com.tigertext:id/rate_our_app_message\")";


    @AndroidFindBy(uiAutomator= RATINGAPPCLOSEBUTTON)
    private MobileElement ratingAppCloseButton;
    @AndroidFindBy(uiAutomator= RATINGAPPYESBUTTON)
    private MobileElement ratingAppYesButton;
    @AndroidFindBy(uiAutomator= RATINGAPPNOBUTTON)
    private MobileElement ratingAppNoButton;
    @AndroidFindBy(uiAutomator= RATINGAPPMESSAGE)
    private MobileElement ratingAppMessageButton;

    public MobileElement getCloseRatingAppButton() {
        return ratingAppCloseButton;
    }
    public MobileElement getRatingAppMessageButton() {
        return ratingAppMessageButton;
    }
    public MobileElement getRatingAppYesButton() {
        return ratingAppYesButton;
    }
    public MobileElement getRatingAppNoButton() {
        return ratingAppNoButton;
    }

}
