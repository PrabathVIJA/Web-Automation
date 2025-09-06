package org.automation;

import java.io.IOException;

import org.automation.pom.base.BaseTest;

import org.automation.pom.objects.BillingData;
import org.automation.pom.objects.userSelectionData;
import org.automation.pom.pages.CheckOutPage;
import org.automation.pom.utils.JacksonUtility;

import org.testng.annotations.Test;

public class CheckOutTestCase extends BaseTest {

	@Test
	public void checkingOutTestCase() throws InterruptedException, IOException {
		BillingData billingData = JacksonUtility.deserializeJson("billingAddress.json", BillingData.class);
		userSelectionData userSelection = JacksonUtility.deserializeJson("usersChoice.json", userSelectionData.class);
		addMensClothingToCart(userSelection.getColor());
		viewCart();
		CheckOutPage checkOut = new CheckOutPage(driver);
		checkOut.enterFirstName(billingData.getFirstName());
		checkOut.enterLasttName(billingData.getLastName());
		checkOut.enterAddress(billingData.getAdressLineOne());
		checkOut.enterTownOrCity(billingData.getTown());
		checkOut.enterPostalCode(billingData.getPostalCode());
		checkOut.enterEmailAdressField(billingData.getEmail());
		checkOut.clickPlaceOrderBtn();
		Thread.sleep(2000);
	}
}
