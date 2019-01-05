package poms;

import com.shaft.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class InstituteDashboard extends PageBase {

    By CalendarTabLink = By.id("calendarTab");


    public InstituteDashboard(WebDriver driver) {
        super(driver);
        jse = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }


    public void GotoCalendar() {

        ElementActions.click(driver, CalendarTabLink);

    }
}
