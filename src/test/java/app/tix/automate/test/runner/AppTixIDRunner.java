package app.tix.automate.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "",
        glue = "app.tix.automate.test",
        features = {"src/test/java/app/tix/automate/test/features/"},
        //features={"classpath:features/"},
        plugin   = {"pretty"}
        )
public class AppTixIDRunner   {

}
