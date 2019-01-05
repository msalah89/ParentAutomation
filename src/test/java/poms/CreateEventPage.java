package poms;


import com.shaft.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import poms.components.NGCalendar;
import poms.components.NGSelect;
import poms.components.TimePicker;

public class CreateEventPage extends PageBase {
    By eventTitleTxtBox = By.id("eventTitle");
    By eventDescriptionTxtBox = By.id("eventDescription");
    By isReservationNeededSwitch = By.xpath("//prt-switcher[@formcontrolname='is_reservation_needed']//span[@class='switch__slider clickable']");
    By eventFor = By.xpath("//prt-drop-down-list[@formcontrolname='event_for']//input");
    By LoginBtn = By.id("submitBtn");
    NGSelect recipientSelect;
    NGCalendar datePicker;
    NGCalendar reservDeadlinePicker;
    TimePicker fromTimePicker;
    TimePicker toTimePicker;
    By saveBtn = By.xpath("//button[text()=' Save ']");

    public CreateEventPage(WebDriver driver) {
        super(driver);

        recipientSelect = new NGSelect(driver, "Recipients");
        datePicker = new NGCalendar(driver, "date");
        reservDeadlinePicker = new NGCalendar(driver, "reply_date");
        fromTimePicker = new TimePicker(driver, "start_time");
        toTimePicker = new TimePicker(driver, "end_time");

    }


    public void FillEventForm(String eventTitle, String eventDescription, String[] recipients, String dateString, String From, String To, Boolean ReservationNeeded, String EventFor, String ReplyDeadLine) throws InterruptedException {

        ElementActions.type(driver, eventTitleTxtBox, eventTitle);
        ElementActions.type(driver, eventDescriptionTxtBox, eventDescription);
        for (String recipient : recipients) {
            recipientSelect.selectValueFromDropDownList(recipient);
        }
//date[0] = day , date[1] = month , date[2] = year
        datePicker.selectDate(dateString);


        fromTimePicker.SelectTime(From);
        toTimePicker.SelectTime(To);

        ElementActions.click(driver, isReservationNeededSwitch);

        ElementActions.click(driver, eventFor);

        WebElement eventForOption = driver.findElement(By.xpath("//div[contains(text(),'" + EventFor + "')]"));

        eventForOption.click();
        reservDeadlinePicker.selectDate(ReplyDeadLine);
        ElementActions.click(driver, saveBtn);

    }


    public void logout() {

        driver.navigate().to("https://egypt.souq.com/eg-en/logout.php");

    }

}
