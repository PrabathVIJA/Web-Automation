package org.automation.pom.base;

import java.time.Duration;
import java.util.List;

import org.automation.pom.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void loading() {
		driver.get(ConfigLoader.getInstance().getBaseUrl());
	}

	public void waitForOverlaysToDisappear(By overLay) {
		List<WebElement> overLays = driver.findElements(overLay);

		if (overLays.size() > 0) {
			wait.until(ExpectedConditions.invisibilityOfAllElements(overLays));

		} else {
			System.out.println("No OverLays Found");
		}
	}
	
		

}
