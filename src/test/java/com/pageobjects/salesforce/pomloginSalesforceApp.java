package com.pageobjects.salesforce;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.base.BrowserUtilities;
import com.utilities.base.TestBase;

public class pomloginSalesforceApp extends TestBase{
	
	public Logger log = Logger.getLogger(getClass().getSimpleName());
	
	public pomloginSalesforceApp(WebDriver driver) {
		TestBase.browserdriver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id= "logo")
	WebElement po_logo;
	@FindBy(id= "username")
	public
	WebElement po_username;
	@FindBy(id= "password")
	public
	WebElement po_password;
	@FindBy(id= "rememberUn")
	public
	WebElement po_Rememberme;
	@FindBy(id= "Login")
	WebElement button_login;
	@FindBy(xpath= "//li[@id='home_Tab']//a[contains(text(),'Home')]")
	public
	WebElement tab_home;
	@FindBy(id= "userNavLabel")
	public
	WebElement po_usernavLabel;
		
	public boolean loginToSalesforceApp() throws Exception{
		boolean bloginFlag=false;
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_logo, 30);
		browserUtil.ufsendKeys(po_username, System.getProperty("username"), "Username");
		browserUtil.ufsendKeys(po_password, System.getProperty("password"), "Password");
		browserUtil.isSelected(po_Rememberme);
		browserUtil.ufClick(button_login);
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_usernavLabel, 30);
		if (browserUtil.isDisplayed(tab_home)) {
			browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, tab_home, 30);
			log.info("LogIn into Salesforce App success" +browserUtil.isDisplayed(tab_home));
			bloginFlag=true;
		} 
		return bloginFlag;
	}
	
	public boolean checkRememberme() throws Exception {
		boolean bRememberMeflag=false;
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_password, 20);
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_username, 20);
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_Rememberme, 20);
		if (browserUtil.isSelected(po_Rememberme)) {
			log.info("Remember Me is selected for this user name" +browserUtil.isDisplayed(po_Rememberme));
			bRememberMeflag=true;
		}
		return bRememberMeflag;
	}
	
	public boolean forgotYourPassword() throws Exception {
		return false;
		
	}
	
	
	@FindBy(id= "error")
	public
	WebElement po_loginerror;
	public void loginErrorMessage() throws Exception {
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_logo, 20);
		browserUtil.ufsendKeys(po_username, System.getProperty("username"),"Username");
		browserUtil.ufsendKeys(po_password, System.getProperty("password"), "Password");
		browserUtil.isSelected(po_Rememberme);
		browserUtil.ufelementClear(po_password);
		log.info("password input is empty on login page after clear");
		browserUtil.ufClick(button_login);
		
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_loginerror, 20);
		String error = browserUtil.ufgetText(po_loginerror);
		
		if (error.trim().equalsIgnoreCase("Please enter your password.")) {
			log.info("Gives valid Login Error Message " + browserUtil.ufgetText(po_loginerror));
		} else {
			log.info("Gives invalid Login Error Message" + browserUtil.ufgetText(po_loginerror));
		}}
	
	
	public void invalidLoginToSalesforceApp() throws Exception{
		
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_logo, 30);
		browserUtil.ufsendKeys(po_username, System.getProperty("invalidusername"),"Username");
		browserUtil.ufsendKeys(po_password, System.getProperty("invalidpassword"), "Password");
		log.info("Input is invalid username and password on login page");
		browserUtil.ufClick(button_login);
		
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_loginerror, 30);
		String error = browserUtil.ufgetText(po_loginerror);
		
		if (error.trim().equalsIgnoreCase("Please check your username and password. If you still can't log in, contact your Salesforce administrator.")) 
		{
			browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_loginerror, 30);	
			log.info("Gives Login Error Message for invalid credentials" + browserUtil.ufgetText(po_loginerror));
			} else {
			log.info("Gives not correct Login Error Message " + browserUtil.ufgetText(po_loginerror));
				}
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, tab_home, 20);
		}
	
	@FindBy(xpath="//a[@id='edit']")
	WebElement link_Editlist;
	@FindBy(id="userNavButton")
	WebElement button_UserNav;
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement button_logout;
	@FindBy(id="idcard-identity")
	WebElement idcard_username;
	@FindBy(id="clear_link")
	WebElement clearlink_username;
	public boolean verifyLoggedIntoSalesforceAppOrElseLogout() throws Exception {
		/*browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, link_Editlist, 30);
		browserUtil.ufClick(link_Editlist);*/
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, button_UserNav, 30);
		browserUtil.MouseOverClick(button_UserNav);
		boolean blogoutFlag=false;
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, button_logout, 30);
		if (browserUtil.isDisplayed(button_logout)) {
			browserUtil.ufClick(button_logout);
			log.info("Logout of Salesforce App success");
			blogoutFlag=true;
		} 
		browserUtil.waitForPageElementToVisible(browserdriver, po_password);
		browserUtil.waitForPageElementToVisible(browserdriver, idcard_username);
		if (browserUtil.isDisplayed(po_password)) {
			log.info("password field is ready for input: " +browserUtil.isDisplayed(po_password));
		}
		if (browserUtil.isEnabled(idcard_username)) {
			log.info("username field is auto-populated with : " + System.getProperty("username"));		
		}
		browserUtil.ufClick(clearlink_username);
		log.info("username field is ready for input: " +browserUtil.isEnabled(po_username));
		return blogoutFlag;
	}
	
	public boolean checkLoginSucessOrNot(String sUsername, String sPassword) throws Exception{
		boolean bloginFlag=false;
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, po_logo, 40);
		browserUtil.ufelementClear(po_username);
		browserUtil.ufsendKeys(po_username, sUsername, "Username");
		browserUtil.ufelementClear(po_password);
		browserUtil.ufsendKeys(po_password, sPassword, "Password");
		browserUtil.isSelected(po_Rememberme);
		browserUtil.ufClick(button_login);
		browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, button_UserNav, 30);
		if (browserUtil.isDisplayed(tab_home)) {
			log.info("LogIn into Salesforce App success" +browserUtil.isDisplayed(tab_home));
			bloginFlag=true;
			browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, button_UserNav, 30);
			browserUtil.MouseOverClick(button_UserNav);
			browserUtil.waitForPageElementToVisibleWithPollingTime(browserdriver, button_logout, 30);
			browserUtil.ufClick(button_logout);
			browserUtil.waitForPageElementToVisible(browserdriver, clearlink_username);
			browserUtil.ufClick(clearlink_username);
		}else {
			log.info("LogIn into Salesforce App is not success" + browserUtil.ufgetText(po_loginerror));
		}
		return bloginFlag;
	}

}
