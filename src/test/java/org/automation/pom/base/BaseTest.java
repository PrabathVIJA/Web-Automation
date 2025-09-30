package org.automation.pom.base;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.automation.pom.factory.DriverManager;
import org.automation.pom.pages.CartDetailsPage;
import org.automation.pom.pages.CheckOutPage;
import org.automation.pom.pages.HomePage;
import org.automation.pom.pages.StorePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void startDriver() {

		driver = new DriverManager().initializeDriver();
	}

	@AfterMethod
	public void quitDriver(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				// ✅ create folder name
				String folderName = "test-output" + File.separator + "screenshots";
				new File(folderName).mkdirs(); // create folder if not exists

				// ✅ build file name with method name + timestamp
				String methodName = result.getMethod().getMethodName();
				String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
				String fileName = methodName + "_" + timeStamp + ".png";

				// ✅ capture screenshot
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File destFile = new File(folderName + File.separator + fileName);

				// ✅ save screenshot
				FileUtils.copyFile(srcFile, destFile);

				System.out.println("✅ Screenshot saved at: " + destFile.getAbsolutePath());
			} catch (Exception e) {
				System.out.println("❌ Could not save screenshot: " + e.getMessage());
			}
		}

		driver.quit();
	}

	protected void addMensClothingToCart(String gender) {
		HomePage home = new HomePage(driver);
		home.load();
		home.clickStorePageLink();
		StorePage store = new StorePage(driver);
		store.selectMensClothingOption(gender);
		store.addingToCart();
		store.GoHoverCartIcon();
	}

	protected void viewCart() {
		CartDetailsPage cartDetail = new CartDetailsPage(driver);
		cartDetail.enterQuantity();
		cartDetail.updateCart();
		String actualSubTotal = cartDetail.SubTotal();

		cartDetail.clickProceedToCheckOutBtn();
	}

	protected void checkOutDetails(String firstName, String lastName, String address, String town, String postalCode,
			String emailAddress) {
		CheckOutPage checkOut = new CheckOutPage(driver);
		checkOut.enterFirstName(firstName);
		checkOut.enterLasttName(lastName);
		checkOut.enterAddress(address);
		checkOut.enterTownOrCity(town);
		checkOut.enterPostalCode(postalCode);
		checkOut.enterEmailAdressField(emailAddress);
		checkOut.clickPlaceOrderBtn();

	}

	
	protected void goToAccountPage() {
		HomePage home = new HomePage(driver);
		home.load();
		home.clickAccountLink();
	}
	
	protected void goToAboutPage() {
		HomePage home = new HomePage(driver);
		home.load();
		
	}
	


}
