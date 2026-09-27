package com.orangehrm.validations;

import com.orangehrm.utils.WaitManager;
import com.orangehrm.utils.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseAssertion {
    protected final WebDriver driver;
    protected final WaitManager waitManager;
    protected final ElementActions  elementActions;
    public BaseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);

    }
    protected abstract void assertTrue(boolean condition , String message);
    protected abstract void assertFalse(boolean condition,String message);
    protected abstract void assertEquals(String actual,String expected ,String message);

    protected void isElementVisible(By locator) {
       boolean flag =  waitManager.getWait().until(driver -> {
            try {
                assertTrue(driver.findElement(locator).isDisplayed(), "Element is not displayed");
                return true;

            }catch (Exception e) {
            return false;
            }


        });
       assertTrue(flag, "Element is visible" + locator);
    }
    protected void assertPageUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl, "Page URL is correct");
    }


}

