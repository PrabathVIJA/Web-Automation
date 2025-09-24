package org.automation;

import java.io.IOException;
import java.util.List;

import org.automation.pom.base.BaseTest;
import org.automation.pom.dataProviders.BillingDataProvider;
import org.automation.pom.objects.BillingData;
import org.automation.pom.objects.userSelectionData;
import org.automation.pom.pages.CheckOutPage;
import org.automation.pom.utils.ConfigLoader;
import org.automation.pom.utils.JacksonUtility;

import org.testng.annotations.Test;

public class CheckOutTestCase extends BaseTest {
	CheckOutPage checkOut;

	@Test(dataProvider = "billingData", dataProviderClass = BillingDataProvider.class,groups = "group2")
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
		Thread.sleep(2000);
	}

	@Test
	public void checkingOutWithRegistrationTestCase() throws IOException, InterruptedException {
		userSelectionData userSelection = JacksonUtility.deserializeJson("usersChoice.json", userSelectionData.class);
		List<BillingData> list = JacksonUtility.deserializeJsonList("billingAddress.json", BillingData.class);
		BillingData billingData = list.get(0);
		addMensClothingToCart(userSelection.getColor());
		viewCart();
		checkOut = new CheckOutPage(driver);
		checkOut.loginWithCredentials(ConfigLoader.getInstance().getUserName(), ConfigLoader.getInstance().getPassword());
		checkOut.enterFirstName(billingData.getFirstName());

		checkOut.enterLasttName(billingData.getLastName());
		checkOut.enterAddress(billingData.getAdressLineOne());
		checkOut.enterTownOrCity(billingData.getTown());
		checkOut.enterPostalCode(billingData.getPostalCode());
		checkOut.enterEmailAdressField(billingData.getEmail());
		checkOut.clickPlaceOrderBtn();
		checkOut.handleSaveAddressPopup(true);
		Thread.sleep(2000);
	}
}
