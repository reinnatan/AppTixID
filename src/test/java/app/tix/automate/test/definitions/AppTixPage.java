package app.tix.automate.test.definitions;

import app.tix.automate.automate.config.AppiumConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class AppTixPage {
    @Autowired
    private AppiumConfig config;

    By allowButton = By.id("com.android.permissioncontroller:id/permission_allow_one_time_button");
    By selectCityText = By.id("id.tix.android:id/tv_city");
    By searchCityTextBox = By.id("id.tix.android:id/edt_search");
    By selectCitySearchBox = By.id("id.tix.android:id/tvCityName");//and contains(@text(),'BEKASI'
    By backButton = By.id("id.tix.android:id/navigation_theater");

    //select navigation bioscope
    By bioscopeBottomButtonTab = By.id("id.tix.android:id/navigation_theater");

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
        config.wait.until(ExpectedConditions.visibilityOfElementLocated(selectCityText)).click();
        config.wait.until(ExpectedConditions.visibilityOfElementLocated(searchCityTextBox)).sendKeys("Bekasi");
        config.wait.until(ExpectedConditions.visibilityOfElementLocated(selectCitySearchBox)).click();
        //config.wait.until(ExpectedConditions.visibilityOfElementLocated(backButton)).click();
    }

    @And("Click cinema icon")
    public void clickCinemaIcon(){
        System.out.println("Click cinema icon");
        config.wait.until(ExpectedConditions.visibilityOfElementLocated(bioscopeBottomButtonTab)).click();
        By mallBekasiText = By.xpath("//android.widget.TextView[@resource-id='id.tix.android:id/tv_cinema_name' and @text='SUMMARECON MAL BEKASI XXI']");

        while (config.driver.findElements(mallBekasiText).size()==0){
            Dimension size = config.driver.manage().window().getSize();
            int starty = (int) (size.height * 0.80);
            int endy = (int) (size.height * 0.20);
            int startx = size.width / 2;
            System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
            config.driver.swipe(startx, starty, startx, endy, 3000);
        }

        config.wait.until(ExpectedConditions.visibilityOfElementLocated(mallBekasiText)).click();
    }

    @Then("Validate information movies and bioscope")
    public void validateInformationMoviesAndBioscope() throws InterruptedException {
        System.out.println("validate information movies and bioscope");
        String bioscopeContact =  config.driver.findElement(By.id("id.tix.android:id/tv_contact")).getText();
        Assert.assertEquals("(021)29572421", bioscopeContact);
    }
}
