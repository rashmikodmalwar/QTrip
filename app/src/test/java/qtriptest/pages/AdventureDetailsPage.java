
package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventureDetailsPage {
  RemoteWebDriver driver;
  public AdventureDetailsPage(RemoteWebDriver driver1){
    this.driver = driver1;
    this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
  }

  @FindBy(name = "name")
  WebElement name_text_box;
  
  @FindBy(name = "date")
  WebElement selected_date;

  @FindBy(name = "person")
  WebElement person_text_box;

  @FindBy(className = "reserve-button")
  WebElement reserve_button;

  @FindBy(id = "reserved-banner")
  WebElement reserve_success_message;
  


  public void bookAdventure(String name, String date,int numberOfPersons) throws InterruptedException{
    System.out.print("hiiiii");
    name_text_box.sendKeys(name);
    Thread.sleep(2000);
    selected_date.sendKeys(date);
    Thread.sleep(2000);
    person_text_box.clear();
    person_text_box.sendKeys(String.valueOf(numberOfPersons));
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.elementToBeClickable(reserve_button));
    reserve_button.click();
    System.out.print("hiiiii");
  }

  public boolean isBookingSuccessful(){
   if(reserve_success_message.isDisplayed()){
    return true;
   }
   return false;
    
  }


}