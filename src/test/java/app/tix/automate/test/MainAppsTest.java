package app.tix.automate.test;


import app.tix.automate.automate.MainApps;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;



@SpringBootTest(classes = MainApps.class)
@ComponentScan({"app.tix.automate.config"})
@CucumberContextConfiguration
public class MainAppsTest {

}
