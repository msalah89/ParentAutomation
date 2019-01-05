package poms;


import com.shaft.validation.Assertions;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Helper;

public class EventPage extends PageBase {
    By eventTitle;

    By eventDateSpan;
    By FromToSpan;
    By replyDateSpan;
    By invitedSpan;


    public EventPage(WebDriver driver, String eventtitle, String eventDate, String From, String To, String ReplyDate, String[] recipents) {
        super(driver);

        String date = Helper.twoDigits(eventDate.split("/")[0]) + " " + StringUtils.substring(Helper.getMonth(Integer.parseInt(eventDate.split("/")[1])), 0, 3)
                + " " + StringUtils.substring(eventDate.split("/")[2], 2, 4);
        String replyDate = Helper.twoDigits(ReplyDate.split("/")[0]) + " " + StringUtils.substring(Helper.getMonth(Integer.parseInt(ReplyDate.split("/")[1])), 0, 3)
                + " " + StringUtils.substring(ReplyDate.split("/")[2], 2, 4);
        String fromto = From.toLowerCase() + " - " + To.toLowerCase();
        String recipentsString = String.join(", ", recipents);

        eventTitle = By.xpath("//h1[contains(text(),'" + eventtitle + "')]");
        eventDateSpan = By.xpath("//span[contains(text(),'" + date + "')]");
        replyDateSpan = By.xpath("//span[contains(text(),'" + replyDate + "')]");
        FromToSpan = By.xpath("//span[contains(text(),'" + fromto + "')]");
        invitedSpan = By.xpath("//span[contains(text(),'" + recipentsString + "')]");
        Assertions.assertElementExists(driver, eventTitle, true);

        Assertions.assertElementExists(driver, eventDateSpan, true);
        Assertions.assertElementExists(driver, FromToSpan, true);
        Assertions.assertElementExists(driver, replyDateSpan, true);
        Assertions.assertElementExists(driver, invitedSpan, true);


    }


}
