package poms;


import com.shaft.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {
    HomePage HomePage;
    By EmailTextBox = By.id("txtEmail");
    By PasswordTextBox = By.id("txtPassword");
    By LoginBtn = By.id("submitBtn");

    public LoginPage(WebDriver driver) {
        super(driver);
    }



    public void Login(String email, String password) {

        ElementActions.type(driver, EmailTextBox, email);
        ElementActions.type(driver, PasswordTextBox, password);
        ElementActions.click(driver, LoginBtn);

    }



}
