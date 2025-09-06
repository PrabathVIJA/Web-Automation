package org.automation.pom.pages;

import org.automation.pom.base.BasePage;
import org.automation.pom.utils.ActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartDetailsPage extends BasePage {

	private ActionUtils action;
	public CartDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input.qty")
	WebElement quantityField;

	@FindBy(css = "button[name=\"update_cart\"]")
	WebElement updateCart;

	@FindBy(css = "td.product-subtotal span.woocommerce-Price-amount")
	WebElement subTotalElement;
	By overLayElement = By.cssSelector(".blockOverlay");

	@FindBy(css = "a[class=\"checkout-button button alt wc-forward\"]")
	WebElement proceedToCheckOutBtn;

	public void enterQuantity() {
		WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(quantityField));
		quantityField.clear();
		quantityField.sendKeys("2");

	}

	public void updateCart() {
		wait.until(ExpectedConditions.elementToBeClickable(updateCart)).click();
	}

	public String SubTotal() {
//		boolean subtotal = wait.until(ExpectedConditions.textToBePresentInElementLocated(
//			    By.cssSelector("td.product-subtotal span.woocommerce-Price-amount"), 
//			    "$200.00"
//			));
		waitForOverlaysToDisappear(overLayElement);
//		
//			// Now get the updated text

		return subTotalElement.getText();

	}
	
	public void clickProceedToCheckOutBtn()
	{
		action = new ActionUtils(driver);
		action.scrollEntirePageToTheBottom();
		wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckOutBtn)).click();
	}

}
