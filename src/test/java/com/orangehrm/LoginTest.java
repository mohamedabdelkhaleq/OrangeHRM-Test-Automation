package com.orangehrm;
import com.orangehrm.drivers.GUIDriver;
import com.orangehrm.pages.pagescomponents.EmployeeManagementPage;
import com.orangehrm.pages.pagescomponents.LoginPage;
import com.orangehrm.utils.dataReader.JsonReader;
import com.orangehrm.utils.logs.LogsManager;
import org.testng.Assert;
import org.testng.annotations.*;
import static com.orangehrm.utils.logs.LogsManager.info;
import static org.testng.AssertJUnit.assertTrue;

public class LoginTest extends BaseTest {
    JsonReader testdata = new JsonReader("login.json");
    @Test
    public void testValidLogin() {
        loginPage.addUserName(testdata.getJsonData("ValidCredentials.username"))
                .addPassword(testdata.getJsonData("ValidCredentials.username"))
                .clickLoginButton();

        LogsManager.info("Login Button clicked");











        Assert.assertTrue(driver.browser().getCurrentUrl().contains("dashboard"),
                "Should land on dashboard after login");
    }
    @Test
    public void testInvalidLoginwithinvalidUsername() {
        loginPage.addUserName(testdata.getJsonData("invalidUsername.username"))
                .addPassword(testdata.getJsonData("invalidUsername.password"))
                .clickLoginButton();
        LogsManager.info("Login Button clicked");

        Assert.assertTrue(loginPage.isInvalidErrorMessageVisible(),
                "isInvalidErrorMessageVisible");
    }
    @Test
    public void testInvalidPassword() {
        loginPage.addUserName(testdata.getJsonData("invalidpassword.username"))
                .addPassword(testdata.getJsonData("invalidpassword.password"))
                .clickLoginButton();

        LogsManager.info("Login Button clicked");
       Assert.assertTrue(loginPage.isInvalidErrorMessageVisible());
    }
    @Test
    public void EmptyFields() {
        loginPage.addUserName(testdata.getJsonData("EmptyFields.username"))
                 .addPassword(testdata.getJsonData("EmptyFields.password"))
                 .clickLoginButton();

        LogsManager.info("Login Button clicked");
        assertTrue(loginPage.isRequiredFiledErrorVisible());
    }
    @Test
    public void SQLInjection() {
        loginPage.addUserName(testdata.getJsonData("SQLInjection.username"))
                .addPassword("SQLInjection.password")
                .clickLoginButton();

        LogsManager.info("Login Button clicked");

        assertTrue(loginPage.isInvalidErrorMessageVisible());
    }
    @Test
    public void testXSSInjectionInUsername() {
        loginPage.addUserName(testdata.getJsonData("XSSInjection.username"))
                .addPassword(testdata.getJsonData("XSSInjection.password"))
                .clickLoginButton();

        LogsManager.info("Login Button clicked with XSS payload");

        assertTrue(loginPage.isInvalidErrorMessageVisible());
    }
    //configurations
//    @BeforeMethod
//    public void setUp() {
//        driver = new GUIDriver();
//        info("Setting up the test environment");
//        driver.browser().navigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        loginPage = new LoginPage(driver);
//        employeeManagementPage = new EmployeeManagementPage(driver);
//        info("Test environment setup complete");
//    }
//    @AfterTest
//    public void tearDown() {
//        driver.quitDriver();
//    }

}

