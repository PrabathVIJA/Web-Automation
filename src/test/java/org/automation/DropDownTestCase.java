package org.automation;

import static org.testng.Assert.assertEquals;

import org.automation.pom.base.BaseTest;
import org.automation.pom.pages.HomePage;
import org.automation.pom.pages.StorePage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class DropDownTestCase extends BaseTest {

	@Test(priority = 0)
	public void selectAllDropDownOptions() {
		HomePage home = new HomePage(driver);
		home.load();
		home.clickStorePageLink();
		StorePage store = new StorePage(driver);
		store.selectAllOptionsDropDown();

	}

	@Test(priority = 1)
	public void selectDropDownByText() {
		HomePage home = new HomePage(driver);
		home.load();
		home.clickStorePageLink();
		StorePage store = new StorePage(driver);
		store.selectOptionByText("Sort by price: low to high");
		assertEquals(store.getSelectedOptionText(), "Sort by price: low to high",
				"the actual string doesn't match the selected dropDown's string");

	}

	@Test(priority = 2)
	public void selectDropDownByValue() {
		HomePage home = new HomePage(driver);
		home.load();
		home.clickStorePageLink();
		StorePage store = new StorePage(driver);
		store.selectOptionByValue("price-desc");
		assertEquals(store.getSelectedOptionText(), "Sort by price: high to low",
				"the actual string doesn't match the selected dropDown's string");

	}

	@Test(priority = 3)
	public void selectDropDownByIndex() {
		HomePage home = new HomePage(driver);
		home.load();
		home.clickStorePageLink();
		StorePage store = new StorePage(driver);
		store.selectOptionByIndex(4);

		assertEquals(store.getSelectedOptionText(), "Sort by price: low to high",
				"the actual string doesn't match the selected dropDown's string");

	}

}
