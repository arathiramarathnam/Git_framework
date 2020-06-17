package com.testnglistenersandextentreports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.base.TestBase;

/** A listener for test running 
 * customizing the extent reports or testNG using listener events
 * onTestStart: Invoked each time before a test will be called.
 * onStart: Invoked when any test class starts/instantiated
 * onTestSuccess: Invoked when success of a test
 * onTestFailure: Invoked when failure of a test
 * onTestSkipped: Invoked when a test is skipped 
 * onTestFailedButWithinSuccessPercentage: Invoked each time when a test fails, but is within success percentage
 * onFinish: Invoked after all tests are run/executed
 * 
 * ITestListener is an interface with unimplemented methods and override/implement with methods body
 * since ITestListener interface extends ITestNGListener class
 * ITestResult: result of a test
 * ITestContext: a test context which contains all the information
 * for a given test run. An instance of this context is passed to the
 * test listeners so they can query information about their environment.
 * **/

public class ListenerTest extends ExtentReportsUtility implements ITestListener{
	
	public static ExtentReports reports=ExtentReportsUtility.createExtentHTMLReport();
	
	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite with multiple test cases started " + context.getAllTestMethods().getClass());
		
	}
	
	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println("New Test Started" + result.getMethod().getMethodName());
		ExtentReportsUtility.extenttest=reports.startTest(result.getMethod().getMethodName());
//		ExtentReportsUtility.createExtentHTMLReport();
		}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		 System.out.println("Test case passed" + result.getMethod().getMethodName());
		 try {
				extenttest.log(LogStatus.PASS, extenttest.addScreenCapture(ExtentReportsUtility.uftakeScreenShot(TestBase.browserdriver, TestBase.sclassNameForScreenShot+"_"+result.getMethod().getMethodName())));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		 System.out.println("Test case Failed" + result.getMethod().getMethodName());
		 try {
			 extenttest.log(LogStatus.FAIL, extenttest.addScreenCapture(ExtentReportsUtility.uftakeScreenShot(TestBase.browserdriver, TestBase.sclassNameForScreenShot+"_"+result.getMethod().getMethodName())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println("Test case Skipped" + result.getMethod().getMethodName());
		
		try {
			extenttest.log(LogStatus.SKIP, extenttest.addScreenCapture(ExtentReportsUtility.uftakeScreenShot(TestBase.browserdriver, TestBase.sclassNameForScreenShot+"_"+result.getMethod().getMethodName())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test Failed but within success percentage" + result.getMethod().getMethodName());
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println("TestSuite FINISHED with Test Cases PASSED!" + context.getPassedTests());
        System.out.println("TestSuite FINISHED with Test Cases FAILED!" + context.getFailedTests());
        reports.endTest(extenttest);
        reports.flush();
	}
	

}
