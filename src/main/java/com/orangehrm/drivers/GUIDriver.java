package com.orangehrm.drivers;

import com.orangehrm.utils.actions.AlertActions;
import com.orangehrm.utils.actions.BrowserActions;
import com.orangehrm.utils.actions.ElementActions;
import com.orangehrm.utils.actions.FrameActions;
import com.orangehrm.utils.dataReader.PropertyReader;
import com.orangehrm.utils.logs.LogsManager;
import com.orangehrm.validations.Validation;
import com.orangehrm.validations.Verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIDriver {
    private final static String browserName = PropertyReader.getProperty("browser");
    private  ThreadLocal<WebDriver> driverThreadLocal=  new ThreadLocal<>();
    public GUIDriver() {
        LogsManager.info("Initializing GUIDriver with Browser: " + browserName);
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        LogsManager.info("Statring driver with browser " + browser);
        AbstractDriver driverFactory = browser.getDriverFactory();
        WebDriver driver =  ThreadGuard.protect(driverFactory.createDriver());
        driverThreadLocal.set(driver);
    }

    public  WebDriver get() {
        return driverThreadLocal.get();
    }
    public ElementActions element(){
        return new ElementActions(get());
    }
    public BrowserActions browser(){
        return new BrowserActions(get());
    }
    public FrameActions frame(){
        return new FrameActions(get());
    }
    public AlertActions  alert(){
        return new AlertActions(get());
    }
    //soft assertions
    public Validation validation() {
        return new Validation(get());
    }
    // hard assertions
    public Verification verification() {
        return new Verification(get());
    }
    public  void quitDriver() {
        driverThreadLocal.get().quit();
    }
}
