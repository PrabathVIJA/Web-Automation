package org.automation.pom.pages;

import org.automation.pom.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//li[@id='menu-item-1227']//a[@class=\"menu-link\"]")
	WebElement storeMenuLink;

	@FindBy(xpath = "//li[@id=\"menu-item-1228\"]//a[text()=\"Men\"]")
	WebElement menMenuLink;

	@FindBy(xpath = "//div[@id=\"ast-desktop-header\"]//a[text()='Account']")
	WebElement AccountLink;

	@FindBy(xpath = "//div[@id=\"ast-desktop-header\"]//a[text()=\"About\"]")
	WebElement aboutLink;

	public void load() {
		loading();
	}

	public StorePage clickStorePageLink() {
		wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
		return new StorePage(driver);
	}

	public void clickAccountLink() {
		wait.until(ExpectedConditions.elementToBeClickable(AccountLink)).click();
	}

	public void clickAboutLink() {
		wait.until(ExpectedConditions.elementToBeClickable(aboutLink)).click();
	}

	public String clickAboutLinkOpenInNewTabAndSwitchToIt() {
		wait.until(ExpectedConditions.elementToBeClickable(aboutLink)).sendKeys(Keys.CONTROL, Keys.RETURN);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
 
		return driver.getTitle();
	}

}
