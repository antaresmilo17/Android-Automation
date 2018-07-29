package stepdef;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.common.CommonMethods;
import com.tigertext.automation.common.StaticData;
import com.tigertext.automation.config.TestConfig;
import com.tigertext.automation.pageObjects.LoginScreen;
import com.tigertext.automation.pageObjects.MessagingScreen;
import com.util.WebDriverController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import net.lightbody.bmp.core.har.Har;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import java.io.File;

import static com.tigertext.automation.common.StaticData.errorMessageStaticData;

public class TLSconnection {
    Logger logger = Logger.getLogger(Groups.class.getName());
    MobileAPI mobileWebdriver = new MobileAPI();
    Login login = new Login();

    @Given("^test$")
    public void onTheTTGetStartedScreen() throws Throwable {
        logger.info("logging test");


        WebDriverController.appium_driver.get("https://www.google.com");

        Har har = WebDriverController.server.getHar();
        File harFile = new File("target/harfile.har");
        harFile.createNewFile();
        har.writeTo(harFile);
        logger.info("har: "+har.toString());
        HarLog log = har.getLog();
        List<HarEntry> entries = new CopyOnWriteArrayList<HarEntry>(log.getEntries());
        logger.info("har entries: "+entries);
        for (HarEntry entry : entries){

            logger.info("har single entries: "+entry.getRequest().getUrl());
        }

        WebDriverController.server.stop();


    }

}