package qtriptest.pages;

import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class LoginPage {
    RemoteWebDriver driver;
    public LoginPage(RemoteWebDriver driver1){
        this.driver = driver1;
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
    }
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login";
    
    @FindBy(id = "floatingInput")
    WebElement emailAdd_txt_box;
    
    @FindBy(id = "floatingPassword")
    WebElement password_txt_box;
    
    @FindBy(className = "btn-login")
    WebElement btnLogin;
    
   

    public void performLogin(String username, String password) throws InterruptedException{
        emailAdd_txt_box.sendKeys(username);
        password_txt_box.sendKeys(password);
        btnLogin.click();
        Thread.sleep(2000);
    }

   

}
    

