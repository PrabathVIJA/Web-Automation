package org.automation;

import static org.testng.Assert.assertEquals;

import org.automation.pom.base.BaseTest;
import org.automation.pom.pages.LoginRegisterPage;
import org.automation.pom.utils.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginRegisterTestCases extends BaseTest {

	LoginRegisterPage loginRegister;

	@Test
	public void validate_AccountPage_Landing() {
		goToAccountPage();
		loginRegister = new LoginRegisterPage(driver);

		Assert.assertEquals(loginRegister.accountHeaderText(), "Accounts");
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
}
