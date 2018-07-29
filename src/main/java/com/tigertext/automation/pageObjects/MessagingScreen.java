package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

 public class MessagingScreen {
     public MessagingScreen(AppiumDriver<MobileElement> driver) {
         PageFactory.initElements(new AppiumFieldDecorator(driver), this);
     }

     public static final String ADDNEWGROUPBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/new_group_fab\")";
     public static final String GROUPSTABPATH = "//*[@text='Groups']";
     public static final String PLUSOPTIONSBUTTONPATH = ".//*[@class='android.widget.ImageButton']";
     public static final String ENTERGROUPNAMETEXTFIELDPATH = "new UiSelector().resourceId(\"com.tigertext:id/create_group_name_edit\")";
     public static final String GETADDNEWGROUPBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/create_group_submit_button\")";
     public static final String NEWGROUPSEARCHUSERTEXTFIELDPATH = "new UiSelector().resourceId(\"com.tigertext:id/search_input_text\")";
     public static final String NEWGROUPSEARCHSECONDUSERTEXTFIELDPATH = "new UiSelector().resourceId(\"com.tigertext:id/search_input_text\")";
     public static final String NEWGROUPADDUSERBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/right_icon_holder\")";
     public static final String NEWGROUPDONEADDINGUSERSBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/search_done\")";
     public static final String CONVERSATIONGROUPNAMEPATH = "new UiSelector().resourceId(\"com.tigertext:id/conversation_contact_name_textView\")";

     public static final String CONVERSATIONTEXTFIELDPATH = "new UiSelector().resourceId(\"com.tigertext:id/activity_conversation_message_sent_textEdit\")";
     public static final String CONVERSATIONMUTEBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/conversation_mute\")";
     public static final String CONVERSATIONADDUSERBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/conversation_add_user\")";
     public static final String CONVERSATIONSETTINGSBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/menu_conversation_details_page\")";
     public static final String CONVERSATIONPRIORITYMESSAGEBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/priority_message_option_gallery\")";
     public static final String CONVERSATIONPRIORITYMESSAGEClOSEDIALOGBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/close_priority_message_banner\")";
     public static final String CONVERSATIONMESSAGETEXTPATH = "new UiSelector().resourceId(\"com.tigertext:id/tv_msg_body\")";
     public static final String CONVERSATIONMESSAGETEXTPATH2 = "//android.widget.TextView[@resource-id='com.tigertext:id/tv_msg_body']";
     public static final String CONVERSATIONMESSAGESTATUSPATH = "new UiSelector().resourceId(\"com.tigertext:id/conversation_item_message_box_status\")";
     public static final String CONVERSATIONMUTETIMEFRAMEOPTIONSPATH = "new UiSelector().resourceId(\"android:id/text1\")";
     public static final String CONVERSATIONUNMUTEBUTTONPATH = "//*[@id='com.tigertext:id/conversation_mute' and @content-desc='Unmute']";
     public static final String CONVERSATIONMUTEICONPATH = "new UiSelector().resourceId(\"com.tigertext:id/mute_icon\")";

     public static final String CONVERSATIONSETTINGSMUTESWITCHPATH = "new UiSelector().resourceId(\"com.tigertext:id/mute_switch\")";
     public static final String CONVERSATIONSETTINGSEDITGROUPBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/edit_group_menu_button\")";
     public static final String CONVERSATIONSETTINGSADDMEMBERBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/add_members_area\")";
     public static final String CONVERSATIONSETTINGSLEAVEGROUPBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/leave_group_row\")";
     public static final String CONVERSATIONSETTINGSMEMBERSCARDBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/members_card_header\")";
     public static final String CONVERSATIONSETTINGSDETAILSHEADLINETEXTPATH = "new UiSelector().resourceId(\"com.tigertext:id/details_headline_text\")";
     public static final String CONVERSATIONSETTINGSMEMBERTABPATH = "//*[@text='Members' and @class='android.widget.TextView']";
     public static final String CONVERSTAIONSETTINGSATTACHMENTSTABPATH = "//*[@text='Attachments' and @class='android.widget.TextView']";
     public static final String CONVERSATIONSETTINGSDIALOGLEAVEOPTIONBUTTONPATH = "new UiSelector().resourceId(\"android:id/button1\")";
     public static final String CONVERSATIONSENDBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/activity_conversation_message_send_button\")";

     public static final String CONVERSATIONOPTIONSRECALLBUTTONPATH = "//android.widget.TextView[@text='Recall']";
     public static final String CONVERSATIONOPTIONSDELETEBUTTONPATH = "//android.widget.TextView[@text='Delete']";
     public static final String CONVERSATIONOPTIONDETAILSBUTTONPATH = "//android.widget.TextView[@text='Details']";
     public static final String CONVERSATIONOPTIONSRESENDBUTTONPATH = "//android.widget.TextView[@text='Resend']";
     public static final String CONVERSATIOOPTIONSFORWARDBUTTONPATH = "//android.widget.TextView[@text='Forward']";
     public static final String CONVERSATIOOPTIONSRESENDASPRIORITYMESSAGEBUTTONPATH = "//android.widget.TextView[@text='Resend as Priority Message']";
     public static final String CONVERSATIONMESSAGEDETAILSDIALOGPATH = "new UiSelector().resourceId(\"com.tigertext:id/dialog_message\")";
     public static final String CONVERSATIONMESSAGEDETAILSDONEBUTTONPATH = "new UiSelector().resourceId(\"android:id/button2\")";
     public static final String CONVERSATIONFORWARDEDTVDISPLAYNAMEPATH = "//*[@resource-id='com.tigertext:id/tv_display_name' and @text='You Forwarded:']";
     public static final String CONVERSATIONTVDISPLAYENAMEPATH = "new UiSelector().resourceId(\"com.tigertext:id/tv_display_name\")";

     public static final String CONVERSATIONPRIORITYMESSAGETVHEADERNAMEPATH = "//*[@resource-id='com.tigertext:id/tv_pm_header' and @text='PRIORITY MESSAGE']";
     public static final String CONVERSATIONGALLERYOPTIONATTACHMENTBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/attachment_option_gallery\")";
     public static final String CONVERSATIONCAMERAOPTIONATTACHMENTBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/attachment_option_camera\")";
     public static final String CONVERSATIONLOCATIONOPTIONATTACHMENTBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/attachment_option_location\")";
     public static final String CONVERSATIONOPTIONFILEATTACHMENTBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/attachment_option_file\")";
     public static final String CONVERSATIONAUDIOATTACHMENBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/attachment_option_audio\")";
     public static final String CONVERSATIONTOOLBARSEARCHBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/inbox_toolbar_search_tt\")";
     public static final String CONVERSATIONGROUPMEMBERREADSTATUSPATH = "new UiSelector().resourceId(\"com.tigertext:id/group_member_read_status\")";
     public static final String CONVERSATIONGROUPREADSTATUSTOOLBARPATH = "new UiSelector().resourceId(\"com.tigertext:id/group_read_status_toolbar_textView\")";
     public static final String CONVERSATIONQUICKREPLYMESSAGEPATH = "new UiSelector().resourceId(\"com.tigertext:id/quick_reply_message\")";

     public static final String CAMERASHUTTERBUTTONPATH = "//GLButton[@text='Shutter']";
     public static final String CAMERAOKAYBUTTONPATH = "new UiSelector().resourceId(\"com.sec.android.app.camera:id/okay\")";
     public static final String IMAGEQUALITYSENDBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/tvSend\")";
     public static final String CONVERSATIONIMAGEVIEWPREVIEWPATH = "new UiSelector().resourceId(\"com.tigertext:id/imageview_preview\")";
     public static final String CONVERSATIONIMAGEPREVIEWPATH = "new UiSelector().resourceId(\"com.tigertext:id/image_preview\")";
     public static final String CONVERSATIONDOCUMENTFILEIMAGEPATH= "new UiSelector().resourceId(\"android.widget.RelativeLayout\")";
     public static final String CONVERSATIONDOCFILENAMEPATH = "new UiSelector().resourceId(\"com.tigertext:id/file_name\")";

     public static final String CONVERSATIONMESSAGETIMESTAMPPATH = "new UiSelector().resourceId(\"com.tigertext:id/conversation_item_time_stamp\")";
     public static final String RECORDERRECORDINGBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/attachment_audio_record_buttton_icon\")";
     public static final String CONVERSATIONRECORDINGPATH = "new UiSelector().resourceId(\"com.tigertext:id/audio_play_pause\")";
     public static final String CONVERSATIONAUDIOPROGRESSPATH = "new UiSelector().resourceId(\"com.tigertext:id/audio_progress\")";
     public static final String GOOGLESELECTMARKERLOCATIONPATH = "new UiSelector().resourceId(\"com.google.android.gms:id/select_marker_location\")";
     public static final String GOOGLELOCATIONCONFIRMBUTTONPATH = "new UiSelector().resourceId(\"com.google.android.gms:id/confirm_button\")";

     public static final String CONVERSATIONPRIORITYMESSAGEPILLPATH = "new UiSelector().resourceId(\"com.tigertext:id/priority_message_pill\")";
     public static final String CONVERSATIONCLOSEPRIORITYMESSAGEBANNERPATH = "new UiSelector().resourceId(\"com.tigertext:id/close_priority_message_banner\")";
     public static final String CONVERSATIONGALLERYIMAGEPATH = "new UiSelector().resourceId(\"com.tigertext:id/gallery_image\")";
     public static final String CONVESATIONSENDATTACHMENTDONEBUTTONPATH = "new UiSelector().resourceId(\"com.tigertext:id/activity_conversation_send_attachment_done\")";
     public static final String OPENFROMFOLDERRECENTBUTTONPATH = "//*[@resource-id='android:id/title' and @text='Recent']";
     public static final String OPENFROMFOLDERGALAXYJ7BUTTONPATH = "//*[@resource-id='android:id/title' and @text='Galaxy J7']";
     public static final String OPENFROMFOLDERDOWNLOADBUTTONPATH = "//*[@resource-id='android:id/title' and @text='Download']";
     public static final String OPENFROMFOLDERPDFFILEPATH = "//*[@resource-id='android:id/title' and @text='pdfFile.pdf']";
     public static final String OPENFROMFOLDERPPFILEPATH = "//*[@resource-id='android:id/title' and @text='powerPointFile.ppt']";
     public static final String OPENFROMFOLDERXLSFILEPATH = "//*[@resource-id='android:id/title' and @text='xlsFile.xls']";

     public static final String OPENFROMFOLDERDOCFILEPATH = "//*[@resource-id='android:id/title' and @text='docFile.doc']";
     public static final String OPENFROMFOLDERMENUOPENBUTTONPATH = "new UiSelector().resourceId(\"com.android.documentsui:id/menu_open\")";
     public static final String CONVERSATIONDOCUMENTPREVIEWTITLEPATH = "new UiSelector().resourceId(\"com.tigertext:id/attachment_document_preview_title\")";
     public static final String CONVERSATIONDOCUMENTPREVIEWDOWNLOADPROGRESSBARPATH = "new UiSelector().resourceId(\"com.tigertext:id/attachment_document_preview_download_progress_bar\")";
     public static final String TIGERTEXTINBOXNOTIFICATIONPATH = "new UiSelector().resourceId(\"android:id/inbox_text0\")";
     public static final String TIGERTEXTNOTIFICATIONPATH = "//*[@resource-id='android:id/title' and @package='com.tigertext']";
     public static final String CONVERSATIOMESSAGEBUBBLEPATH = "new UiSelector().resourceId(\"com.tigertext:id/message_bubble_background\")";
     public static final String DYNAMICCONVERSATIONMESSAGEFORWARDSELECTEDCONTACT = "//*[@resource-id='com.tigertext:id/contact_display_name' and @text='USERNAME']";
     public static final String CONVERSATIONGROUPDISPLAYNAME = "//*[@text='GROUPNAME']";
     public static final String DYNAMICCONVERSATIONMESSAGETEXTBUBBLE = "//*[@resource-id='com.tigertext:id/message_bubble_background']";

     public static final String DYNAMICADDUSERSELECTOR = "//android.widget.TextView[@text='USERNAME']";
     public static final String CONVERSATIONMESSAGEDETAILMESSAGEID = "//*[Contains(text(),'MESSAGEID')";
     public static final String SECURECAMERANOTHANKSBUTTON = "new UiSelector().resourceId(\"com.tigertext:id/didYouKnowHeader\")";
     public static final String FORWARDRECIPIENTSPILLSPATH = "new UiSelector().resourceId(\"com.tigertext:id/search_input_text\")";
     public static final String CONTACTDISPLAYNAMEPATH = "new UiSelector().resourceId(\"com.tigertext:id/display_name\")";
     public static final String CONVERSATIONFRWDORIGINALPERSONPATH = "new UiSelector().resourceId(\"com.tigertext:id/tv_frwd_original_person\")";
     public static final String COMPOSENEWMESSAGEBUTTONPATH = "//android.view.ViewGroup[@resource-id='com.tigertext:id/main_floating_action_menu']/android.widget.ImageButton[@index='0']";
     public static final String CONVERSATIONBANGMESSAGEPATH = "new UiSelector().resourceId(\"com.tigertext:id/conversation_bang_message_display_body\")";
     public static final String CONVERSATIONATTACHMENTRECYCLERVIEWPATH = "new UiSelector().resourceId(\"com.tigertext:id/send_attachment_recycler_view\")";

     @AndroidFindBy(uiAutomator = CONVERSATIONBANGMESSAGEPATH)
     public List<MobileElement> conversationBangMessage;
     @AndroidFindBy(xpath = COMPOSENEWMESSAGEBUTTONPATH)
     public MobileElement composeNewMessageButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONDOCUMENTFILEIMAGEPATH)
     public MobileElement conversationDocumentFileImagePath;
     @AndroidFindBy(uiAutomator = FORWARDRECIPIENTSPILLSPATH)
     public MobileElement forwardRecipientsPills;
     @AndroidFindBy(uiAutomator = CONTACTDISPLAYNAMEPATH)
     public MobileElement contactDisplayName;
     @AndroidFindBy(uiAutomator = CONTACTDISPLAYNAMEPATH)
     public List<MobileElement> contactDisplayNames;
     @AndroidFindBy(uiAutomator = CONVERSATIONFRWDORIGINALPERSONPATH)
     public MobileElement conversationFrwdOriginalPerson;
     @AndroidFindBy(uiAutomator = ADDNEWGROUPBUTTONPATH)
     private MobileElement addNewGroupButton;
     @AndroidFindBy(xpath = GROUPSTABPATH)
     private MobileElement groupsTab;
     @AndroidFindBy(xpath = PLUSOPTIONSBUTTONPATH)
     private MobileElement plusOptionsButton;
     @AndroidFindBy(uiAutomator = ENTERGROUPNAMETEXTFIELDPATH)
     private MobileElement enterGroupNameTextField;
     @AndroidFindBy(uiAutomator = SECURECAMERANOTHANKSBUTTON)
     private MobileElement secureCameraNoThanksButton;

     @AndroidFindBy(uiAutomator = GETADDNEWGROUPBUTTONPATH)
     private MobileElement newGroupNextButton;
     @AndroidFindBy(uiAutomator = NEWGROUPSEARCHUSERTEXTFIELDPATH)
     private MobileElement newGroupSearchUserTextField;
     @AndroidFindBy(uiAutomator = NEWGROUPSEARCHSECONDUSERTEXTFIELDPATH)
     private List<MobileElement> newGroupSearchSecondUserTextField;
     @AndroidFindBy(uiAutomator = NEWGROUPADDUSERBUTTONPATH)
     private List<MobileElement> newGroupAddUserButton;
     @AndroidFindBy(uiAutomator = NEWGROUPDONEADDINGUSERSBUTTONPATH)
     private MobileElement newGroupDoneAddingUsersButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONGROUPNAMEPATH)
     private MobileElement conversationGroupName;
     @AndroidFindBy(uiAutomator = CONVERSATIONGROUPNAMEPATH)
     private List<MobileElement> conversationGroupNames;
     @AndroidFindBy(uiAutomator = CONVERSATIONTEXTFIELDPATH)
     private MobileElement conversationTextField;
     @AndroidFindBy(uiAutomator = CONVERSATIONMUTEBUTTONPATH)
     private MobileElement conversationMuteButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONADDUSERBUTTONPATH)
     private MobileElement conversationAddUserButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONSETTINGSBUTTONPATH)
     private MobileElement conversationSettingsButton;

     @AndroidFindBy(uiAutomator = CONVERSATIONPRIORITYMESSAGEBUTTONPATH)
     private MobileElement conversationPriorityMessageButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONPRIORITYMESSAGEClOSEDIALOGBUTTONPATH)
     private MobileElement conversationPriorityMessageCloseDialogButton;
     @AndroidFindAll({@AndroidBy (uiAutomator = CONVERSATIONMESSAGETEXTPATH), @AndroidBy(xpath=CONVERSATIONMESSAGETEXTPATH2)})
     private List<MobileElement> conversationMessageText;
     @AndroidFindBy(uiAutomator = CONVERSATIONMESSAGESTATUSPATH)
     private List<MobileElement> conversationMessageStatus;
     @AndroidFindBy(uiAutomator = CONVERSATIONMUTETIMEFRAMEOPTIONSPATH)
     private List<MobileElement> conversationMuteTimeFrameOptions;
     @AndroidFindBy(xpath = CONVERSATIONUNMUTEBUTTONPATH)
     private MobileElement conversationUnmuteButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONMUTEICONPATH)
     private MobileElement conversationMuteIcon;
     @AndroidFindBy(uiAutomator = CONVERSATIONMUTEICONPATH)
     private List<MobileElement> conversationMuteIcons;
     @AndroidFindBy(uiAutomator = CONVERSATIONSETTINGSMUTESWITCHPATH)
     private MobileElement conversationSettingsMuteSwitch;
     @AndroidFindBy(uiAutomator = CONVERSATIONSETTINGSEDITGROUPBUTTONPATH)
     private MobileElement conversationSettingsEditGroupButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONSETTINGSADDMEMBERBUTTONPATH)
     private MobileElement conversationSettingsAddMemberButton;

     @AndroidFindBy(uiAutomator = CONVERSATIONSETTINGSLEAVEGROUPBUTTONPATH)
     private MobileElement conversationSettingsLeaveGroupButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONSETTINGSMEMBERSCARDBUTTONPATH)
     private MobileElement conversationSettingsMembersCardButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONSETTINGSDETAILSHEADLINETEXTPATH)
     private List<MobileElement> conversationSettingsDetailsHeadlineText;
     @AndroidFindBy(xpath = CONVERSATIONSETTINGSMEMBERTABPATH)
     private MobileElement conversationSettingsMemberTab;
     @AndroidFindBy(xpath = CONVERSTAIONSETTINGSATTACHMENTSTABPATH)
     private MobileElement conversationSettingsAttachmentsTab;
     @AndroidFindBy(uiAutomator = CONVERSATIONSETTINGSDIALOGLEAVEOPTIONBUTTONPATH)
     private MobileElement conversationSettingsDialogLeaveOptionButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONSENDBUTTONPATH)
     private MobileElement conversationSendButton;
     @AndroidFindAll({@AndroidBy(xpath = CONVERSATIONOPTIONSDELETEBUTTONPATH), @AndroidBy(xpath = CONVERSATIONOPTIONSRECALLBUTTONPATH)})
     private MobileElement conversationOptionsRecallButton;
     @AndroidFindBy(xpath = CONVERSATIONOPTIONDETAILSBUTTONPATH)
     private MobileElement conversationOptionsDetailsButton;
     @AndroidFindBy(xpath = CONVERSATIONOPTIONSRESENDBUTTONPATH)
     private MobileElement conversationOptionsResendButton;

     @AndroidFindBy(xpath = CONVERSATIOOPTIONSFORWARDBUTTONPATH)
     private MobileElement conversationOptionsForwardButton;
     @AndroidFindBy(xpath = CONVERSATIOOPTIONSRESENDASPRIORITYMESSAGEBUTTONPATH)
     private MobileElement conversationOptionsResendAsPriorityMessageButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONMESSAGEDETAILSDIALOGPATH)
     private MobileElement conversationMessageDetailsDialog;
     @AndroidFindBy(uiAutomator = CONVERSATIONMESSAGEDETAILSDONEBUTTONPATH)
     private MobileElement conversationMessageDetailsDoneButton;
     @AndroidFindBy(xpath = CONVERSATIONFORWARDEDTVDISPLAYNAMEPATH)
     private List<MobileElement> conversationForwardedTvDisplayName;
     @AndroidFindBy(uiAutomator = CONVERSATIONTVDISPLAYENAMEPATH)
     private List<MobileElement> conversationTvDisplayName;
     @AndroidFindBy(xpath = CONVERSATIONPRIORITYMESSAGETVHEADERNAMEPATH)
     private MobileElement conversationPriorityMessageTvHeaderName;
     @AndroidFindBy(uiAutomator = CONVERSATIONGALLERYOPTIONATTACHMENTBUTTONPATH)
     private MobileElement conversationGalleryOptionAttachmentButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONCAMERAOPTIONATTACHMENTBUTTONPATH)
     private MobileElement conversationCameraOptionAttachmentButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONLOCATIONOPTIONATTACHMENTBUTTONPATH)
     private MobileElement conversationLocationOptionAttachmentButton;

     @AndroidFindBy(uiAutomator = CONVERSATIONOPTIONFILEATTACHMENTBUTTONPATH)
     private MobileElement conversationOptionFileAttachmentButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONAUDIOATTACHMENBUTTONPATH)
     private MobileElement conversationAudioAttachmentButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONTOOLBARSEARCHBUTTONPATH)
     private MobileElement conversationToolbarSearchButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONGROUPMEMBERREADSTATUSPATH)
     private List<MobileElement> conversationGroupMemberReadStatus;
     @AndroidFindBy(uiAutomator = CONVERSATIONGROUPREADSTATUSTOOLBARPATH)
     private MobileElement conversationGroupReadStatusToolbar;
     @AndroidFindBy(uiAutomator = CONVERSATIONQUICKREPLYMESSAGEPATH)
     private List<MobileElement> conversationQuickReplyMessage;
     @AndroidFindBy(xpath = CAMERASHUTTERBUTTONPATH)
     private MobileElement cameraShutterButton;
     @AndroidFindBy(uiAutomator = CAMERAOKAYBUTTONPATH)
     private MobileElement cameraOkayButton;
     @AndroidFindBy(uiAutomator = IMAGEQUALITYSENDBUTTONPATH)
     private MobileElement imageQualitySendButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONIMAGEVIEWPREVIEWPATH)
     private List<MobileElement> conversationImageViewPreview;
     @AndroidFindBy(uiAutomator = CONVERSATIONIMAGEPREVIEWPATH)
     private MobileElement conversationImagePreview;
     @AndroidFindBy(uiAutomator = CONVERSATIONDOCFILENAMEPATH)
     private MobileElement conversationDocFileName;

     @AndroidFindBy(uiAutomator = CONVERSATIONMESSAGETIMESTAMPPATH)
     private List<MobileElement> conversationMessageTimeStamp;
     @AndroidFindBy(uiAutomator = RECORDERRECORDINGBUTTONPATH)
     private MobileElement recorderRecordingButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONRECORDINGPATH)
     private MobileElement conversationRecording;
     @AndroidFindBy(uiAutomator = CONVERSATIONAUDIOPROGRESSPATH)
     private List<MobileElement> conversationAudioProgress;
     @AndroidFindBy(uiAutomator = GOOGLESELECTMARKERLOCATIONPATH)
     private MobileElement googleSelectMarkerLocation;
     @AndroidFindBy(uiAutomator = GOOGLELOCATIONCONFIRMBUTTONPATH)
     private MobileElement googleLocationConfirmButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONPRIORITYMESSAGEPILLPATH)
     private MobileElement conversationPriorityMessagePill;
     @AndroidFindBy(uiAutomator = CONVERSATIONCLOSEPRIORITYMESSAGEBANNERPATH)
     private MobileElement conversationClosePriorityMessageBanner;
     @AndroidFindBy(uiAutomator = CONVERSATIONGALLERYIMAGEPATH)
     private List<MobileElement> conversationGalleryImage;
     @AndroidFindBy(uiAutomator = CONVESATIONSENDATTACHMENTDONEBUTTONPATH)
     private MobileElement conversationSendAttachmentDoneButton;

     @AndroidFindBy(xpath = OPENFROMFOLDERRECENTBUTTONPATH)
     private MobileElement openFromFolderRecentButton;
     @AndroidFindBy(xpath = OPENFROMFOLDERGALAXYJ7BUTTONPATH)
     private MobileElement openFromFolderGalaxyJ7Button;
     @AndroidFindBy(xpath = OPENFROMFOLDERDOWNLOADBUTTONPATH)
     private MobileElement openFromFolderDownloadButton;
     @AndroidFindBy(xpath = OPENFROMFOLDERPDFFILEPATH)
     private MobileElement openFromFolderPDFfile;
     @AndroidFindBy(xpath = OPENFROMFOLDERPPFILEPATH)
     private MobileElement openFromFolderPPfile;
     @AndroidFindBy(xpath = OPENFROMFOLDERXLSFILEPATH)
     private MobileElement openFromFolderXLSfile;
     @AndroidFindBy(xpath = OPENFROMFOLDERDOCFILEPATH)
     private MobileElement openFromFolderDocfile;
     @AndroidFindBy(uiAutomator = OPENFROMFOLDERMENUOPENBUTTONPATH)
     private MobileElement openFromFolderMenuOpenButton;
     @AndroidFindBy(uiAutomator = CONVERSATIONDOCUMENTPREVIEWTITLEPATH)
     private List<MobileElement> conversationDocumentPreviewTitle;
     @AndroidFindBy(uiAutomator = CONVERSATIONDOCUMENTPREVIEWDOWNLOADPROGRESSBARPATH)
     private MobileElement conversationDocumentPreviewDownloadProgressBar;

     @AndroidFindBy(uiAutomator = TIGERTEXTINBOXNOTIFICATIONPATH)
     private MobileElement tigerTextInboxNotification;
     @AndroidFindBy(xpath = TIGERTEXTNOTIFICATIONPATH)
     private MobileElement tigerTextNotification;
     @AndroidFindBy(xpath = CONVERSATIONATTACHMENTRECYCLERVIEWPATH)
     private MobileElement conversationAttachmentRecyclerViewPath;
     @AndroidFindBy(uiAutomator = CONVERSATIOMESSAGEBUBBLEPATH)
     private List<MobileElement> conversationMessageBubble;

     public MobileElement getGroupsTab() {
         return groupsTab;
     }
     public MobileElement getAddNewGroupButton() {
         return addNewGroupButton;
     }
     public List<MobileElement> getNewGroupAddUserButton() {
         return newGroupAddUserButton;
     }
     public List<MobileElement> getNewGroupSearchSecondUserTextField() {
         return newGroupSearchSecondUserTextField;
     }
     public MobileElement getEnterGroupNameTextField() {
         return enterGroupNameTextField;
     }
     public MobileElement getConversationGroupName() {
         return conversationGroupName;
     }
     public List<MobileElement> getConversationGroupNames() {return  conversationGroupNames;}
     public List<MobileElement> getConversationMessageText() {
         return conversationMessageText;
     }
     public MobileElement getConversationAddUserButton() {
         return conversationAddUserButton;
     }
     public MobileElement getConversationMuteButton() {
         return conversationMuteButton;
     }

     public List<MobileElement> getConversationMessageStatus() {
         return conversationMessageStatus;
     }
     public MobileElement getConversationSettingsButton() {
         return conversationSettingsButton;
     }
     public MobileElement getConversationTextField() {
         return conversationTextField;
     }
     public MobileElement getConversationPriorityMessageButton() {
         return conversationPriorityMessageButton;
     }
     public List<MobileElement> getConversationMuteTimeFrameOptions() {
         return conversationMuteTimeFrameOptions;
     }
     public MobileElement getConversationUnmuteButton() {
         return conversationUnmuteButton;
     }
     public MobileElement getNewGroupDoneAddingUsersButton() {
         return newGroupDoneAddingUsersButton;
     }
     public MobileElement getNewGroupNextButton() {
         return newGroupNextButton;
     }
     public MobileElement getNewGroupSearchUserTextField() {
         return newGroupSearchUserTextField;
     }
     public MobileElement getPlusOptionsButton() {
         return plusOptionsButton;
     }

     public List<MobileElement> getConversationAudioProgress() {
         return conversationAudioProgress;
     }
     public List<MobileElement> getConversationForwardedTvDisplayName() {
         return conversationForwardedTvDisplayName;
     }
     public List<MobileElement> getConversationGalleryImage() {
         return conversationGalleryImage;
     }
     public List<MobileElement> getConversationGroupMemberReadStatus() {
         return conversationGroupMemberReadStatus;
     }
     public MobileElement getConversationImagePreview() {
         return conversationImagePreview;
     }
     public List<MobileElement> getConversationImageViewPreview() {
         return conversationImageViewPreview;
     }

     public MobileElement getConversationDocFileName() {
         return conversationDocFileName;
     }

     public List<MobileElement> getConversationMessageTimeStamp() {
         return conversationMessageTimeStamp;
     }
     public List<MobileElement> getConversationQuickReplyMessage() {
         return conversationQuickReplyMessage;
     }
     public List<MobileElement> getConversationSettingsDetailsHeadlineText() {
         return conversationSettingsDetailsHeadlineText;
     }
     public List<MobileElement> getConversationTvDisplayName() {
         return conversationTvDisplayName;
     }
     public MobileElement getCameraOkayButton() {
         return cameraOkayButton;
     }
     public MobileElement getConversationDocumentFileImagePath() {
         return conversationDocumentFileImagePath;
     }
     public MobileElement getConversationAttachmentRecyclerViewPath() {
         return conversationAttachmentRecyclerViewPath;
     }
     public MobileElement getConversationGroupReadStatusToolbar() {
         return conversationGroupReadStatusToolbar;
     }

     public MobileElement getCameraShutterButton() {
         return cameraShutterButton;
     }
     public MobileElement getConversationAudioAttachmentButton() {
         return conversationAudioAttachmentButton;
     }
     public MobileElement getConversationCameraOptionAttachmentButton() {
         return conversationCameraOptionAttachmentButton;
     }
     public MobileElement getConversationClosePriorityMessageBanner() {
         return conversationClosePriorityMessageBanner;
     }
     public MobileElement getConversationGalleryOptionAttachmentButton() {
         return conversationGalleryOptionAttachmentButton;
     }
     public MobileElement getConversationLocationOptionAttachmentButton() {
         return conversationLocationOptionAttachmentButton;
     }
     public MobileElement getConversationMessageDetailsDialog() {
         return conversationMessageDetailsDialog;
     }
     public MobileElement getConversationMessageDetailsDoneButton() {
         return conversationMessageDetailsDoneButton;
     }
     public MobileElement getConversationMuteIcon() {
         return conversationMuteIcon;
     }
     public List<MobileElement> getConversationMuteIcons(){ return conversationMuteIcons;}

     public MobileElement getConversationOptionFileAttachmentButton() {
         return conversationOptionFileAttachmentButton;
     }
     public MobileElement getConversationOptionsDetailsButton() {
         return conversationOptionsDetailsButton;
     }
     public MobileElement getConversationOptionsForwardButton() {
         return conversationOptionsForwardButton;
     }
     public List<MobileElement> getConversationDocumentPreviewTitle() {
         return conversationDocumentPreviewTitle;
     }
     public MobileElement getConversationOptionsRecallButton() {
         return conversationOptionsRecallButton;
     }
     public MobileElement getConversationDocumentPreviewDownloadProgressBar() {
         return conversationDocumentPreviewDownloadProgressBar;
     }
     public MobileElement getConversationOptionsResendAsPriorityMessageButton() {
         return conversationOptionsResendAsPriorityMessageButton;
     }
     public MobileElement getConversationOptionsResendButton() {
         return conversationOptionsResendButton;
     }
     public MobileElement getConversationPriorityMessagePill() {
         return conversationPriorityMessagePill;
     }
     public MobileElement getConversationPriorityMessageTvHeaderName() {
         return conversationPriorityMessageTvHeaderName;
     }

     public MobileElement getConversationRecording() {
         return conversationRecording;
     }
     public MobileElement getConversationSendAttachmentDoneButton() {
         return conversationSendAttachmentDoneButton;
     }
     public List<MobileElement> getConversationMessageBubble() {
         return conversationMessageBubble;
     }
     public MobileElement getConversationSendButton() {
         return conversationSendButton;
     }
     public MobileElement getConversationSettingsAddMemberButton() {
         return conversationSettingsAddMemberButton;
     }
     public MobileElement getConversationSettingsAttachmentsTab() {
         return conversationSettingsAttachmentsTab;
     }
     public MobileElement getConversationSettingsDialogLeaveOptionButton() {
         return conversationSettingsDialogLeaveOptionButton;
     }
     public MobileElement getConversationSettingsEditGroupButton() {
         return conversationSettingsEditGroupButton;
     }
     public MobileElement getConversationSettingsLeaveGroupButton() {
         return conversationSettingsLeaveGroupButton;
     }
     public MobileElement getConversationSettingsMembersCardButton() {
         return conversationSettingsMembersCardButton;
     }

     public MobileElement getConversationSettingsMemberTab() {
         return conversationSettingsMemberTab;
     }
     public MobileElement getConversationSettingsMuteSwitch() {
         return conversationSettingsMuteSwitch;
     }
     public MobileElement getConversationTGroupReadStatusToolbar() {

         return conversationGroupReadStatusToolbar;
     }
     public MobileElement getConversationToolbarSearchButton() {
         return conversationToolbarSearchButton;
     }
     public MobileElement getGoogleLocationConfirmButton() {
         return googleLocationConfirmButton;
     }
     public MobileElement getGoogleSelectMarkerLocation() {
         return googleSelectMarkerLocation;
     }
     public MobileElement getImageQualitySendButton() {
         return imageQualitySendButton;
     }
     public MobileElement getOpenFromFolderDocfile() {
         return openFromFolderDocfile;
     }
     public MobileElement getOpenFromFolderDownloadButton() {
         return openFromFolderDownloadButton;
     }
     public MobileElement getOpenFromFolderGalaxyJ7Button() {
         return openFromFolderGalaxyJ7Button;
     }

     public MobileElement getOpenFromFolderMenuOpenButton() {
         return openFromFolderMenuOpenButton;
     }
     public MobileElement getOpenFromFolderPDFfile() {
         return openFromFolderPDFfile;
     }
     public MobileElement getOpenFromFolderPPfile() {
         return openFromFolderPPfile;
     }
     public MobileElement getOpenFromFolderRecentButton() {
         return openFromFolderRecentButton;
     }
     public MobileElement getOpenFromFolderXLSfile() {
         return openFromFolderXLSfile;
     }
     public MobileElement getRecorderRecordingButton() {
         return recorderRecordingButton;
     }
     public MobileElement getTigerTextInboxNotification() {
         return tigerTextInboxNotification;
     }
     public MobileElement getTigerTextNotification() {
         return tigerTextNotification;
     }
     public MobileElement getContactDisplayName() {
         return contactDisplayName;
     }
     public List<MobileElement> getContactDisplayNames() {
         return contactDisplayNames;
     }
     public MobileElement getConversationFrwdOriginalPerson() {
         return conversationFrwdOriginalPerson;
     }
     public MobileElement getForwardRecipientsPills() {
         return forwardRecipientsPills;
     }
     public MobileElement getComposeNewMessageButton() {
         return composeNewMessageButton;
     }
     public List<MobileElement> getConversationBangMessage() {
         return conversationBangMessage;
     }

     public MobileElement getSecureCameraNoThanksButton() {
         return secureCameraNoThanksButton;
     }
     public MobileElement getConversationPriorityMessageCloseDialogButton() {
         return conversationPriorityMessageCloseDialogButton;
     }
 }