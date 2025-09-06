package org.automation;

import static org.testng.Assert.assertEquals;

import java.util.Locale.Category;

import org.automation.pom.base.BaseTest;
import org.automation.pom.pages.CartDetailsPage;
import org.testng.annotations.Test;

public class CartDetailsTestCase extends BaseTest {

	@Test
	public void CartDetails() throws InterruptedException {
		addMensClothingToCart("men");
		CartDetailsPage cartDetail = new CartDetailsPage(driver);
		cartDetail.enterQuantity();
		cartDetail.updateCart();
		String actualSubTotal = cartDetail.SubTotal();
		assertEquals(actualSubTotal, "$200.00");
		cartDetail.clickProceedToCheckOutBtn();

	}
}
