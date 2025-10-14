package org.automation;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.automation.pom.base.BaseTest;
import org.automation.pom.dataProviders.BillingDataProvider;
import org.automation.pom.dataProviders.ProductDataProvider;
import org.automation.pom.objects.BillingData;
import org.automation.pom.objects.Product;
import org.automation.pom.objects.userSelectionData;
import org.automation.pom.pages.CheckOutPage;
import org.automation.pom.pages.OrderDetailsPage;
import org.automation.pom.pages.StorePage;
import org.automation.pom.utils.ConfigLoader;
import org.automation.pom.utils.JacksonUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutTestCase extends BaseTest {
	CheckOutPage checkOut;
	StorePage storePage;
	OrderDetailsPage orderPage;

	@Test(dataProvider = "billingData", dataProviderClass = BillingDataProvider.class)
	public void checkingOutTestCase(BillingData billingData) throws InterruptedException, IOException {

		userSelectionData userSelection = JacksonUtility.deserializeJson("usersChoice.json", userSelectionData.class);
		addMensClothingToCart(userSelection.getColor());
		viewCart();
		checkOut = new CheckOutPage(driver);
		checkOut.enterFirstName(billingData.getFirstName());
		checkOut.enterLasttName(billingData.getLastName());
		checkOut.enterAddress(billingData.getAdressLineOne());
		checkOut.enterTownOrCity(billingData.getTown());
		checkOut.enterPostalCode(billingData.getPostalCode());
		checkOut.enterEmailAdressField(billingData.getEmail());
		checkOut.clickPlaceOrderBtn();

	}

	@Test
	public void checkingOutWithRegistrationTestCase() throws IOException, InterruptedException {
		userSelectionData userSelection = JacksonUtility.deserializeJson("usersChoice.json", userSelectionData.class);
		List<BillingData> list = JacksonUtility.deserializeJsonList("billingAddress.json", BillingData.class);
		BillingData billingData = list.get(0);
		addMensClothingToCart(userSelection.getColor());
		viewCart();
		checkOut = new CheckOutPage(driver);
		checkOut.loginWithCredentials(ConfigLoader.getInstance().getUserName(),
				ConfigLoader.getInstance().getPassword());
		checkOut.enterFirstName(billingData.getFirstName());

		checkOut.enterLasttName(billingData.getLastName());
		checkOut.enterAddress(billingData.getAdressLineOne());
		checkOut.enterTownOrCity(billingData.getTown());
		checkOut.enterPostalCode(billingData.getPostalCode());
		checkOut.enterEmailAdressField(billingData.getEmail());
		checkOut.clickPlaceOrderBtn();
		checkOut.handleSaveAddressPopup(true);

	}

	// Adds all items that are being displayed on Page to the cart and checks out
	@Test
	public void addAllItemsOnPageOneToCartAndCheckOutTest() throws IOException, InterruptedException {
		int expectedCount = 13;
		addAllItemsOnPageToCart();
		storePage = new StorePage(driver);

		storePage.clickNextPage();
		storePage.addAllItems();

		storePage.clickCheckOut();

		int actualCount = storePage.getCountOfCart();
		assertEquals(actualCount, expectedCount);
		List<BillingData> list = JacksonUtility.deserializeJsonList("billingAddress.json", BillingData.class);
		BillingData billingData = list.get(0);
		checkOut = new CheckOutPage(driver);
		checkOut.enterFirstName(billingData.getFirstName());
		checkOut.enterLasttName(billingData.getLastName());
		checkOut.enterAddress(billingData.getAdressLineOne());
		checkOut.enterTownOrCity(billingData.getTown());
		checkOut.enterPostalCode(billingData.getPostalCode());
		
		checkOut.enterEmailAdressField(billingData.getEmail());
		checkOut.verifyIfDirectBankTransferBtnIsChecked();
		checkOut.clickPlaceOrderBtn();

	}
	
	@Test(dataProvider = "productName", dataProviderClass = ProductDataProvider.class)
	public void addAnyItemToCartWithDynamicSelector(Product product) throws IOException {
		goToStorePage();
		storePage = new StorePage(driver);
		orderPage = new OrderDetailsPage(driver);
		
		storePage.addItemToTheCartUsingDynamicSelector(product.getProductName());
		storePage.clickCheckOut();
		List<BillingData> list = JacksonUtility.deserializeJsonList("billingAddress.json", BillingData.class);
		BillingData billingData = list.get(0);
		checkOut = new CheckOutPage(driver);
		checkOut.enterFirstName(billingData.getFirstName());
		checkOut.enterLasttName(billingData.getLastName());
		checkOut.enterAddress(billingData.getAdressLineOne());
		checkOut.enterTownOrCity(billingData.getTown());
		checkOut.enterPostalCode(billingData.getPostalCode());
		
		checkOut.enterEmailAdressField(billingData.getEmail());
		checkOut.verifyIfDirectBankTransferBtnIsChecked();
		checkOut.clickPlaceOrderBtn();
		String expected = orderPage.getSuccessText();
		Assert.assertEquals("Thank you. Your order has been received.",expected);
	}
}
