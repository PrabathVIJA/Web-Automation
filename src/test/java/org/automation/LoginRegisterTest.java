package org.automation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.automation.pom.base.BaseTest;
import org.automation.pom.factory.DriverManager;
import org.automation.pom.pages.HomePage;
import org.automation.pom.pages.LoginRegisterPage;
import org.automation.pom.utils.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginRegisterTest extends BaseTest {

	LoginRegisterPage loginRegister;

	@Test
	public void validate_AccountPage_Landing() {
		goToAccountPage();
		loginRegister = new LoginRegisterPage(driver);

		Assert.assertEquals(loginRegister.accountHeaderText(), "Account");

	}

	@Test
	public void validate_Without_userName() {
		goToAccountPage();
		loginRegister = new LoginRegisterPage(driver);
		loginRegister.loginClick();
		String actual = loginRegister.userNameRequiredText();

		Assert.assertEquals(actual, "Error: Username is required.");

	}

	@Test
	public void validate_With_EmptyPasswordField() {
		goToAccountPage();
		loginRegister = new LoginRegisterPage(driver);

		loginRegister.enterUsernameOrEmail("pra");
		loginRegister.loginClick();
		String actual = loginRegister.emptyPasswordFieldText();

		Assert.assertEquals(actual, "Error: The password field is empty.");

	}

	@Test
	public void validate_WronginputEntered_In_UserNameField() {
		goToAccountPage();
		loginRegister = new LoginRegisterPage(driver);
		loginRegister.enterUsernameOrEmail("pra");
		String enteredValue = loginRegister.getInputEnteredInUserNameOrEmailAddressField();
		Assert.assertEquals(enteredValue, "pra");
	}

	@Test
	public void validate_WronginputEntered_In_PWDField() {
		goToAccountPage();
		loginRegister = new LoginRegisterPage(driver);
		loginRegister.enterPassword("prabath@");
		String enteredValue = loginRegister.getInputEnteredInPasswordField();
		Assert.assertEquals(enteredValue, "prabath@");
	}

	@Test
	public void validate_LostYourPasswordBtn() {
		goToAccountPage();
		loginRegister = new LoginRegisterPage(driver);
		loginRegister.clickLostMyPassword();
		String val = loginRegister.getLostYourPWDTextOnPWDChangePage();
		Assert.assertEquals(val,
				"Lost your password? Please enter your username or email address. You will receive a link to create a new password via email.");

	}

	@Test
	public void loginWithValidCredentials() {
	goToAccountPage();
		loginRegister = new LoginRegisterPage(driver);
		loginRegister.enterUsernameOrEmail(ConfigLoader.getInstance().getUserName());
		loginRegister.enterPassword(ConfigLoader.getInstance().getPassword());
		loginRegister.loginClick();

		Assert.assertEquals(loginRegister.greetingMessage(), "Hello kiran (not kiran? Log out)");
		loginRegister.clickLogOut();
	}

	@Test
	public void openAboutPageInNewTabAndSwitchToIt() {
		goToAboutPage();
		HomePage home = new HomePage(driver);

		Assert.assertEquals(home.clickAboutLinkOpenInNewTabAndSwitchToIt(), "About – AskOmDch");

	}

	// check the background color of login button
	@Test
	public void loginBtn_BackGroundColor_validation() {
		goToAccountPage();
		loginRegister = new LoginRegisterPage(driver);

		Assert.assertEquals(loginRegister.loginBtn_BackGround_Color("background-color"), "rgba(49, 151, 214, 1)");

	}
	
	@Test
	public void validateLoginWithCheckingRememberMe()
	{
		goToAccountPage();
		loginRegister = new LoginRegisterPage(driver);
		loginRegister.loginWithRememberMe(ConfigLoader.getInstance().getUserName(), ConfigLoader.getInstance().getPassword());
		boolean isChecked = loginRegister.clickRememberBtn();
		assertTrue(isChecked);
		loginRegister.loginClick();
		loginRegister.clickLogOut();
	}

}
