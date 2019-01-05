package poms;

import com.shaft.element.ElementActions;
import com.shaft.validation.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends PageBase {
    String Institute = "";
    By InstitutesListDiv = By.className("institution-details__header");
    By AllCategoriesLink = By.partialLinkText("All Categories");

    public HomePage(WebDriver driver) {
        super(driver);
        jse = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    public By InstituteLink(String institute) {

        return By.xpath("//div[contains(@class, 'institution__name') and normalize-space(text()) = '" + institute + "']"
        );
    }

    public void verifyInstituteListLoaded() {

        Assertions.assertElementExists(driver, InstitutesListDiv, true);


    }

    public void GotoInstitute(String institute) {

        By instituteLink = InstituteLink(institute);

        Assertions.assertElementExists(driver, instituteLink, true);
        ElementActions.click(driver, instituteLink);

    }
}
