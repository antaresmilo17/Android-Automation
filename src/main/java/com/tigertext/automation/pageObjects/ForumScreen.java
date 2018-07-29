package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ForumScreen {
    public ForumScreen(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String GROUPSFORUMTVROOMSPATH = "new UiSelector().resourceId(\"com.tigertext:id/tvRooms\")";
    public static final String DYNAMICFORUMSTABPATH = "//*[@text='FORUMTAB']";
    public static final String NEWFORUMBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/new_room_fab\")";
    public static final String NEWFORUMEDITNAMEPATH = "new UiSelector().resourceId(\"com.tigertext:id/create_group_name_edit\")";
    public static final String NEWFORUMDESCRIPTIONNAMEPATH = "new UiSelector().resourceId(\"com.tigertext:id/etRoomDesc\")";
    public static final String NEWFORUMCOMPLETEBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/ivNext\")";
    public static final String FORUMTITLEPATH = MessagingScreen.CONTACTDISPLAYNAMEPATH;
    public static final String FORUMJOINBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/tvJoin\")";
    public static final String FORUMMYFORUMSSELECTACTIONTITLEPATH = "new UiSelector().resourceId(\"android:id/alertTitle\")";
    public static final String FORUMMYFORUMSMUTEDURATIONTITLEPATH = "new UiSelector().resourceId(\"com.tigertext:id/alertTitle\")";
    public static final String SELECTACTIONLABLEPATH = "//android.widget.TextView[@text='ACTIONLABLE']";
    public static final String FORUMTITLEPATHS = FORUMTITLEPATH;
    public static final String FORUMSNACKBARTEXTPATH = "new UiSelector().resourceId(\"com.tigertext:id/snackbar_text\")";
    public static final String FORUMSAVATARIMAGEPATH = "new UiSelector().resourceId(\"com.tigertext:id/avatar\")";

    @AndroidFindBy(uiAutomator= FORUMSAVATARIMAGEPATH)
    private List<MobileElement> forumAvatarImage;
    @AndroidFindBy(uiAutomator= FORUMSNACKBARTEXTPATH)
    private MobileElement forumSnackBarText;
    @AndroidFindBy(uiAutomator= FORUMTITLEPATHS)
    private List<MobileElement> forumTitles;
    @AndroidFindAll({@AndroidBy(uiAutomator = FORUMMYFORUMSSELECTACTIONTITLEPATH), @AndroidBy(uiAutomator = FORUMMYFORUMSMUTEDURATIONTITLEPATH)})
    private MobileElement forumMyForumsSelectActionTitle;
    @AndroidFindBy(uiAutomator = FORUMJOINBUTTONPATH)
    private List<MobileElement> forumJoinButton;
    @AndroidFindBy(uiAutomator = FORUMTITLEPATH)
    private MobileElement forumTitle;
    @AndroidFindBy(uiAutomator = NEWFORUMCOMPLETEBUTTONPATH)
    private MobileElement newForumCompleteButton;
    @AndroidFindBy(uiAutomator = NEWFORUMDESCRIPTIONNAMEPATH)
    private MobileElement newForumDescriptionName;
    @AndroidFindBy(uiAutomator = NEWFORUMEDITNAMEPATH)
    private MobileElement newForumEditName;
    @AndroidFindBy(uiAutomator = GROUPSFORUMTVROOMSPATH)
    private MobileElement groupsForumTvRooms;
    @AndroidFindBy(uiAutomator = DYNAMICFORUMSTABPATH)
    private MobileElement exploreForumsTab;
    @AndroidFindBy(uiAutomator = NEWFORUMBUTTONPATH)
    private MobileElement newForumButton;

    public List<MobileElement> getForumAvatarImage() {
        return forumAvatarImage;
    }
    public MobileElement getForumSnackBarText() {
        return forumSnackBarText;
    }
    public List<MobileElement> getForumTitles() {
        return forumTitles;
    }
    public MobileElement getForumMyForumsSelectActionTitle() {
        return forumMyForumsSelectActionTitle;
    }
    public MobileElement getForumTitle() {
        return forumTitle;
    }
    public List<MobileElement> getForumJoinButtons() {
        return forumJoinButton;
    }
    public MobileElement getGroupsForumTvRooms() {
        return groupsForumTvRooms;
    }
    public MobileElement getExploreForumsTab() {
        return exploreForumsTab;
    }
    public MobileElement getNewForumButton() {
        return newForumButton;
    }
    public MobileElement getNewForumEditName() {
        return newForumEditName;
    }
    public MobileElement getNewForumDescriptionName() {
        return newForumDescriptionName;
    }
    public MobileElement getNewForumCompleteButton() {
        return newForumCompleteButton;
    }
}
