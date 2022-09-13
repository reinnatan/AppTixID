package app.tix.automate.test.connectdevice;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.URL;

@Configuration
public class BaseConfigDeviceFarm {

    ThreadLocal<AndroidDriver<MobileElement>> driver = new ThreadLocal<>();

    @Bean//("scenarioName")
    @Scope("prototype")
    public AndroidDriver setupDriverConfig(String scenarioName){
        System.out.println("Bean Scenario Dipanggil "+scenarioName);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Android");
        caps.setCapability("appPackage", "id.tix.android");
        caps.setCapability("appActivity", "id.tix.android.splash.view.SplashActivity");
        caps.setCapability("platformName", "Android");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("platformVersion", "13.0");
        caps.setCapability("noReset", true);
        caps.setCapability("build",scenarioName);

        try {
            driver.set( new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps));
            return driver.get();
        }catch (Exception e){
            System.out.println("Terjadi Error "+e.getMessage());
            return null;
        }
    }

}
