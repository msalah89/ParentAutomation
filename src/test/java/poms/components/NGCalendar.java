package poms.components;

import com.shaft.element.ElementActions;
import com.shaft.validation.Assertions;
import com.shaft.validation.Verifications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DateFormatSymbols;
import java.util.Locale;

public class NGCalendar {


    WebDriver driver;
    String calendarRootPath;
    String rootXPath="//bs-datepicker-container";
    By rootElement;
    By calendarBody = By.xpath(rootXPath);
    By currentYear =By.xpath( rootXPath+"//bs-datepicker-navigation-view/button[3]");
    By currentMonth = By.xpath( rootXPath+"//bs-datepicker-navigation-view/button[2]");
    By calendarNextBtn = By.cssSelector("bs-datepicker-navigation-view > button.next");
    By calendarPrevBtn = By.cssSelector("bs-datepicker-navigation-view > button.previous");
    By selectedDayLocator(int day){

        return  By.xpath("(//bs-datepicker-container//td[normalize-space()='"+day+"'])[1]");
    }
    Boolean isCalendarOpened = false;
    public NGCalendar(WebDriver driver, String formcontrolname) {
        this.driver = driver;
        calendarRootPath = "//prt-date-picker[@formcontrolname='" + formcontrolname + "']";

        rootElement = By.xpath(calendarRootPath);


    }





    public String getMonth(int month) {
        Locale locale = new Locale("en", "US");
        return new DateFormatSymbols(locale).getMonths()[month - 1];
    }
    public void openCalendar() {
        ElementActions.click(driver, rootElement);

        Assertions.assertElementExists(driver, calendarBody, true);
        this.isCalendarOpened = true;
    }

    public void selectYearAndMonth(int year, int month) {
        String monthString = getMonth(month);

        String yearString = String.valueOf(year);
        String calendarCurrentYear = ElementActions.getText(driver, currentYear);




        while (!driver.findElement (currentYear).getText().equals(yearString)  || !driver.findElement(currentMonth).getText().equals(monthString)) {

                   driver.findElement(calendarNextBtn).click();

            }




        Assertions.assertElementAttribute(driver, currentYear, "text", yearString, 2, true);
        Assertions.assertElementAttribute(driver, currentMonth, "text", monthString, 2, true);


    }

    public void selectDate(String dateString) {
        int day = Integer.parseInt(dateString.split("/")[0]);
        int month = Integer.parseInt(dateString.split("/")[1]);
        int  year = Integer.parseInt(dateString.split("/")[2]);

        openCalendar();
        selectYearAndMonth(year, month);
        // Select Day
        Verifications.verifyElementExists(driver, selectedDayLocator(day),true);
        ElementActions.click(driver, selectedDayLocator(day));

    }

    By dropDownOption(String selectValue) {
        return By.xpath("//div[contains(@class, 'option-wrapper') and contains(.//div, '" + selectValue + "')]");


    }

    By selectedOption(String selectValue) {
        return By.xpath("//div[contains(@class, 'ng-value-wrapper') and contains(., '" + selectValue + "')]");

    }


}
