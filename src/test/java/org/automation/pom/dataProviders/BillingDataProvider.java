package org.automation.pom.dataProviders;

import java.io.IOException;
import java.util.List;

import org.automation.pom.objects.BillingData;
import org.automation.pom.utils.JacksonUtility;
import org.testng.annotations.DataProvider;

public class BillingDataProvider {

    @DataProvider(name = "billingData")
    public static Object[][] billingDataProvider() throws IOException {
        List<BillingData> list = JacksonUtility.deserializeJsonList("billingAddress.json", BillingData.class);

        Object[][] data = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);  // Put each BillingData object in one row
        }
        return data;
    }
}
