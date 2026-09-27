package com.orangehrm;

import com.orangehrm.pages.pagescomponents.EmployeeManagementPage;
import com.orangehrm.pages.pagescomponents.LoginPage;
import com.orangehrm.drivers.GUIDriver;
import com.orangehrm.pages.pagescomponents.NavigationBarComponents;
import com.orangehrm.utils.WaitManager;
import com.orangehrm.utils.actions.BrowserActions;
import com.orangehrm.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static com.orangehrm.utils.logs.LogsManager.info;

public class BaseTest {
    public GUIDriver  driver = new GUIDriver();
    public LoginPage  loginPage;
    public NavigationBarComponents navigationBarComponents;
    public EmployeeManagementPage employeeManagementPage = new EmployeeManagementPage(driver);
    @BeforeTest
    public void setUp() {
        info("Setting up the test environment");
        driver.browser().navigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        //navigationBarComponents= loginPage.login("Admin", "admin123");
        info("Test environment setup complete");
    }

//
//    @AfterTest
//    public void tearDown() {
//       driver.quitDriver();
//    }
}
