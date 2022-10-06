package qtriptest.pages;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class RegisterPage {

 RemoteWebDriver driver;
public String lastGeneratedUserName_;
public RegisterPage(RemoteWebDriver driver1){
    this.driver = driver1;
    this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
}
String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";

@FindBy(id = "floatingInput")
WebElement emailAdd_txt_box;

@FindBy(id = "floatingPassword")
WebElement password_txt_box;

@FindBy(name = "confirmpassword")
WebElement confirmPassword_txt_box;

@FindBy(xpath = "//button[text()='Register Now']")
WebElement btnRegister;

public void navigateToRegisterPage() {
    if (!driver.getCurrentUrl().equals(this.url)) {
        driver.get(this.url);
    }
}

public void registerUser(String name,String password,String confirmPassword,Boolean generateRandomUsername){

    String test_data_userName;
    if(generateRandomUsername){
        test_data_userName = name+UUID.randomUUID().toString();
    }else{
        test_data_userName = name;
    }
   
   emailAdd_txt_box.sendKeys(test_data_userName);
   password_txt_box.sendKeys(password);
   confirmPassword_txt_box.sendKeys(confirmPassword);
   btnRegister.click();
   lastGeneratedUserName_ = test_data_userName;
}
}
