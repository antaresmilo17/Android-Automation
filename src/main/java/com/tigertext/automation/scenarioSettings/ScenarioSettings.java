package com.tigertext.automation.scenarioSettings;
import com.api_wrapper.MobileAPI;
import com.tigertext.automation.common.CommonMethods;
import com.tigertext.automation.config.TestConfig;
import com.tigertext.automation.pageObjects.LoginScreen;
import com.tigertext.automation.pageObjects.MessagingScreen;
import com.util.DriverFactory;
import com.util.WebDriverController;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.logging.Logger;

public class ScenarioSettings {
    @Autowired @Qualifier
    MobileAPI mobile_webdriver = new MobileAPI();
    WebDriverController webDriverController = new WebDriverController();
    MessagingScreen messagingPO;

    LoginScreen loginPO;
    CommonMethods commonMethods;
    Logger logger = Logger.getLogger(ScenarioSettings.class.getName());


    /*This is responsible for any actions that take place BEFORE any test cases are executed.
      This is where we initialize which device/simulator to use*/
    @Before
    public void before(Scenario scenario) throws Throwable {
        //If the driver is null, create it. If not, reuse it so that we use only 1 simulator
        if(WebDriverController.appium_driver == null) {
            //Initializes the device/simulator
            AppiumDriver appium_driver = webDriverController.mobileInstantiate(scenario);
            loginPO = new LoginScreen(appium_driver);
        }
        else {
            WebDriverController.appium_driver.launchApp();
            loginPO = new LoginScreen(WebDriverController.appium_driver);
        }
        //This sets up which environment to use within the app
        theEnvironmentIsSetupToEnvironment();
    }

    //This is responsible for any actions that take place AFTER all test cases are executed
    @After
    public void after(Scenario scenario) throws Throwable {
        //Close the application
        WebDriverController.appium_driver.closeApp();

        //Below stores the results of whether the Scenario passed or failed.
        if(scenario.isFailed()){
            DriverFactory.passFailMap.put("didTestSuitePass" + scenario.getId(), false);
        }
        else {
            DriverFactory.passFailMap.put("didTestSuitePass" + scenario.getId(), true);
        }
    }

    public void theEnvironmentIsSetupToEnvironment() throws Throwable {
        String environment;
        //This makes sure that the environment is consistent per test even if the config file somehow changes.
        if (mobile_webdriver.doesDictionaryKeyExist("environment")) {
            environment = mobile_webdriver.data.getDictionary("environment");
        } else {
            environment = TestConfig.Environment.getName();
        }
        mobile_webdriver.data.addDictionary("environment", environment);

       try{
            mobile_webdriver.waitForPageObjectToBeClickable(loginPO.getGetStartedButton(), 40);
        /*Refining indexes because the app isn't able to find the environment by name. When we try doing this,
          it is selecting the incorrect environment by 1 index. EX: I try finding the environment by the text "QA".
          Instead of clicking QA, it is clicking Staging although I am specifying that I want to click QA.*/
            String environmentIndex = "";
            String dynamicEnvNumber = "";
            if (environment.contains("prod")) {
                environmentIndex = "2";
            } else if (environment.contains("staging")) {
                environmentIndex = "3";
            } else if (environment.contains("qa")) {
                environmentIndex = "4";
            } else if (environment.contains("dev")) {
                environmentIndex = "5";
            } else if (environment.contains("env")) {
                environmentIndex = "8";
                dynamicEnvNumber = environment.substring(3, 4);
            }
            loginPO.getEnvironmentLabel().click();
            WebElement environmentElement = WebDriverController.appium_driver.findElementByXPath("//*[@class='android.widget.CheckedTextView'][" + environmentIndex + "]");
            environmentElement.click();
            if (environment.contains("env")) {
                WebElement dynamicEnvTextField = WebDriverController.appium_driver.findElementByXPath("//*[@class='android.widget.EditText']");
                dynamicEnvTextField.clear();
                dynamicEnvTextField.sendKeys(dynamicEnvNumber);
            }
        }catch (Exception e)
       {
           logger.info("NO NEED TO SELECT ENVIRONMENT");

       }
    }
}


