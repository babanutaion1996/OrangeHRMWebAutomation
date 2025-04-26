package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/recruitment.feature",
        glue = {"stepdefinitions","hooks"}, // path to stepdefictions and hooks
        dryRun = false,// to get unimplemented steps without running the script.
        monochrome = true,// just used for improving console readiblity
        plugin = {
                "pretty",// Prints gherking steps on the console
                "html:target/HTM-AutomationReports.html"// to create an html report
        }
)



public class TestRunner { }



