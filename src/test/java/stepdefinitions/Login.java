package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HRMLogin;
import utils.ConfigReader;
import utils.Driver;
import utils.LoggerUtils;

import java.util.List;
import java.util.Map;
import static hooks.Hooks.driver;

public class Login {


    private static HRMLogin  loginPage = new HRMLogin();

    @Given("I am on the OrangeHRM login page")
    public void i_am_on_the_orange_hrm_login_page() {
        driver.get(ConfigReader.readProperty("stg_url"));
    }

    @When("User enters valid credentials")
    public void user_enters_valid_credentials(DataTable dataTable) {
        List<Map<String,String>> credentialsList = dataTable.asMaps(String.class,String.class);
        for (Map<String,String> creds: credentialsList){
            String username = creds.get("username");
            String password = creds.get("password");
            loginPage.usernameBox.sendKeys(username);
            loginPage.passwordBox.sendKeys(password);
            LoggerUtils.info("Valid username and password is sent.");
            loginPage.loginBtn.click();
        }
    }

    @Then("User should be redirected to dasboard page")
    public void user_should_be_redirected_to_dasboard_page() {
      LoggerUtils.info("Validating that url contains 'dashboard'");
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        LoggerUtils.info("Validating that page contains Dashboard header");
        Assert.assertTrue(loginPage.dashboardHeader.isDisplayed());
        LoggerUtils.info("Logged in successfully.");
    }

    @When("User enter invalid credentials {string} {string}")
    public void user_enter_invalid_credentials(String username, String password) {
        loginPage.usernameBox.sendKeys(username);
        loginPage.passwordBox.sendKeys(password);
        LoggerUtils.info("Invalid -> username and password <- combination is sent.");
        loginPage.loginBtn.click();
    }

    @Then("User should see the {string} error message")
    public void user_should_see_the_error_message(String expectedErrorMessage) {
        LoggerUtils.info("Validating the error message element is dispalyed");
        Assert.assertTrue(loginPage.invalidCredentialsWarning.isDisplayed());
        LoggerUtils.info("Validating displayed error message matches the expected.");
        // "Invalid Message " and your expected message "Invalid Message"
        String actualDisplayedErrorMessage =
                loginPage.invalidCredentialsWarning.getText().trim();
        Assert.assertEquals(expectedErrorMessage,actualDisplayedErrorMessage);
        LoggerUtils.
                info(expectedErrorMessage + " is displayed successfully");
    }

    @When("User misses a credentials field {string} {string}")
    public void user_misses_a_credentials_field(String username, String password) {
        if(username.isEmpty()){
            LoggerUtils.info("Validating when user doesn't provide username");
            loginPage.passwordBox.sendKeys(password);
        }else if(password.isEmpty()){
            LoggerUtils.info("Validating when user doesn't provide password");
            loginPage.usernameBox.sendKeys(username);
        } else if (username.isEmpty() && password.isEmpty()) {
            LoggerUtils.info("Validating when user doesn't provide any credentials");
            // no need to send any keys
        }
        loginPage.loginBtn.click();
    }

    @Then("User should see a {string} message for the {string} field")
    public void user_should_see_a_message_for_the_field(String requiredMsg, String missingField) {
     if(missingField.equals("username")){
         Assert.assertTrue(loginPage.requiredWarningForUsername.isDisplayed());
         String actualMsg = loginPage.requiredWarningForUsername.getText().trim();
         Assert.assertEquals(requiredMsg,actualMsg);
         LoggerUtils.info("Required message successfully displayed for missing username field. ");
     }
     else if(missingField.equals("password")){
         Assert.assertTrue(loginPage.requiredWarningForPassword.isDisplayed());
         LoggerUtils.info("Required message successfully displayed for missing password field");
         String actualMsg = loginPage.requiredWarningForPassword.getText().trim();
         Assert.assertEquals(requiredMsg,actualMsg);
     } else if (missingField.equals("both")) {
       // Required message is displayed for username
         String actualMsgUsername = loginPage.requiredWarningForUsername.getText().trim();
         Assert.assertEquals(requiredMsg,actualMsgUsername);
         // Required message is displayed for password
         String actualMsgPassword = loginPage.requiredWarningForPassword.getText().trim();
         Assert.assertEquals(requiredMsg,actualMsgPassword);

         Assert.assertTrue(loginPage.requiredWarningForPassword.isDisplayed());
         Assert.assertTrue(loginPage.requiredWarningForUsername.isDisplayed());
         LoggerUtils.info("Required message for both missing field is successfully displayed.");
     }

    }



}
