package app.tix.automate.test.connectdevice;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BaseConfigDeviceFarm {


    @Bean("scenarioName")
    @Scope("prototype")
    public DesiredCapabilities setupDriverConfig(String scenarioName){
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
        return caps;
    }

}
