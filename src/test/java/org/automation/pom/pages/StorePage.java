package org.automation.pom.pages;

import java.util.List;


import org.automation.pom.base.BasePage;
import org.automation.pom.utils.ActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.TimeoutException;


public class StorePage extends BasePage {
	private ActionUtils actionUtil;

	public StorePage(WebDriver driver) {
		super(driver);

		PageFactory.initElements(driver, this);
		actionUtil = new ActionUtils(driver);

	}

	@FindBy(id = "woocommerce-product-search-field-0")
	WebElement searchProductInputField;

	@FindBy(xpath = "//select[@name=\"orderby\"]")
	WebElement orderByDropdown;

	@FindBy(id = "product_cat")
	WebElement categoryDropdown;

	@FindBy(xpath = "//span[@class=\"ui-slider-handle ui-corner-all ui-state-default\"][1]")
	WebElement sliderbutton;

	@FindBy(id = "woocommerce-product-search-field-0")
	WebElement searchField;

	@FindBy(css = "button[value='Search']")
	WebElement searchBtn;

	@FindBy(xpath = "//a[@aria-label=\"Add “Dark Grey Jeans” to your cart\"]")
	WebElement greyJeanAddToCart;

	@FindBy(xpath = "//div[@class=\"ast-woocommerce-container\"]//a[text()=\"Add to cart\"]")
	List<WebElement> itemsDisplayedOnPageOne;

	@FindBy(xpath = "//div[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//span[@class='count']")
	WebElement cartIcon;

	@FindBy(xpath = "//div[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//a[@class='button wc-forward'][normalize-space()='View cart']")
	WebElement viewCart;

	@FindBy(css = "#ast-desktop-header #ast-site-header-cart .button.checkout.wc-forward")
	WebElement checkOut;

	@FindBy(css = "div#ast-desktop-header .count")
	WebElement countOfCart;

	@FindBy(css = "a.next ")
	WebElement nextPageBtn;

	public void enterInProductInputField(String str) {
		wait.until(ExpectedConditions.elementToBeClickable(searchProductInputField)).sendKeys(str);
	}

	public void selectAllOptionsDropDown() {
		WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(orderByDropdown));

		Select select = new Select(dropDown);

		List<String> texts = select.getOptions().stream().map(WebElement::getText).toList();

		for (String text : texts) {
			dropDown = wait.until(ExpectedConditions.elementToBeClickable(orderByDropdown));
			select = new Select(dropDown);

			System.out.println("Selecting: " + text);
			select.selectByVisibleText(text);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// selecting dropdown value by means of text
	public void selectOptionByText(String text) {
		WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(orderByDropdown));

		Select select = new Select(dropDown);

		select.selectByVisibleText(text);

	}

	// selecting dropdown by using value
	public void selectOptionByValue(String value) {
		WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(orderByDropdown));

		Select select = new Select(dropDown);

		select.selectByValue(value);
	}

	// selecting dropdown by index value
	public void selectOptionByIndex(int index) {
		WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(orderByDropdown));

		Select select = new Select(dropDown);

		select.selectByIndex(index);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// go to category dropDown and just click on it
	public void testingCategoryDropDown() {
		actionUtil.scrollIntoView(categoryDropdown);
		categoryDropdown.click();
	}

	// helper method to fetch selected dropDown
	public String getSelectedOptionText() {
		WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(orderByDropdown));

		Select select = new Select(dropDown);

		return select.getFirstSelectedOption().getText();
	}

	// testing if we can move slider to right side
	public void sliderAutomation() {

		actionUtil.scrollIntoView(sliderbutton);
		actionUtil.dragging(sliderbutton);

	}

	public void selectMensClothingOption(String str) {

		WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(searchField));
		dropDown.sendKeys(str);
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

	}

	public void addingToCart() {

		actionUtil.scrollIntoView(greyJeanAddToCart);
		wait.until(ExpectedConditions.elementToBeClickable(greyJeanAddToCart)).click();
	}

	// dyanmic method for adding item to cart
	public void addItemToTheCartUsingDynamicSelector(String productName) {
		try {
			By addToCart = By.cssSelector(String.format("[aria-label='Add “%s” to your cart']", productName));
			System.out.println("Adding " + productName + " to" + " Cart");
			wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
		} catch (TimeoutException e) {
			System.out.println("Can't click on " + productName);// prints exception type, message, and exact line numbers
		}

	}

//hover over cart Icon and clikc view cart
	public void GoHoverCartIcon() {
		actionUtil.scrollIntoView(cartIcon);
		actionUtil.Hover(cartIcon);
		wait.until(ExpectedConditions.elementToBeClickable(viewCart)).click();
	}

	// hover over cart Icon and click check out
	public void clickCheckOut() {
		actionUtil.scrollIntoView(cartIcon);
		actionUtil.Hover(cartIcon);
		wait.until(ExpectedConditions.elementToBeClickable(checkOut)).click();
	}

	// selects all the visible items on page
	public void addAllItems() throws InterruptedException {
		List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElements(itemsDisplayedOnPageOne));

		for (WebElement item : items) {
			item.click();
			String productId = item.getAttribute("data-product_id");

//			By viewInCart = By.cssSelector("a[data-product_id='" + productId + "'] + a.added_to_cart");
			By viewInCart = By.cssSelector(String.format("a[data-product_id='%s'] + a.added_to_cart", productId));
			wait.until(ExpectedConditions.visibilityOfElementLocated(viewInCart));
		}

	}

	public int getCountOfCart() {
		String text = wait.until(ExpectedConditions.elementToBeClickable(countOfCart)).getText().trim();
		int count = Integer.parseInt(text);
		return count;

	}

	public void clickNextPage() {
		wait.until(ExpectedConditions.elementToBeClickable(nextPageBtn)).click();
	}
}
