package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class HRMLogin {
    public HRMLogin(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (name = "username")
    public WebElement usernameBox;

    @FindBy (name = "password")
    public WebElement passwordBox;

    @FindBy (tagName = "button")
    public WebElement loginBtn;

    @FindBy (xpath = "//p[.=\"Forgot your password? \"]")
    public WebElement forgotPasswordBtn;

    @FindBy (xpath = "//div[@class=\"oxd-alert-content oxd-alert-content--error\"]")
    public WebElement invalidCredentialsWarning;

    @FindBy (xpath = "//input[@name=\"username\"]/../following-sibling::span")
    public WebElement requiredWarningForUsername;

    @FindBy(xpath = "//input[@name=\"password\"]/../following-sibling::span")
    public WebElement requiredWarningForPassword;

    @FindBy(xpath = "//h6[@class=\"oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module\"]")
    public WebElement dashboardHeader;



}
