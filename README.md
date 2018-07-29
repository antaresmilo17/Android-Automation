# Native_TT_Roar
Below are the detailed instructions on how to setup the Native Automation for your mobile device.


# Machine Setup:

1. Open Terminal 
2. Install Homebew: https://brew.sh/
3. Install git: https://www.atlassian.com/git/tutorials/install-git
4. Install JAVA 8 SDK https://java.com/en/download/help/mac_install.xml
5. Setup the environment variable for JAVA_HOME
6. Install gradle: on terminal issue command: brew install gradle 
7. Install IntelliJ IDEA "Community edition IDE": https://www.jetbrains.com/idea/download/#section=mac
8. Clone the repo: git clone git@github.com:tigertext/Native_TT_Roar.git.
9. Setup the environment variable to log where the automation was stored in your bash_profile: export ANDROID_AUTO_HOME=/Users/{enterUserName}/Documents/iOS-Auto/

# IntelliJ Setup
1. Open IntelliJ and Under Configure, select Plugins.
2. Search and download the plugins for: Gherkins and Cucumber for Java
3. Close Dialog when done and select Open and open the project.
4. Look at the event log and you should see message saying "Unlinked Gradle Project?". Click Import Gradle Project
5. Uncheck the checkbox for "Create separate module per source set" 
6. Use gradle wrapper task configuration
7. Click OK
8. Wait for some dependencies to download and you should get the dialog for "Import Gradle Project". Click OK with the checkbox checked.
9. Create a file named "gradle.properties" and place it in the path: /Users/<USER NAME>/.gradle
10. The content of the file should just be: repoUserName=<username> and repoPassword=<password> (for now you can use: repoUserName=wlinares@tigertext.com repoPassword=tigerqa)
11. Build the project.
12. Make sure to update driver.properties with your updated values for the IOS device.
13. Also the path for the APP works best inside the automation folder "native_tt_roar_automation". Example: Original error: Bad app: /Users/willielinares/Desktop/apps/TigerText.ipa. App paths need to be absolute, or relative to the appium server install dir, or a URL to compressed file, or a special app name. (WARNING: The server did not provide any stacktrace information)

# Troubleshooting IntelliJ Issues:
1. If there is an issue with no Project SDK selected, set it to 1.8 under File -> Project Structure -> Project Settings -> Project.
2. If there is a compile error with Java selection 1.9, go to File -> Project Structure -> Modules and set the Language Level to 8 - Lamdas 

# Tools needed for Android testing:
1. Install Nodejs: https://nodejs.org/en/download/
2. Uninstall previous Appium and Install Appium Desktop from the .dmg file: https://github.com/appium/appium-desktop/releases/tag/v1.2.4. Make sure to open Appium from the desktop after to unload the resources and you can close it after.
3. Install Android Studio: https://developer.android.com/studio/index.html
4. Set the environment variable for ANDROID_HOME.

# Environment Variables
This is all that is required from your bash_profile:
- $vi ~./bash_profile
- export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home 
- export ANDROID_HOME=/Users/{yourUserName}/Library/Android/sdk
- export FRAMEWORK_AUTO_HOME=/Users/{yourUserName}/Documents/Native_TT_Roar
- export ANDROID_AUTO_HOME=/Users/{yourUserName}/Documents/Android-Auto
- export iOS_AUTO_HOME=/Users/{yourUserName}/Documents/iOS-Auto
- export PATH=$PATH:$JAVA_HOME/bin:$ANDROID_HOME/platform-tools

#How to extract and update the TigerConnect APK file
[Pre-requisites]
1. Install the APK Extractor app from the Google Store
2. Using the APK Exctractor app, search for the TigerConnect App
3. Extract the APK and note the folder is being downloaded to
4. Connect Android Phone to you pc
5. Copy the extracted TigerConnect App from your phone to the Android-Auto project folder
- path = "/Users/{yourUserName}/Documents/Android-Auto/"
6. Rename the app name to the following:
- app-debug.apk
8. Upload new app to BrowserStack by doing the following CURL command:
- curl -u "username:secretkey" -X POST "https://api.browserstack.com/app-automate/upload" -F "file=@/Users/{yourUserName}/Documents/Android-Auto/app-debug.apk"
9. Wait for the response
10. Copy the "bs://{numbersAndLetters}"
11. Updated the "remote.browserstackappbs" section in the driver.properties file with the copied "bs://{numbersAndLetters}"
12. Save and Rebuild the project
13. Update the Git repo

# Troubleshooting Tips
1. If dependencies you add aren't being added, go to View -> Tool Windows -> Gradle. From the window, there is a Circle Arrow button that will refresh and download new dependencies. 
2. If you think there are dependencies missing from the IOS or Android installation, you can run the following command to check from Appium's perspective: 
- Install Appium doctor from the command line: npm install -g appium-doctor 
- Type: appium-doctor --android

# Executing the Automation from Command Line
Similar to web, the automation can be executed via command line with different options. Below is the most basic that uses most of the default values from the config file:

- gradle clean test -Dcucumber.options="--tags @LoginScenario"

To run through an Android simulator in SauceLabs, use the following command:

- gradle clean test -Dremote.saucelabs=true -Ddevice.os=android -Ddevice.platform=Android -Ddevice.platformVersion=4.4 -Ddevice.name="LG Nexus 4 Emulator" -Ddevice.app=app-debug.apk -Dcucumber.options="--tags @LoginTests"

Below are the available options you are able to override from the command line:
- device.os=android
- device.platform=Android
- device.appDirectory=/Users/{enterUserName}/Documents/Native_TT_Roar/native_tt_roar_automation/
- device.app=TigerText.apk
- device.name=3300f892bfed14ad

# Example commands to run the automation through different devices
Android Physical:

gradle clean test
-Dremote.saucelabs=false
-Ddevice.os=android
-Ddevice.platform=Android
-Ddevice.name="{ENTER DEVICE NUMBER FROM ADB}"
-Ddevice.app=app-debug.apk
-Dcucumber.options="--tags @LoginTests"(OPTIONAL ON VM WHEN USING INTELLIJ)

Android Simulator:

gradle clean test
-Ddevice.platformVersion=6.0
-Ddevice.platform="Samsung Galaxy S7"
-Ddevice.app=app-debug.apk
-Dkey={ENTER TIGER CONNECT API KEY}
-Dsecret={ENTER TIGER CONNECT API SECRET}
-Dremote.stackusername={ENTER BROWSERSTACK USERNAME}
-Dremote.stackkey={ENTER BROWSERSTACK KEY}

Android IntelliJ VM options:
-Ddevice.platformVersion=6.0
-Ddevice.platform="Samsung Galaxy S7"
-Ddevice.app=app-debug.apk
-Dkey=6CKxrByRohhJKPi9YIwbfVwIEpfmFdSe
-Dsecret=fYFip7NSQxhY09iNVBpTHzhoANkC0Z2wUiWlS0zdSzhvlHxA
-Dremote.stackusername=ricardolimo1
-Dremote.stackkey=iF8huu3vM9qje3LCqxxk

# Referencing the Framework to this project
To reference the repo, you just need to provide the info in the build.gradle as follows:

repositories {
    maven {
        credentials {
            username '$repoUserName'
            password '$repoPassword'
        }
        url "https://<yourappid>.appspot.com"
    }
}

dependencies{
    compile group: 'com.framework', name: 'nativeautomationframework', version: "latest.integration"
}

# Troubleshoot Dependency for the Framework
- If the latest version is not being added after refreshing from gradle, type this in the command line after navigating to the automation from the command line: gradle build --refresh-dependencies
