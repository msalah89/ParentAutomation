package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import poms.*;
import tests.TestBase;
import utilities.Credentials;

import java.util.UUID;


public class CreateEvent extends TestBase {
    HomePage HomePageObject;
    LoginPage LoginPageObject;
    InstituteDashboard instituteDashboardObject;
    InstituteCalendar instituteCalendarObject;
    CreateEventPage createEventPageObject;
    String eventName="test_event_9e85bfe5-1559-488f-adcd-f60a098e8120";

    @BeforeSuite

    @Given("^User Login with credentials$")
    public void user_Login_with_credentials() {
        // Write code here that turns the phrase above into concrete actions
        LoginPageObject = new LoginPage(driver);
        String userName = Credentials.UserName;
        String password = Credentials.Password;
        LoginPageObject.Login(userName, password);
    }

    @Given("^Go to create event page$")
    public void go_to_create_event_page() {
        // Write code here that turns the phrase above into concrete actions
        HomePageObject = new HomePage(driver);
        instituteDashboardObject = new InstituteDashboard(driver);
        instituteCalendarObject = new InstituteCalendar(driver);
        HomePageObject.verifyInstituteListLoaded();
        HomePageObject.GotoInstitute("Kids Palace");
        instituteDashboardObject.GotoCalendar();
        instituteCalendarObject.GotoCreateEvent();
    }


    @When("^Fill Event Form with \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",$")
    public void fill_Event_Form_with(String title, String description, String recipients, String dateString, String From, String To, String ReservationNeeded, String EventFor, String ReplyDeadLine) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        createEventPageObject = new CreateEventPage(driver);
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        this.eventName = title + "_" + randomUUIDString;
        String[] recipentsList = recipients.split(",");
        Boolean reservationNeeded = Boolean.parseBoolean(ReservationNeeded);
        createEventPageObject.FillEventForm(eventName, description, recipentsList, dateString, From, To, reservationNeeded, EventFor, ReplyDeadLine);


    }

    @Then("^I should find the event in calendar with date \"([^\"]*)\"$")
    public void i_should_find_the_event_in_calendar_with_date(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        int day =Integer.parseInt( arg1.split("/")[0]);
        int month =Integer.parseInt( arg1.split("/")[1]);

        instituteCalendarObject = new InstituteCalendar(driver);
        instituteCalendarObject.scrollTillDateReached(day, month);
        instituteCalendarObject.openEventPage(eventName);
    }

    @Then("^event details should have \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" ,\"([^\"]*)\" and \"([^\"]*)\"$")
    public void event_details_should_have_and(String date, String From, String To, String ReplyDate, String recipents) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
EventPage eventPage = new EventPage(driver, eventName,date,From,To,ReplyDate,recipents.split(","));

    }

    @AfterSuite
    @Then("^My \"([^\"]*)\" should appear beside the cart$")
    public void my_should_appear_beside_the_cart(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions


    }
}
