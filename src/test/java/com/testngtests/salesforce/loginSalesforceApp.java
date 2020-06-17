package com.testngtests.salesforce;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjects.salesforce.pomloginSalesforceApp;
import com.utilities.base.BrowserUtilities;
import com.utilities.base.TestBase;

public class loginSalesforceApp extends TestBase{
	
	pomloginSalesforceApp pologin;
	public Logger log = Logger.getLogger(getClass().getSimpleName());
	
	
@BeforeTest
	public void settingupURL() throws Exception {
	sclassNameForScreenShot= getClass().getSimpleName();
	TestBase.browserdriver.get(browserUtil.getAppURL(System.getProperty("salesforceurl")));
	log.info("launched saleforceAPP URL successfuly");
	pologin = new pomloginSalesforceApp(browserdriver);
//	pologin.verifyLoggedIntoSalesforceAppOrElseLogout();
}

@AfterMethod
	public void settingReqURL() throws Exception {
//	TestBase.browserdriver.get(browserUtil.getAppURL(System.getProperty("salesforceurl")));
	}

@Test(priority=2)
public void to_Login_WithValid_Credentials() throws Exception{
		
		pologin.loginToSalesforceApp();
		Assert.assertEquals(browserUtil.ufgetText(pologin.po_usernavLabel), System.getProperty("userNavLabel"));
}

@Test(priority=1)
public void to_login_withInvalid_Credentials() throws Exception{
	pologin.invalidLoginToSalesforceApp();
	Assert.assertNotSame(browserUtil.ufgetText(pologin.po_loginerror), browserUtil.isDisplayed(pologin.tab_home), "Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
}

@Test(priority=3)
public void to_Check_Login_ErrorMessage() throws Exception{
	pologin.loginErrorMessage();
	Assert.assertEquals(browserUtil.ufgetText(pologin.po_loginerror), "Please enter your password.");
}


@Test(dependsOnMethods="to_Login_WithValid_Credentials")
public void to_Check_Logout_SalesforceApp() throws Exception{
	pologin.verifyLoggedIntoSalesforceAppOrElseLogout();
	Assert.assertEquals(browserUtil.isEnabled(pologin.po_username), true);
	Assert.assertEquals(browserUtil.isDisplayed(pologin.po_password), true);
	log.info("username and password fields are ready for input after logout: " +browserUtil.isDisplayed(pologin.po_password));
}

@Test(dependsOnMethods="to_Check_Logout_SalesforceApp")
public void to_Check_RememberMe() throws Exception{
	pologin.checkRememberme();
	Assert.assertEquals(browserUtil.isDisplayed(pologin.po_Rememberme), true);
}

@Test(priority=4, dataProvider="sUsernameandsPassword")
public void to_check_LoginSuccessOrNot_WithDataProvider(String sUsername, String sPassword) throws Exception{
	boolean bloginFlag = pologin.checkLoginSucessOrNot(sUsername, sPassword);
	//Assert.assertEquals(browserUtil.ufgetText(pologin.po_usernavLabel), System.getProperty("userNavLabel"));
	Assert.assertEquals(bloginFlag, true);
	}


@DataProvider(name="sUsernameandsPassword")
public Object[][] getdatasUsernameAndsPassword()	{
	Object data[][]=new Object[3][3];
		return new Object[][] {{"arathi.ram@salesforce.com", "test@123"},{"admin@admin.com","admin"},{"a.gmail.com","a"}};
	//return new Object[][] {{"arathi.ram@salesforce.com", "test@123"}};
	
}

}
