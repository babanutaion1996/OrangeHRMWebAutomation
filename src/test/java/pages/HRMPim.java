package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class HRMPim {
    public HRMPim(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class=\"oxd-table-card\"]/div/div[2]/div")
    public List<WebElement> employeePim;

    @FindBy(xpath = "//a[@href=\"/web/index.php/pim/viewPimModule\"]")
    public WebElement navigateToPim;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary\"]")
    public WebElement addBtnPim;

    @FindBy(xpath = "//input[@class=\"oxd-input oxd-input--active orangehrm-firstname\"]")
    public WebElement firstNamePim;

    @FindBy(xpath = "//input[@class=\"oxd-input oxd-input--active orangehrm-middlename\"]")
    public WebElement middleNamePim;

    @FindBy(xpath = "//label[contains(text(), \"Driver's License Number\")]/following::input[@class=\"oxd-input oxd-input--active\"][1]")
    public WebElement dlNumber;

    @FindBy(xpath = "//input[@class=\"oxd-input oxd-input--active orangehrm-lastname\"]")
    public WebElement lastNamePim;

    @FindBy(xpath = "//label[@class=\"oxd-label\"]/../following-sibling::div/input")
    public WebElement employeeIdPim;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]")
    public WebElement saveBtnPim;

    @FindBy(xpath = "//p[@class=\"oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text\"]")
    public WebElement messageSuccess;

    @FindBy(xpath = "//div/p[@class=\"oxd-text oxd-text--p orangehrm-form-hint\"]/following-sibling::button")
    public WebElement successfullySavedPim;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin\"]")
    public WebElement deleteBtn;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    public WebElement alreadyExistingId;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    public WebElement requiredMessage;

    @FindBy(xpath = "//label[contains(text(),'Employee Id')]/following::input[1]")
    public WebElement employeeIdEdit;

    @FindBy(xpath = "//label[contains(text(),'Employee Name')]/following::input[1]")
    public WebElement employeeLastNameEdit;

    @FindBy(xpath = "//p[@class=\"oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text\"]")
    public WebElement noRecordsFound;

    @FindBy(xpath = "//div[@class=\"oxd-form-actions\"]//button[.=\" Search \"]")
    public WebElement searchBtnEdit;

    public WebElement returnElementIdFromRow(String employeeIdNumber){
        for(WebElement employeeID: employeePim){
            if(employeeID.getText().trim().equalsIgnoreCase(employeeIdNumber)){
                return employeeID;
            }
        }
        return null;
    }

    public List<WebElement> returnRowIdElements(WebDriver driver, String employeeIdNumber){
        String xpathForRowIdElements=
                "//div[@class=\"oxd-table-card\"]/div/div[2]/div[.=\""+employeeIdNumber+"\"]/../../div";
        List<WebElement> rowIdElements
                = driver.findElements(By.xpath(xpathForRowIdElements));

        return rowIdElements;

    }

// edit button with specific employee id
    ////div[contains(@class, 'oxd-table-card')]//div[text()='805']/following::div[contains(@class, 'oxd-table-cell-actions')][1]
////div[contains(@class, 'oxd-table-card')]//div[text()='9632']/following::div/button[1]
    public List<WebElement> returnRowActions(WebDriver driver, String employeeIdNumber){
        String xpathForRowIdActions=
                "//div[@class=\"oxd-table-card\"]/div/div[2]/div[.='"+employeeIdNumber+"']/../..//div[@class=\"oxd-table-cell-actions\"]/button";

        List<WebElement> rowIdActions
                = driver.findElements(By.xpath(xpathForRowIdActions));

        return rowIdActions;

    }



}
