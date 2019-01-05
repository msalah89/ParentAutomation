package poms;

 
import java.util.Set;

import com.shaft.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageBase
{
	public LoginPage(WebDriver driver) {
		super(driver);
	}

    HomePage HomePage ;
    

    By EmailTextBox  = By.id("txtEmail");
    

    By PasswordTextBox  = By.id("txtPassword");
    By LoginBtn = By.id("submitBtn");

    
    ///  <summary>
    ///      Login in Souq.com using email and password
    ///  </summary>
    ///  <param name="email"> Souq.com user email address</param>
    ///  <param name="password">Souq.com user password</param>
    public   void Login(String email, String password) {

        ElementActions.type(driver,EmailTextBox,email);
        ElementActions.type(driver,PasswordTextBox,password);
       ElementActions.click(driver,LoginBtn);
    
    }
    


    
    public void logout() {
    	
    	driver.navigate().to("https://egypt.souq.com/eg-en/logout.php");
    	
    }

}
