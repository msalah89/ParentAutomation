package poms.components;

import com.shaft.element.ElementActions;
import com.shaft.validation.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class NGSelect {
    WebDriver driver;
    String rootXPath;
    String inputXPath;
    String dropdownPanelXPath;
    By rootElement;
    By textInput;
    By ddItemsList;

    public NGSelect(WebDriver driver, String id) {
        this.driver = driver;
        rootXPath = "//*[@id='" + id + "']";
        inputXPath = rootXPath + "//input";
        dropdownPanelXPath = rootXPath + "//*[contains(@class, 'ng-dropdown-panel-items')]";
        rootElement = By.xpath(rootXPath);
        textInput = By.xpath(inputXPath);
        ddItemsList = By.xpath(dropdownPanelXPath);


    }

    By dropDownOption(String selectValue) {
        return By.xpath("(//div[contains(@class, 'option-wrapper') and contains(.//div, '" + selectValue + "')])[1]");


    }
    By selectedOption(String selectValue) {
        return By.xpath("//div[contains(@class, 'ng-value-wrapper') and contains(., '" + selectValue + "')]");


    }
    public void selectValueFromDropDownList(String Value) {


        String searchTerm = Value.split(" ")[0];

        By selectOption = dropDownOption(Value);
        By selectedOption = selectedOption(Value);
        ElementActions.type(driver, textInput, searchTerm);


        ElementActions.click(driver, selectOption);

         Assertions.assertElementExists(driver,selectedOption,true);

    }




}
