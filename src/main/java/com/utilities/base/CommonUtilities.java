package com.utilities.base;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;

public class CommonUtilities {
	
 	public void loadPropertyFile(String propertiesfilepath) throws Exception {
 		TestBase.log.info("Test Data Properties File path: " +propertiesfilepath);
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(propertiesfilepath);
		prop.load(file);
		System.getProperties().putAll(prop);
	}
 	 		
 	public void loadLog4jPropertyFile(String sPathLog4jPropertyFile) throws Exception{
 		TestBase.log.info("Log4j Properties File path: " +sPathLog4jPropertyFile);
 		Properties props = new Properties();
 		FileInputStream fileinput = new FileInputStream(sPathLog4jPropertyFile);
 		props.load(fileinput);
 		PropertyConfigurator.configure(props);
 		}
 	
 	public void loadConfigPropertyFile(String sPathconfigPropertyFile) throws Exception{
 		TestBase.log.info("Config Properties File path: " +sPathconfigPropertyFile);
 		Properties props = new Properties();
 		FileInputStream fileinput = new FileInputStream(sPathconfigPropertyFile);
 		props.load(fileinput);
 		System.getProperties().putAll(props);
 		}
 	

}
