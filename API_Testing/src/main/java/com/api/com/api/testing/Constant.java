package com.api.com.api.testing;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Constant {

	 public static final String Path_TestData = "D:\\Appium\\ApiTesting\\src\\main\\java\\data\\TestData.xlsx";
	 public static final String Path_TestReports = "D:\\Appium\\ApiTesting\\test-output\\Reports.html";
	 public static final ExtentReports extent = new ExtentReports(Constant.Path_TestReports,true);
	 public static final String File_TestData = "API";
	 public static String IP=null;
	 public static final String SKIP = "You Skipped this Test case";
	 
	 
	 public static void setIP(String server) {
		 if(server.equalsIgnoreCase("Test")) {
			 IP="";  //test server IP
		 }
		 else {
			 IP=""; //live server IP
		 }
	 }
	 

}
