package org.automation.pom.pages;

import org.automation.pom.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOutPage extends BasePage {

	public CheckOutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "billing_first_name")
	WebElement firstNameField;

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

	public void enterFirstName(String firstName) {
		wait.until(ExpectedConditions.elementToBeClickable(firstNameField)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(firstNameField)).sendKeys(firstName);
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

}
