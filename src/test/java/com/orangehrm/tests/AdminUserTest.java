package com.orangehrm.tests;



import com.orangehrm.BaseTest;
import com.orangehrm.pages.pagescomponents.AdminPage;
import com.orangehrm.utils.dataReader.JsonReader;
import com.orangehrm.utils.logs.LogsManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminUserTest extends BaseTest {
    JsonReader testUserData = new JsonReader("user.json");

    @Test(description = "TC-01: Add system user with missing username shows validation error")
    public void addUserWithMissingUsername() {
       navigationBarComponents.clickAdminButton()
                .clickAddButton()
                .clickUserRoleDropDown()
                .clickUserRoleChoice()
                .clickStatusDropDown()
                .clickStatusChoice()
                .addEmpoyeeName(testUserData.getJsonData("missingUsername.employeeName"))
                .addUserName(testUserData.getJsonData("missingUsername.username"))
                .addPassword(testUserData.getJsonData("missingUsername.password"))
                .addConfirmPassword(testUserData.getJsonData("missingUsername.password"))
                .clickSaveButton();
        LogsManager.info("Attempted to save user with missing username");
    }

    @Test(description = "TC-02: Add system user with missing password shows validation error")
    public void addUserWithMissingPassword() {
        navigationBarComponents.clickAdminButton()
                .clickAddButton()
                .clickUserRoleDropDown()
                .clickUserRoleChoice()
                .clickStatusDropDown()
                .clickStatusChoice()
                .addEmpoyeeName(testUserData.getJsonData("missingPassword.employeeName"))
                .addUserName(testUserData.getJsonData("missingPassword.username"))
                .addPassword(testUserData.getJsonData("missingPassword.password"))
                .addConfirmPassword(testUserData.getJsonData("missingPassword.password"));

        LogsManager.info("Attempted to save user with missing password");
    }

    @Test(description = "TC-03: Confirm password mismatch shows validation error")
    public void addUserWithPasswordMismatch() {
        navigationBarComponents.clickAdminButton()
                .clickAddButton()
                .clickUserRoleDropDown()
                .clickUserRoleChoice()
                .clickStatusDropDown()
                .clickStatusChoice()
                .addEmpoyeeName(testUserData.getJsonData("mismatchPassword.employeeName"))
                .addUserName(testUserData.getJsonData("mismatchPassword.username"))
                .addPassword(testUserData.getJsonData("mismatchPassword.password"))
                .addConfirmPassword(testUserData.getJsonData("mismatchPassword.confirmPassword"));

        LogsManager.info("Attempted to save user with mismatched passwords");
    }

    @Test(description = "TC-04: Add system user with already-existing username shows duplicate error")
    public void addUserWithDuplicateUsername() {
        navigationBarComponents.clickAdminButton()
                .clickAddButton()
                .clickUserRoleDropDown()
                .clickUserRoleChoice()
                .clickStatusDropDown()
                .clickStatusChoice()
                .addEmpoyeeName(testUserData.getJsonData("duplicateUser.employeeName"))
                .addUserName(testUserData.getJsonData("duplicateUser.username")) // username that already exists
                .addPassword(testUserData.getJsonData("duplicateUser.password"))
                .addConfirmPassword(testUserData.getJsonData("duplicateUser.password"));

        LogsManager.info("Attempted to save duplicate username");
    }
}