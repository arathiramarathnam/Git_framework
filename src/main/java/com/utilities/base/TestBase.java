package com.utilities.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.testnglistenersandextentreports.ExtentReportsUtility;
import com.testnglistenersandextentreports.ListenerTest;

import io.appium.java_client.android.AndroidDriver;

@Listeners(com.testnglistenersandextentreports.ListenerTest.class)
public class TestBase {
	
	public static WebDriver browserdriver = null;
	
	public static BrowserUtilities browserUtil =new BrowserUtilities();
	public static ExtentReportsUtility extentsReports =new ExtentReportsUtility();
	public static CommonUtilities commonUtil = new CommonUtilities();
	public static String sclassNameForScreenShot="";
	
	public static String sPathBrowser=System.getProperty("user.dir")+"\\src\\main\\resources\\properties\\conf.properties";
	public static String sBrowserName="";
	public static String sSalesForceAppURL="";
	public static String sTekArchAppURL="";
	public static String sRestAPIURL="";
	
	public static String sTestDataPath=System.getProperty("user.dir")+"\\src\\main\\resources\\properties\\testdata.properties";
	public static String sUserName;
	public static String sPassword;
	public static String sEmailID;
	public static String sForgotPassword;
	public static String sPasswordResetmsg;
	public static String un;
	public static String sResendemail;
	public static String sUserNameInvalid;
	public static String sPasswordInvalid;
	public static String sUserNameNavLabel;
	public static String sLastName;

	public static String slog4jPath=System.getProperty("user.dir")+"\\src\\main\\resources\\properties\\log4j.properties";
	public static Logger log = Logger.getLogger(TestBase.class);
	

	@BeforeSuite
	public void TriggeringAllDependencies() throws Exception{
		
		commonUtil.loadLog4jPropertyFile(slog4jPath);
		commonUtil.loadConfigPropertyFile(sPathBrowser);
		browserUtil.launchBrowser(System.getProperty("browser"));
//		browserUtil.getAppURL(System.getProperty("salesforceurl"));
		commonUtil.loadPropertyFile(sTestDataPath);
		sUserName= System.getProperty("username");
		sPassword= System.getProperty("password");
		sEmailID=System.getProperty("emailid");
		sForgotPassword = System.getProperty("forgotpsswd");
		sPasswordResetmsg = System.getProperty("psswdresetmsg");
		un= System.getProperty("un");
		sResendemail=System.getProperty("resendtheemail");
		sUserNameInvalid= System.getProperty("invalidusername");
		sPasswordInvalid= System.getProperty("invalidpassword");
		sUserNameNavLabel=System.getProperty("userNavLabel");
		sLastName=System.getProperty("lastName");
	}
		
	@AfterSuite
	public void TearDownAllDepencies() throws Exception{
		browserdriver.quit();

		log.info("Quit all instances of the browser " +sPathBrowser);

	}


}
