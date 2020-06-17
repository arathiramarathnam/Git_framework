package com.utilities.base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtilities {
	
//	public static TestBase base =new TestBase();
//	public static Logger log = Logger.getLogger(TestBase.class);

	public void launchBrowser(String sBrowser) throws Exception{
		
		if(sBrowser.equalsIgnoreCase("Chrome")|| sBrowser.startsWith("ch")) {
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "C:\Users\arath\eclipse\com.qa.Selenium.web.browser\chromedriver.exe");
		TestBase.browserdriver = new ChromeDriver();
		}else if (sBrowser.equalsIgnoreCase("Fire")|| sBrowser.startsWith("fi")) {
			WebDriverManager.firefoxdriver().setup();
			TestBase.browserdriver=new FirefoxDriver();
		}else if (sBrowser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			TestBase.browserdriver=new EdgeDriver();
		}else if (sBrowser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			TestBase.browserdriver=new EdgeDriver();
		}else {
			TestBase.log.info("You have not launched browser type correctly "+sBrowser);
		}
	TestBase.log.info("launched the browser "+sBrowser);
	TestBase.browserdriver.manage().window().maximize(); 
		
	}
	
	public String getAppURL(String sAppURL) throws Exception 	{
		if (sAppURL.contains("qa")) {
			TestBase.browserdriver.get(sAppURL);
		}else if (sAppURL.contains("salesforce")) {
			TestBase.browserdriver.get(sAppURL); 
		}else if(sAppURL.contains("qa01")){
			TestBase.browserdriver.get(sAppURL); 
		}else {
			TestBase.log.info("You are not launching the correct AppURL "+sAppURL);
		}
		
		TestBase.log.info("Launching the AppURL "+sAppURL);
		return sAppURL;
	}
		
	public void waitForPageElementToVisible(WebDriver driver, WebElement eleToWait) throws Exception{
		WebDriverWait wait = new WebDriverWait(TestBase.browserdriver, 50);
		wait.until(ExpectedConditions.visibilityOf(eleToWait));
		if(eleToWait.isDisplayed()){
			TestBase.log.info("Element is displayed " + eleToWait);
		}else {
			TestBase.log.info("Element is still displaying.... " + eleToWait);
		}
	}
	@SuppressWarnings("deprecation")
	public boolean waitForPageElementToVisibleWithPollingTime(WebDriver driver, WebElement eleToWait, int iTimeInSeconds) throws Exception {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(TestBase.browserdriver)
				.withTimeout(iTimeInSeconds, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(new Function<WebDriver, WebElement>(){
		public WebElement apply(WebDriver driver) {
			if (eleToWait.isDisplayed()) {
			TestBase.log.info("Element is displayed " + eleToWait);
			return eleToWait;
			} else {
			TestBase.log.info("Element is NOT displaying.... " + eleToWait);
			return null;
			}
		}
		});
		return !eleToWait.isDisplayed();
	}
	
	public void standardWaitForAllPageElementsToVisible(WebDriver driver, WebElement eleToWait) throws Exception{
		TestBase.browserdriver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		if (eleToWait.isDisplayed()) {
			TestBase.log.info("Element is displayed " + eleToWait);
		} else {
			TestBase.log.info("Element is still displaying.... " + eleToWait);
		}
	}
	
	public boolean isDisplayed(WebElement ele) throws Exception{
		boolean bisDisplayFlag=false;
		if(ele.isDisplayed()){
			bisDisplayFlag=true;
			TestBase.log.info("Element is displayed " + ele);
			
		}else {
			bisDisplayFlag=false;
			TestBase.log.info("Element is not displaying.... " + ele);
		}
		return bisDisplayFlag;
		}
	
	public boolean isSelected(WebElement ele) throws Exception{
		boolean bisSelectflag=false;
		if(ele.isSelected()){
			bisSelectflag=true;
			TestBase.log.info("Element is selected already " + ele);
			}else {
			ele.click();
			bisSelectflag=true;
			TestBase.log.info("Element is selected.... " + ele);
		}
		return bisSelectflag;
	}
	
	public boolean isEnabled(WebElement ele) throws Exception{
		boolean bisEnabledflag=false;
		if(ele.isEnabled()){
			bisEnabledflag=true;
			TestBase.log.info("Element is enabled for input " + ele);
			
		}else {
			bisEnabledflag=false;
			TestBase.log.info("Element is not enabled .... " + ele);
		}
		return bisEnabledflag;
	}
	
	public boolean ufsrollintoViewEle(WebElement ele, boolean bIsViewflag)	throws Exception{
		bIsViewflag=false;
		JavascriptExecutor js=(JavascriptExecutor) TestBase.browserdriver;
		((JavascriptExecutor) TestBase.browserdriver).executeScript("arguments[0].scrollIntoView()", ele);
		bIsViewflag=true;
		return bIsViewflag;
	}
	
	public void ufclickeleusingJS(WebElement ele) throws Exception{
		JavascriptExecutor executor=(JavascriptExecutor) TestBase.browserdriver;
		((JavascriptExecutor) TestBase.browserdriver).executeScript("arguments[0].click();", ele);
	}
	
	public void ufsendKeys(WebElement ele, String sendKeys, String objName) throws Exception{
		if(ele.isDisplayed()){
			ele.sendKeys(sendKeys);
			TestBase.log.info("passing value to element parameters... " + ele);
		}
	}
	
	public void ufClick(WebElement ele) throws Exception{
		if(ele.isDisplayed()){
			ele.click();
			TestBase.log.info("Element is displayed and perform click... " + ele);
		}else {
			TestBase.log.info("Element is not displayed to perform click action... " + ele);
		}
	}
	
//	public String ufgetText(WebElement ele, String stext, String sExpectedtext) throws Exception{
//		stext=ele.getText();
//		if(ele.getText().trim().equalsIgnoreCase(sExpectedtext) || ele.getText().trim().contains(sExpectedtext)) {
//			TestBase.log.info("expected text is displayed for the element... " + ele.getText());
//			}else {
//			TestBase.log.info("expected text is not displayed for the element... " + ele.getText());
//			}
//		return ele.getText();
//		}
	
	public String ufgetText(WebElement ele) throws Exception{
		return ele.getText();
		}
	
	
	public String ufgetAttributeValue(WebElement ele, String attribute) throws Exception{
		return ele.getAttribute(attribute);
		}
	
	public void ufelementClear(WebElement ele) throws Exception{
		ele.clear();
		}
	
	

	public void MouseOver(WebElement ele) throws Exception{
		if (ele.isDisplayed()) {
			Actions actObj=new Actions(TestBase.browserdriver);
			actObj.moveToElement(ele).click().build().perform();
			TestBase.log.info("element is displayed to mouseover  " + ele);
		} else {
			TestBase.log.info("element is not displayed to mouseover  " + ele);
			}
		
		}
	
	public void MouseOverClick(WebElement ele) throws Exception{
		if (ele.isDisplayed()) {
			Actions actObj=new Actions(TestBase.browserdriver);
			actObj.moveToElement(ele).click().build().perform();
			TestBase.log.info("element is displayed to mouseover and click " + ele);
		} else {
			TestBase.log.info("element is not displayed to mouseover and click " + ele);
			}
		
		}
	
	public String ufGetTimeStamp(){
		DateFormat dateFormat=DateFormat.getDateTimeInstance();
		Date date=new Date();
		String DateValue=dateFormat.format(date);
		DateValue=DateValue.replaceAll(":", "_");
		DateValue=DateValue.replaceAll(",", "");
		return DateValue;
		}
	
	public String ufTakeImage(WebDriver driver, String sDestFilePath, String sclassName) throws IOException{
		String timestamp=ufGetTimeStamp();
		String simagesDir="/screeenshots/";
		TakesScreenshot srcShot=(TakesScreenshot) driver;
		File srcfileObj= srcShot.getScreenshotAs(OutputType.FILE);
		sDestFilePath=System.getProperty("user.dir")+ simagesDir;
		String destFile=sclassName+ timestamp+ ".png";
		sDestFilePath=sDestFilePath + destFile;
		File DestFileObj=new File(sDestFilePath);
		FileUtils.copyFile(srcfileObj, DestFileObj);
		return sDestFilePath;
		}
	public void ufScreenShotBrowserforWebElement(WebDriver driver, String classname) throws Exception {
		String destDir= "/screenshots/";
		TakesScreenshot srcshot =((TakesScreenshot) TestBase.browserdriver);
		File srcFile=srcshot.getScreenshotAs(OutputType.FILE);
		String destFile=classname + ".png";
		String sPathImage = System.getProperty("user.dir") + destDir + destFile;
		File destinationFile =new File(sPathImage);
		try {
			FileUtils.copyFile(srcFile, destinationFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//select the dropdown using "select by visible text", so pass VisibleText as 'Yellow' to function
	public void ufselectByVisibleText(WebElement WE, String VisibleText){
		Select selObj=new Select(WE);
		selObj.selectByVisibleText(VisibleText);
		}
		 
		//select the dropdown using "select by index", so pass IndexValue as '2'
		public void ufselectByIndex(WebElement WE, int IndexValue){
		Select selObj=new Select(WE);
		selObj.selectByIndex(IndexValue);
		}
		 
		//select the dropdown using "select by value", so pass Value as 'thirdcolor'
		public void ufSelectByValue(WebElement WE, String Value){
		Select selObj=new Select(WE);
		selObj.selectByValue(Value);
		}
		
		public void ufRadiobutton(WebElement ele, String objName) {
			if(ele.isDisplayed() ){
				ele.click();
			}
		}
		
		public void ufselectDropdown(WebElement ele, String objName) {
			
			if(ele.isDisplayed()) {
					
					if(ele.isSelected()) {
						TestBase.log.info("Element is already selected... " + ele);
					}else{
					ele.click();
					TestBase.log.info("Element is selected to perform click... " + ele);
					}
			}else {
				TestBase.log.info("Element is not displayed to perform click... " + ele);
				}	
			}
	
	public void ufselectCheckBox(WebElement ele, String objName) {
		
		if(ele.isDisplayed()) {
			if(ele.isSelected()) {
				TestBase.log.info("element is already selected"+ele);
				}
				else{
				ele.click();
				TestBase.log.info("element is selected to perform click"+ele);
				}
		}else {
			TestBase.log.info("element is selected to perform click"+ele);
		}	
		}	
		public static void ufAssertTest(String sActual, String sExpected) throws Exception{
		  Assert.assertEquals(sActual, sExpected); 
		}
	
	
		public static void AssertErrormsg(String actualmsg, String errormsg) {
		if(actualmsg.equals(errormsg)) {
			TestBase.log.error("returns the expected Errormessage");
			}else {
				TestBase.log.error("Not returns the expected Errormessage");
			 }
			}
	
	public void quitBrowser(){
		TestBase.browserdriver.quit();
	}
	
}