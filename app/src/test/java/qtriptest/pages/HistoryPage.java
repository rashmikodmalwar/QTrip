
package qtriptest.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HistoryPage {
    RemoteWebDriver driver;
    @FindBy(xpath = "//table//tbody//tr//th")
    List<WebElement> transactionId;

    @FindBy(xpath = "//button[text()='Cancel']")
    WebElement cancelbtn;

    @FindBy(xpath ="//strong[text()='here']")
    WebElement clickToViewHistory;
   
    public HistoryPage(RemoteWebDriver driver1){
        this.driver = driver1;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
       
    } 
  
    
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/";

    public void navegateToRservation(){
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(url);
        }
    }
   
    public void navigateHistoryPage(){
        clickToViewHistory.click();
    }

    public List<WebElement> getReservations(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfAllElements(transactionId));
      return transactionId;
    }

    public void cancelReservation(String transaction) throws InterruptedException{
        for(int j = 0;j<transactionId.size();j++){
           if(transactionId.get(j).getText().equals(transaction)){
            cancelbtn.click();
           
           }
        }
    }

    // public boolean isReserVationCancel(String transaction){
      
    //    for(int k=0;k<transactionId.size();k++){
    //     if(!transactionId.get(k).getText().equalsIgnoreCase(transaction)){
    //         System.out.println("hhhhhh");
    //         return true;
            
    //     }
    //    }
    //    System.out.println("hhhhhh11");
    //    return false;
    // }
    
}