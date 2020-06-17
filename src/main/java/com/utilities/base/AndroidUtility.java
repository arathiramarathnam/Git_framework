package com.utilities.base;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidUtility {
	
	public void launchApp(String sAppName) throws Exception{
		// code to require to launch an app
		// 1. android/ ios 2. Device name = 3. Device Os 4.App package info 5.App activity
		/* JSON representation raw JSON
		{
			  "platformName": "ANDROID",
			  "deviceName": "ZY22437NNV",
			  "appPackage": "com.android.contacts",
			  "appActivity": "com.android.contacts.activities.PeopleActivity"
			}
*/
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");//"platformName"
//		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "ZY22437NNV");
		if(sAppName.equalsIgnoreCase("calc"))
		{
			capabilities.setCapability("appPackage", "com.google.android.calculator");
			capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		}else if(sAppName.equalsIgnoreCase("Contacts"))
		{
			capabilities.setCapability("appPackage", "com.android.contacts");
			capabilities.setCapability("appActivity",  "com.android.contacts.activities.PeopleActivity");
		}
	
		//launch appium app --> Then get the appium IP and port --> Bind it to your driver 
		// Appium become an server - hub --> Selenium code will become an node -->
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
//		TestBase.appiumdriver = new AndroidDriver<WebElement>(url, capabilities);
		System.out.println("App Launched");
	}


}
