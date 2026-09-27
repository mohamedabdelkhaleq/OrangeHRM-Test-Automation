package com.orangehrm.utils.actions;

import com.orangehrm.utils.WaitManager;
import com.orangehrm.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertActions {
    private final WebDriver driver;
    private final WaitManager waitManager;
    public AlertActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }
    /**
     * Accepts Alert if present
     */
    public void acceptAlert() {
        waitManager.getWait().until(driver -> {
            try {
                driver.switchTo().alert().accept();
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to accept alert: " , e.getMessage());
                return false;
            }
        });
    }
    /**
     * Dismisses the Alert
     */
    public void dismissAlert() {
        waitManager.getWait().until(driver -> {
            try {
                driver.switchTo().alert().dismiss();
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to dismiss alert: " , e.getMessage());
                return false;
            }
        });

    
}
    /**
     * Gets the text of the Alert
     * @return String text of the Alert
     */
    public String getAlertText() {
        return waitManager.getWait().until(driver -> {
            try {
                String text =  driver.switchTo().alert().getText();
                return !text.isEmpty() ? text : null;

            } catch (Exception e) {
                LogsManager.error("Failed to get alert text: " , e.getMessage());
                return null;
            }
        });
    }
    public void setAlertText(String text) {
        waitManager.getWait().until(driver -> {
            try {
                driver.switchTo().alert().sendKeys(text);
                return true;
            }catch (Exception e) {
                LogsManager.error("Failed to set alert text: " , e.getMessage());
                return false;
            }
        });
    }

}