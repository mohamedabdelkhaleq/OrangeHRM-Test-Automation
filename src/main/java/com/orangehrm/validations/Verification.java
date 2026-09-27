package com.orangehrm.validations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

//Hard assertion
public class Verification extends BaseAssertion {
    public Verification(WebDriver driver) {
        super(driver);
    }
    @Override
    public void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    @Override
    public void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Override
    public void assertEquals(String actual, String expected, String message) {
    Assert.assertEquals(actual, expected, message);
    }
}
