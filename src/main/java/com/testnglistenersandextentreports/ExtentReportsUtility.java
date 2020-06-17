package com.testnglistenersandextentreports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.base.TestBase;

public class ExtentReportsUtility {
	
	public static ExtentReports reports;
	public static ExtentTest extenttest;

	public static ExtentReports createExtentHTMLReport() {
		
		String uniqueDateTime=new SimpleDateFormat("'SalesforceApp_'yyyyMMddHHmmss.SSS''").format(new Date());
		String sExtentReportsDir = "/ExtentReports/";
		String sExtentReportPath=System.getProperty("user.dir") +sExtentReportsDir+ "TestNGReport_"+uniqueDateTime+".html";
	//	extenttest.log(LogStatus.INFO, "Reports html file has been created successfully");
		return reports=new ExtentReports(sExtentReportPath);
	}
	
	public static String uftakeScreenShot(WebDriver driver, String classname) throws Exception{
		TakesScreenshot shot = (TakesScreenshot)TestBase.browserdriver; 
		File sourceFile = shot.getScreenshotAs(OutputType.FILE);
		String sScreenshotsDir="/screenshots/";
		String uniqueDateTime=new SimpleDateFormat("'SalesforceReport_'yyyyMMddHHmmss.SSS'.html'").format(new Date());
		String sImageFile = classname+uniqueDateTime+".png";
		String sPathOfDestinationImage = System.getProperty("user.dir") +sScreenshotsDir+ sImageFile;
		File destFile = new File(sPathOfDestinationImage);
		try {
			FileUtils.copyFile(sourceFile, destFile);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sPathOfDestinationImage;
//		extenttest.log(LogStatus.PASS, logger.addScreenCapture(sPathOfDestinationImage);
	}
	
	
	
}
