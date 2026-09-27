package com.orangehrm.tests.e2e;

import com.orangehrm.BaseTest;
import com.orangehrm.pages.pagescomponents.EmployeeManagementPage;
import com.orangehrm.pages.pagescomponents.LoginPage;
import com.orangehrm.utils.dataReader.JsonReader;
import com.orangehrm.utils.dataReader.PropertyReader;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
@Epic("End-to-End Flows")
@Feature("E2E 1 - Full HR Lifecycle")
public class E2E1_FullHrLifecycleTest extends BaseTest{
    // Shared state across steps
    JsonReader testEmployeedata = new JsonReader("employee.json");
    private String newUsername = "hruser_" + System.currentTimeMillis();
    private String newPassword = "Test@1234";
    private String empFirstName = "HRFirst";
    private String empLastName  = "HRLast";

    // ── Step 1: Login as admin ────────────────────────────────────────────
    @Test(priority = 1)
    @Story("")
    @Severity(SeverityLevel.BLOCKER)
    public void step1_loginAsAdmin() {
        navigationBarComponents = loginPage.login(PropertyReader.getProperty("admin.username"),
                PropertyReader.getProperty("admin.password")
        );

        Assert.assertTrue(driver.browser().getCurrentUrl().contains("dashboard"),
                "Should land on dashboard after login");
    }

    // ── Step 2: Add new employee via PIM ─────────────────────────────────
    @Test(priority = 2, dependsOnMethods = "step1_loginAsAdmin")
    @Story("Add employee")
    @Severity(SeverityLevel.CRITICAL)
    public void step2_addNewEmployee() {
        EmployeeManagementPage employeeManagementPage =navigationBarComponents.clickPimButton()
                .clickAddBTN()
                .addFirstName(testEmployeedata.getJsonData("validEmployee.first_name"))
                .addLastName(testEmployeedata.getJsonData("validEmployee.last_name"))
                .addEmployeeId(testEmployeedata.getJsonData("validEmployee.employee_id"))
                .clickSaveButton();


        Assert.assertEquals(testEmployeedata.getJsonData("validEmployee.first_name")+" "
                + testEmployeedata.getJsonData("validEmployee.last_name"),employeeManagementPage.getProfileLabel());
    }

    // ── Step 3: Create system user for that employee ──────────────────────
    @Test(priority = 3, dependsOnMethods = "step2_addNewEmployee")
    @Story("Create system user")
    @Severity(SeverityLevel.CRITICAL)
    public void step3_createSystemUser() {
        EmployeeManagementPage employeeManagementPage =navigationBarComponents.clickPimButton()
                .clickAddBTN()
                .addFirstName(testEmployeedata.getJsonData("executiveEmployee.first_name"))
                .addLastName( testEmployeedata.getJsonData("executiveEmployee.last_name"))
                .addEmployeeId(testEmployeedata.getJsonData("executiveEmployee.employee_id_"))
                .clickLoginDetails()
                .addUserName(testEmployeedata.getJsonData("executiveEmployee.username"))
                .addPassword(testEmployeedata.getJsonData("executiveEmployee.password"))
                .addConfirmPassword(testEmployeedata.getJsonData("executiveEmployee.password"))
                .clickSaveButton();


        Assert.assertTrue(driver.browser().getCurrentUrl().contains("auth/login"),
                "Should be redirected to login page after logout");

    }

    // ── Step 4: Logout from admin account ────────────────────────────────
    @Test(priority = 4, dependsOnMethods = "step3_createSystemUser")
    @Story("Admin logout")
    @Severity(SeverityLevel.NORMAL)
    public void step4_logout() {
        navigationBarComponents.clickMenuButton()
                        .clickLogoutButton();


        Assert.assertTrue(driver.browser().getCurrentUrl().contains("auth/login"),
                "Should be redirected to login page after logout");

    }


    // ── Step 5: Login as new user ─────────────────────────────────────────
    @Test(priority = 5, dependsOnMethods = "step4_logout")
    @Story("New user login")
    @Severity(SeverityLevel.CRITICAL)
    public void step5_loginAsNewUser() {
        loginPage.login(testEmployeedata.getJsonData("executiveEmployee.username"),
                testEmployeedata.getJsonData("executiveEmployee.password"));

        Assert.assertTrue(driver.browser().getCurrentUrl().contains("dashboard"),
                "Should land on dashboard after login");
    }

    // ── Step 6: Verify dashboard shows correct username ───────────────────
    @Test(priority = 6, dependsOnMethods = "step5_loginAsNewUser")
    @Story("Verify dashboard")
    @Severity(SeverityLevel.NORMAL)
    public void step6_verifyDashboardUsername() {
        String dashboardUsername = navigationBarComponents.clickPimButton().getProfileLabel();

        Assert.assertEquals(dashboardUsername, testEmployeedata.getJsonData("validEmployee.first_name")+" "
                        + testEmployeedata.getJsonData("validEmployee.last_name"),
                "Dashboard should display the correct username");

    }
    // ── Step 7: Logout and verify redirect ───────────────────────────────
    @Test(priority = 7, dependsOnMethods = "step6_verifyDashboardUsername")
    @Story("New user logout")
    @Severity(SeverityLevel.NORMAL)
    public void step7_logoutAndVerifyRedirect() {
        navigationBarComponents.clickMenuButton()
                .clickLogoutButton();

        Assert.assertTrue(driver.browser().getCurrentUrl().contains("auth/login"),
                "Should be redirected to login page after logout");
    }
}
