package com.orangehrm.utils.actions;

import com.orangehrm.utils.WaitManager;
import com.orangehrm.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameActions {
    private final WebDriver driver;
    private final WaitManager waitManager;
    public FrameActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }
    public void switchToFrameByIndex(int index) {
        waitManager.getWait().until(driver -> {
            try {
                driver.switchTo().frame(index);
                LogsManager.info("Switched to frame by index: " + index);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
    /**
     * Switch By name or id of the frame
     * @param nameOrId name or id of the frame
     */
    public void switchToFrameByNameOrId(String nameOrId) {
        waitManager.getWait().until(driver -> {
            try {
                driver.switchTo().frame(nameOrId);
                LogsManager.info("Switched to frame by name: " + nameOrId);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
    /**
     * Switch by webElement of the frame
     * @param frameLocator webElement of the frame
     */
    public void switchToFrameByElement(By frameLocator) {
        waitManager.getWait().until(driver -> {
            try {
                driver.switchTo().frame(driver.findElement(frameLocator));
                LogsManager.info("Switched to frame: " + frameLocator);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
    /**
     * Switches back  to the Default Content of the page
     */
    public void setToDefaultContent() {
        waitManager.getWait().until(driver -> {
            try {
                driver.switchTo().defaultContent();
                LogsManager.info("Switched to default content");
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }


}
