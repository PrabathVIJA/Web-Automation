package org.automation.pom.pages;

import org.automation.pom.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginRegisterPage extends BasePage {

	public LoginRegisterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "username")
	private WebElement loginUserNameOrEmailField;
	@FindBy(id = "password")
	private WebElement loginPasswordField;
	@FindBy(css = ".woocommerce-button.button.woocommerce-form-login__submit")
	private WebElement loginBtn;
	@FindBy(xpath = "//ul[@class=\"woocommerce-error\"]//li")
	private WebElement userNameReqrdErrorText;
	@FindBy(xpath = "//h1[text()='Account']")
	private WebElement AccountHeaderText;

	@FindBy(xpath = "//ul[@class=\"woocommerce-error\"]")
	private WebElement passwordfieldEmptyText;
	@FindBy(css = "p[class=\"woocommerce-LostPassword lost_password\"]>a")
	private WebElement lostYourPWDBtn;
	@FindBy(xpath = "//form[@class=\"woocommerce-ResetPassword lost_reset_password\"]/p[contains(text(),\"Lost your password?\")]")
	private WebElement lostYourPasswordTextInPageWhereUserChangesHisPassword;

	@FindBy(id = "reg_username")
	private WebElement registerNameField;
	@FindBy(id = "reg_email")
	private WebElement registerEmaildField;
	@FindBy(id = "reg_password")
	private WebElement passwordField;
	@FindBy(xpath = "//button[text()=\"Register\"]")
	private WebElement registerBtn;
	@FindBy(xpath = "//div[@class=\"woocommerce-MyAccount-content\"]//p[normalize-space(text())=\"Hello\"]")
	private WebElement accountGreeting;

	@FindBy(xpath = "//a[text()='Log out']")
	private WebElement logOutBtn;

	@FindBy(id = "rememberme")
	private WebElement rememberMeBtn;

	public void enterUsernameOrEmail(String user) {
		WebElement usernameEmailFld = wait.until(ExpectedConditions.elementToBeClickable(loginUserNameOrEmailField));
		usernameEmailFld.clear();
		usernameEmailFld.sendKeys(user);
	}

	public void enterPassword(String pwd) {
		WebElement passwordFld = wait.until(ExpectedConditions.elementToBeClickable(loginPasswordField));
		passwordFld.clear();
		passwordFld.sendKeys(pwd);
	}

	public void loginClick() {
		WebElement login = wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		login.click();
	}

	public String userNameRequiredText() {
		WebElement errorMessage = wait.until(ExpectedConditions.elementToBeClickable(userNameReqrdErrorText));
		return userNameReqrdErrorText.getText();
	}

	public String accountHeaderText() {
		WebElement AccountHeader = wait.until(ExpectedConditions.elementToBeClickable(AccountHeaderText));
		return AccountHeader.getText();
	}

	public String emptyPasswordFieldText() {
		wait.until(ExpectedConditions.elementToBeClickable(passwordfieldEmptyText));
		return passwordfieldEmptyText.getText();
	}

	public void clickLostMyPassword() {
		wait.until(ExpectedConditions.elementToBeClickable(lostYourPWDBtn)).click();
	}

	public String getInputEnteredInUserNameOrEmailAddressField() {
		WebElement usernameEmailFld = wait.until(ExpectedConditions.elementToBeClickable(loginUserNameOrEmailField));
		return usernameEmailFld.getAttribute("value");
	}

	public String getInputEnteredInPasswordField() {
		WebElement passWordField = wait.until(ExpectedConditions.elementToBeClickable(loginPasswordField));
		return passWordField.getAttribute("value");
	}

	public String getLostYourPWDTextOnPWDChangePage() {
		WebElement lostPwrd = wait
				.until(ExpectedConditions.elementToBeClickable(lostYourPasswordTextInPageWhereUserChangesHisPassword));
		return lostPwrd.getText();
	}

	public String greetingMessage() {
		wait.until(ExpectedConditions.elementToBeClickable(accountGreeting));
		return accountGreeting.getText();
	}

	public void clickLogOut() {
		wait.until(ExpectedConditions.elementToBeClickable(logOutBtn)).click();
	}

	public String loginBtn_BackGround_Color(String cssValue) {
		String value = wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).getCssValue(cssValue);
		return value;
	}

	public boolean clickRememberBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(rememberMeBtn));
		if (!rememberMeBtn.isSelected()) {
			rememberMeBtn.click();
		}

		return rememberMeBtn.isSelected();
	}

	public void loginWithRememberMe(String userName, String pwd) {
		enterUsernameOrEmail(userName);
		enterPassword(pwd);
		clickRememberBtn();

	}

	//
}
