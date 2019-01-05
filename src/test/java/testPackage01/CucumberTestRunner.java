package testPackage01;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import tests.TestBase;;



@CucumberOptions(features="src/test/java/features"
        ,glue= {"steps"}
        ,plugin= {"pretty","html:target/cucumber-html-report"})
@Test
public class CucumberTestRunner extends TestBase {

}