package app.tix.automate.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "",
        glue = "app.tix.automate.test",
        features = {"src/test/java/app/tix/automate/test/features/tixid.feature"},
        //features={"classpath:features/"},
        plugin   = {"pretty"}
        )
@ContextConfiguration("classpath:*Cucumber.xml")
public class AppTixIDRunner   {

}
