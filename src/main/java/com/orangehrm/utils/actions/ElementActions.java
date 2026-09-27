package com.orangehrm.utils.actions;
import com.orangehrm.utils.WaitManager;
import com.orangehrm.utils.logs.LogsManager;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import java.io.File;

public class ElementActions {
    private WebDriver driver;
    private WaitManager waitManager;
    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);}

    public void type(By locator, String text) {
        waitManager.getWait().until(driver ->{
            try {
                WebElement element = getElement(locator);
                scrollToElementJS(locator);
                element.sendKeys(Keys.CONTROL + "a");
                element.sendKeys(Keys.DELETE);
                element.sendKeys(text);
                LogsManager.info("Text entered: " , text , " into element: " + locator);
                return true;
            }catch (Exception e){
                return false;
            }
        } );

    }

    public void click(By locator) {
        waitManager.getWait().until(driver ->
        {
          try {
                WebElement element = getElement(locator);
                scrollToElementJS(locator);
                element.click();
              LogsManager.info("Clicked on element: " + locator);
                return true;
            } catch (Exception e) {
                    return false;
                }
        }

        );
    }



    public String getText(By locator) {
        return waitManager.getWait().until(driver -> {
            try {
                WebElement element = getElement(locator);
                scrollToElementJS(locator);
                String text = element.getText();
                LogsManager.info("Text retrieved: " , text , " from element: " + locator);
                return !text.isEmpty() ? text : null;


            }catch (Exception e){
                return null;
            }
        }
        );
    }

    public void scrollToElementJS(By locator) {
        WebElement element = getElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'center'});",
                element
        );
    }

    public void uploadFile(By locator, String filePath) {
        String absolutePath = System.getProperty("user.dir") + File.separator + filePath;
        waitManager.getWait().until(driver -> {
            try {
                WebElement element = getElement(locator);
                scrollToElementJS(locator);
                element.sendKeys(absolutePath);
                LogsManager.info("Uploaded file: " + absolutePath + " into element: " + locator);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }
    public boolean IsVisible (By locator) {
       return waitManager.getWait().until(driver -> {
            try {
                WebElement element = getElement(locator);
                scrollToElementJS(locator);
                element.isDisplayed();
                LogsManager.info("Element is displayed: " + locator);
                return true;
            }catch (Exception e){
                LogsManager.info("Element is not displayed: " + locator);
                return false;
            }
        });
    }

}
