package org.automation.pom.pages;

import org.automation.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderDetailsPage extends BasePage {

	public OrderDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private final By orderSuccessMessage = By.xpath("//p[text()=\"Thank you. Your order has been received.\"]");

	public String getSuccessText() {
		String successText = wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage)).getText();
		return successText;
	}
}
