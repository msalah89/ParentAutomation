package testPackage01;

import cucumber.api.CucumberOptions;
import org.testng.annotations.Test;
import tests.TestBase;


@CucumberOptions(features = "src/test/java/features"
        , glue = {"steps"}
        , plugin = {"pretty", "html:target/cucumber-html-report"})
@Test
public class CucumberTestRunner extends TestBase {

}