package org.automation.pom.pages;

import java.time.Duration;
import java.util.List;

import org.automation.pom.base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage extends BasePage {

	public CheckOutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "billing_first_name")
	WebElement firstNameField;
	//
	By firstNameFieldInput = By.id("billing_first_name");

	@FindBy(id = "billing_last_name")
	WebElement lastNameField;

	@FindBy(id = "billing_address_1")
	WebElement streetAddressField;

	@FindBy(id = "billing_city")
	WebElement town;

	@FindBy(id = "billing_postcode")
	WebElement postalCodeField;

	@FindBy(id = "billing_email")
	WebElement emailAddressField;

	@FindBy(id = "place_order")
	WebElement placeOrderBtn;

	@FindBy(xpath = "//a[text()='Click here to login']")
	WebElement clickToLoginBtn;

	@FindBy(id = "username")
	WebElement userNameField;

	@FindBy(id = "password")
	WebElement passwordField;

	@FindBy(css = "button[class='woocommerce-button button woocommerce-form-login__submit']")
	WebElement loginBtn;

	public void enterFirstName(String firstName) {
	WebElement firstNameInputField =	wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFieldInput));
	firstNameInputField.clear();
	firstNameInputField.sendKeys(firstName);
	}

	public void enterLasttName(String lastName) {
		wait.until(ExpectedConditions.elementToBeClickable(lastNameField)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(lastNameField)).sendKeys(lastName);
	}

	public void enterAddress(String adress) {
		wait.until(ExpectedConditions.elementToBeClickable(streetAddressField)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(streetAddressField)).sendKeys(adress);
	}

	public void enterTownOrCity(String townName) {
		wait.until(ExpectedConditions.elementToBeClickable(town)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(town)).sendKeys(townName);
	}

	public void enterPostalCode(String postalCode) {
		wait.until(ExpectedConditions.elementToBeClickable(postalCodeField)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(postalCodeField)).sendKeys(postalCode);
	}

	public void enterEmailAdressField(String email) {
		wait.until(ExpectedConditions.elementToBeClickable(emailAddressField)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(emailAddressField)).sendKeys(email);
	}

	public void clickPlaceOrderBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
	}

	// method to login
	public void loginWithCredentials(String user, String password) {
		wait.until(ExpectedConditions.elementToBeClickable(clickToLoginBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(userNameField)).sendKeys(user);
		wait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

	}
	
	public void handleSaveAddressPopup(boolean saveAddress) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        List<WebElement> popupList = driver.findElements(By.id("save-address-popup")); // returns empty list if not found

	        if (!popupList.isEmpty()) { // popup exists
	            if (saveAddress) {
	                driver.findElement(By.id("save-address-yes")).click();
	            } else {
	                driver.findElement(By.id("save-address-no")).click();
	            }
	        }
	    } catch (Exception e) {
	        // popup didn’t appear, continue test
	        System.out.println("Save Address popup not displayed, continuing test...");
	    }
	}


}
