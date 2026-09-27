package com.orangehrm.utils.actions;

import com.orangehrm.utils.logs.LogsManager;
import org.openqa.selenium.*;

public class BrowserActions {
    private final WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }
    /**
    * Maximize Window
    */
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }
    /**
     * Refresh Page
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * get Current Web page's URL
     */
    public String getCurrentUrl() {
        String url =  driver.getCurrentUrl();
        LogsManager.info("Current URL: " + url);
        return url;
    }

    /**
     * Navigate to a specific URL
     */
    public void navigateTo(String url) {
        driver.get(url);
        LogsManager.info("Navigated to: " + url);
    }

    /**
     * Navigate Back to a Specific URL
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * Navigate Forward to a Specific URL
     */
    public void navigateForward() {
        driver.navigate().forward();
    }

    /**
     * Close Current Window
     */
    public void closeCurrentWindow() {
        driver.close();
    }
    /**
     * Open a new window
     */
    public void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }
}
