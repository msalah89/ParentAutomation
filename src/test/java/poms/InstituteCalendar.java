package poms;

import com.shaft.element.ElementActions;
import com.shaft.validation.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Helper;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class InstituteCalendar extends PageBase {

    By createEventBtn = By.name("createEventBtn");
    By weekDays = By.cssSelector("h6.week-days");

    public InstituteCalendar(WebDriver driver) {
        super(driver);
        jse = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }


    public String dateReached(int day, int month) {

        AtomicReference<String> weekDayString = new AtomicReference<>("");
        List<WebElement> weekDaysList = driver.findElements(weekDays);
        weekDaysList.forEach(weekDay -> {
            String endOfWeek = weekDay.getText().split("-")[1].trim();
            int endDay = Integer.parseInt(endOfWeek.split(" ")[0]);
            String endMonthString = endOfWeek.split(" ")[1];
            int endMonth = Helper.getMonthNumber(endMonthString);
            if (endMonth >= month && endDay >= day) {

                weekDayString.set(weekDay.getText());
            }
        });

        return weekDayString.get();

    }

    public void scrollTillDateReached(int day, int month) {
        String weekFound = "";
        while (weekFound.isEmpty()) {

            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");

            weekFound = dateReached(day, month);


        }
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");

        By dayWeekLocator = By.xpath("//*[contains(text(),'" + weekFound + "')]");
        Assertions.assertElementExists(driver, dayWeekLocator, true);


    }

    public void openEventPage(String eventName) {

        By eventLocator = By.xpath("//span[contains(text(),'" + eventName + "')]");
        Assertions.assertElementExists(driver, eventLocator, true);
        ElementActions.click(driver, eventLocator);
    }

    public void GotoCreateEvent() {

        ElementActions.click(driver, createEventBtn);

    }
}
