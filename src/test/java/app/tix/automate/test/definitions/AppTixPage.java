package app.tix.automate.test.definitions;

import app.tix.automate.automate.page.RegisterPage;
import app.tix.automate.test.connectdevice.BaseConfigDeviceFarm;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URL;


public class AppTixPage {
    @Autowired
    private RegisterPage config;

    By allowButton = By.id("com.android.permissioncontroller:id/permission_allow_one_time_button");
    By selectCityText = By.id("id.tix.android:id/tv_city");
    By searchCityTextBox = By.id("id.tix.android:id/edt_search");
    By selectCitySearchBox = By.id("id.tix.android:id/tvCityName");//and contains(@text(),'BEKASI'
    By backButton = By.id("id.tix.android:id/navigation_theater");

    //select navigation bioscope
    By bioscopeBottomButtonTab = By.id("id.tix.android:id/navigation_theater");

    WebDriverWait wait;
    AndroidDriver driver;

    @Before
    public void fungsiDipanggil(Scenario scenario){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        //System.out.println("Fungsi Dipanggil "+nameofCurrMethod);

        ApplicationContext context
                = new AnnotationConfigApplicationContext(
                BaseConfigDeviceFarm.class);

        try {
            driver = (AndroidDriver) context.getBean("scenarioName", scenario.getName().replaceAll(" ",""));
            wait = new WebDriverWait(driver, 6);
        }catch (Exception e){
            System.out.println("Terjadi Error "+e.getMessage());
        }
    }

    @Given("Open AppTixId apps")
    public void openAppTixIdDashboard(){
        System.out.println("Open AppTixId apps");
        //boolean display = config.wait.until(ExpectedConditions.visibilityOfElementLocated(allowButton)).isDisplayed();
        //if(display) {
        //    config.wait.until(ExpectedConditions.visibilityOfElementLocated(allowButton)).click();
        //}

    }

    @And("Search city region Bekasi")
    public void searchCityRegionBekasi(){

        System.out.println("Search city region bekasi");
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectCityText)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchCityTextBox)).sendKeys("Bekasi");
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectCitySearchBox)).click();
        //config.wait.until(ExpectedConditions.visibilityOfElementLocated(backButton)).click();
    }

    @And("Click cinema icon")
    public void clickCinemaIcon(){
        System.out.println("Click cinema icon");
        wait.until(ExpectedConditions.visibilityOfElementLocated(bioscopeBottomButtonTab)).click();
        By mallBekasiText = By.xpath("//android.widget.TextView[@resource-id='id.tix.android:id/tv_cinema_name' and @text='SUMMARECON MAL BEKASI XXI']");

        while (driver.findElements(mallBekasiText).size()==0){
            Dimension size = driver.manage().window().getSize();
            int starty = (int) (size.height * 0.80);
            int endy = (int) (size.height * 0.20);
            int startx = size.width / 2;
            System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
            driver.swipe(startx, starty, startx, endy, 3000);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(mallBekasiText)).click();
    }

    @Then("Validate information movies and bioscope")
    public void validateInformationMoviesAndBioscope() throws InterruptedException {

        System.out.println("validate information movies and bioscope");
        String bioscopeContact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id.tix.android:id/tv_contact"))).getText();
        Assert.assertEquals("(021)29572421", bioscopeContact);
    }

    @After
    public void dispatchDriver(){
        driver.quit();
    }


}
