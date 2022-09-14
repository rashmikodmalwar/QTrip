package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
  RemoteWebDriver driver;
    public HomePage(RemoteWebDriver driver1){
        this.driver = driver1;
        driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
    }
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    @FindBy(xpath="//a[@class='nav-link login register']")
    WebElement register_btn;

    @FindBy(xpath ="//div[text()='Logout']")
    WebElement logout_btn;

    @FindBy(xpath ="//div[text()='Logout']")
    WebElement logout_lable;

    @FindBy(xpath = "//input[@id='autocomplete']")
    WebElement searchCity_txt_box;

    @FindBy(xpath ="//ul[@id='results']//child::li")
    WebElement select_Auto_City;

    @FindBy(xpath = "//ul[@id='results']")
    WebElement no_city_found;

    public void goToHomePage(){
      if(!driver.getCurrentUrl().equals(this.url)){
        driver.get(url);
    }
  }
    public void clickRegister(){
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        register_btn.click();
    }

    public boolean isUserLoggedIn(){
      try{
        return logout_btn.isDisplayed();
     }
     catch(Exception ex){
         return false;
     }
    }

    public void logoutUser(){
      logout_btn.click();
    }

    public void searchCity(String city){
       searchCity_txt_box.clear();
       searchCity_txt_box.sendKeys(city);
    }

    public boolean noCityFound(){
      try{
        return no_city_found.isDisplayed();
      }
      catch(Exception ex)
      {
        return false;
      }

    }
    
    public boolean assertAutoCompleteText(String city){
      if(select_Auto_City.getText().equals(city)){
        return true;
      }
      else
      return false;
    }
    public void selectCity(String autoCompleteCity){
     select_Auto_City.click();
    }

    public Boolean verifyUserLoggedIn()
    {
        try{
           return logout_lable.isDisplayed();
        }
        catch(Exception ex){
            return false;
        }
       
    }

}
