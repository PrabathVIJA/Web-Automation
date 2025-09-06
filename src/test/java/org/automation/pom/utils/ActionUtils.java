package org.automation.pom.utils;

import org.automation.pom.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtils {
	private Actions action;
	private WebDriver driver;

	public ActionUtils(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
	}

	// method for dragging
	public void dragging(WebElement element) {

		action.dragAndDropBy(element, 100, 0).perform();
	}
	
	//method to Hover
	public void Hover(WebElement element)
	{
		
		action.moveToElement(element).perform();
	}
	
	//js method for scrolling into view of an element
	public void scrollIntoView(WebElement element)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void scrollEntirePageToTheBottom()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
	    long currentPosition = 0;
	    long scrollStep = 300; // pixels per scroll, adjust if needed

	    while (currentPosition < lastHeight) {
	        js.executeScript("window.scrollBy(0, arguments[0]);", scrollStep);
	        currentPosition += scrollStep;

	        // small wait to simulate human scroll
	        try {
	            Thread.sleep(200); // 0.2 seconds
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // update the page height in case new content loads
	        long newHeight = (long) js.executeScript("return document.body.scrollHeight");
	        if (newHeight > lastHeight) {
	            lastHeight = newHeight;
	        }
	    }

	    // final scroll to make sure we reach exact bottom
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
}
