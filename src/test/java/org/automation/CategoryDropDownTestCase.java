package org.automation;

import org.automation.pom.base.BaseTest;
import org.automation.pom.pages.HomePage;
import org.automation.pom.pages.StorePage;
import org.testng.annotations.Test;

public class CategoryDropDownTestCase extends BaseTest {

	@Test
	public void selectCategoryDropDown()  {
		HomePage home = new HomePage(driver);
		home.load();
		home.clickStorePageLink();
		StorePage store = new StorePage(driver);
		store.testingCategoryDropDown();
		
	}

	@Test
	public void sliderTestCase() throws InterruptedException {
		HomePage home = new HomePage(driver);
		home.load();
		
		StorePage store =home.clickStorePageLink();
		store.sliderAutomation();
		Thread.sleep(5000);
	}

	@Test
	public void selectMensClothingandAddingToCartTestCase()  {
		HomePage home = new HomePage(driver);
		home.load();
		home.clickStorePageLink();
		StorePage store = new StorePage(driver);
		store.selectMensClothingOption("men");
		store.addingToCart();
		store.GoHoverCartIcon();
		
	}
}
