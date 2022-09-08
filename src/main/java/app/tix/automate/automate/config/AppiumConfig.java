package app.tix.automate.automate.config;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;

@Component
public class AppiumConfig {
    public WebDriverWait wait;
    public AndroidDriver<MobileElement> driver;

    @PostConstruct
    public void AppiumConfig() {

        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("deviceName", "Android");
            caps.setCapability("appPackage", "id.tix.android");
            caps.setCapability("appActivity", "id.tix.android.splash.view.SplashActivity");
            caps.setCapability("platformName", "Android");
            caps.setCapability("udid", "emulator-5554");
            caps.setCapability("platformVersion", "13.0");
            caps.setCapability("noReset", true);
            driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
            wait = new WebDriverWait(driver, 6);

        }catch (Exception e){
            System.out.println("Terjadi Error "+e.getMessage());
            driver.quit();
        }
    }

    @Before
    public void setupConfig(){


    }

    @After
    public void dispatchConfig(){
        driver.quit();
    }
}
