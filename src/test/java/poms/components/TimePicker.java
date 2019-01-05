package poms.components;

import com.shaft.element.ElementActions;
import com.shaft.validation.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TimePicker {
    WebDriver driver;
    String rootXPath;

    String dropdownPanelXPath;
    By rootElement = By.xpath("//timepicker");
    By timepickerBtn;

    By hourTxtBox = By.xpath("//*[@id='timepicker_0']//table//tbody//tr[2]//td[1]//input");
    By minuteTxtBox = By.xpath("//*[@id='timepicker_0']/table/tbody/tr[2]/td[3]/input");
    By ampmBtn = By.xpath("//*[@id='timepicker_0']/table/tbody/tr[2]/td[5]/button");


    public TimePicker(WebDriver driver, String formcontrolname) {
        this.driver = driver;
        rootXPath = "//*[@formcontrolname='" + formcontrolname + "']";
        timepickerBtn = By.xpath("//*[@formcontrolname='" + formcontrolname + "']//div//button");

        dropdownPanelXPath = rootXPath + "//*[contains(@class, 'ng-dropdown-panel-items')]";


    }


    public void SelectTime(String timeString) {

        int hour = Integer.parseInt(timeString.split(" ")[0].split(":")[0].trim());
        int minute = Integer.parseInt(timeString.split(" ")[0].split(":")[1].trim());
        String ampm = timeString.split(" ")[1].trim();

        ElementActions.click(driver, timepickerBtn);
        ElementActions.type(driver, hourTxtBox, String.valueOf(hour));
        ElementActions.type(driver, minuteTxtBox, String.valueOf(minute));
        if (!ElementActions.getText(driver, ampmBtn).toLowerCase().equals(ampm.toLowerCase())) {
            ElementActions.click(driver, ampmBtn);
        }
        ElementActions.click(driver, timepickerBtn);
        Assertions.assertElementAttribute(driver, timepickerBtn, "text", timeString, 1, true);
    }


}
