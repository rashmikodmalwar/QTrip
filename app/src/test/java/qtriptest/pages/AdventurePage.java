package qtriptest.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventurePage {
    RemoteWebDriver driver;
    //String url="https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?city=goa";

    @FindBy(id = "duration-select")
    WebElement select_duration_dropdown;

    @FindBy(id = "category-select")
    WebElement select_category_dropdown;

    @FindBy(id = "search-adventures")
    WebElement search_adventure_text_box;

    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    List<WebElement> getImageCount;

    @FindBy(xpath = "//div[@id='data']//div[@class='activity-card']//h5")
    List<WebElement> getPlaceText;

    @FindBy(xpath ="(//div[@class='ms-3']//parent::div)[2]")
    WebElement clear_duration;
    //(//div[@class='ms-3']//parent::div)[2]
    //div[@onclick='clearDuration(event)']
   

    @FindBy(xpath ="//div[@onclick='clearCategory(event)']")
    WebElement clear_Category;
    //(//div[@class='ms-3']//parent::div)[4]
   
    public AdventurePage(RemoteWebDriver driver1){
        this.driver = driver1;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
    }
    
    public void setFilterValueDuration(String filter){
     
        Select dropdown = new Select(select_duration_dropdown);
        dropdown.selectByVisibleText(filter);

    }

    public void setFilterValueCategory(String filter){
        
        Select dropdown = new Select(select_category_dropdown);
        dropdown.selectByVisibleText(filter);

    }
   
    public void clearFilterDuration(){
        if(clear_duration.isEnabled()){
            clear_duration.click();
        }
        

    }

    public void clearFilterCategory(){
       if(clear_Category.isEnabled()){
        clear_Category.click();;

       }
        
    }

    public int getResultCount(){
       // int countsize=0;
   WebDriverWait wait = new WebDriverWait(driver, 30);
   wait.until(ExpectedConditions.visibilityOfAllElements(getImageCount));
    return getImageCount.size();
  
}

    public void selectAdventure(String placeName){
        for(int i=0;i<getPlaceText.size();i++){
            if(getPlaceText.get(i).getText().equalsIgnoreCase(placeName)){
                getPlaceText.get(i).click();
            }
        }
    }

}