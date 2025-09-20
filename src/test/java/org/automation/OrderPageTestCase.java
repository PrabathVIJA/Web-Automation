package org.automation;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.automation.pom.base.BaseTest;
import org.automation.pom.objects.BillingData;
import org.automation.pom.objects.userSelectionData;
import org.automation.pom.pages.OrderDetailsPage;
import org.automation.pom.utils.JacksonUtility;
import org.testng.annotations.Test;

public class OrderPageTestCase extends BaseTest{
	
	@Test
	public void validateSuccessMessage() throws IOException
	{
		List<BillingData> billingDataList = JacksonUtility.deserializeJsonList("billingAddress.json", BillingData.class);
		BillingData billingData = billingDataList.get(0); 
		userSelectionData userSelection = JacksonUtility.deserializeJson("usersChoice.json", userSelectionData.class);
		addMensClothingToCart(userSelection.getColor());
		viewCart();
		checkOutDetails(billingData.getFirstName(),billingData.getLastName(),billingData.getAdressLineOne(),billingData.getTown(),billingData.getPostalCode(),billingData.getEmail());
		OrderDetailsPage orderDetails = new OrderDetailsPage(driver);
		assertEquals(orderDetails.getSuccessText(), "Thank you. Your order has been received.");
		
		
	}

}
