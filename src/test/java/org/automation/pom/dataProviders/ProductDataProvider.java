package org.automation.pom.dataProviders;

import java.io.IOException;
import java.util.List;

import org.automation.pom.objects.BillingData;
import org.automation.pom.objects.Product;
import org.automation.pom.utils.JacksonUtility;
import org.testng.annotations.DataProvider;

public class ProductDataProvider {

	@DataProvider(name = "productName")
	public static Object[][] productDataProvider() throws IOException {
		List<Product> list = JacksonUtility.deserializeJsonList("products.json", Product.class);

		Object[][] data = new Object[list.size()][1];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i); // Put each product Data object in one row
		}
		return data;

	}
}
