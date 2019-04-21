package com.api.com.api.testing;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.gargoylesoftware.htmlunit.javascript.host.Map;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SetupClass {

	
	private static String sTestCaseName;
	public static String authKey=null;
	public static ExtentTest logger;
	//public ExtentReports extent = new ExtentReports(Constant.Path_TestReports,true);
	
	public static SoftAssert assert1 = new SoftAssert();
	public static String getsTestCaseName() {
		return sTestCaseName;
	}

	public void setupReports() {
		Constant.extent.loadConfig(new File("D:\\Appium\\ApiTesting\\test-output\\config.xml"));
		logger = Constant.extent.startTest(getsTestCaseName());
	}
	
	public void setsTestCaseName(String sTestCaseName) {
		this.sTestCaseName = sTestCaseName;

	}

	public static RequestSpecification request;
	public static String token;
	public static JSONObject requestParams;
	
	public static String getbaseUri(String server,String endPoint) {
		String uri=server+endPoint;
		return uri;
	}
	
	public static RequestSpecification requestSpecification() {
		request = null;
		request=RestAssured.given();
		return request;
	}
	
	public static Response getPostResponse(JSONObject requestParams,RequestSpecification rs) {
		rs.header("Content-Type", "application/json");
		if(authKey!=null) {
		rs.header("Authorization",authKey);
		}
		rs.body(requestParams.toJSONString());
		System.out.println(requestParams.toJSONString());
		Response response=rs.post();
		return response;
	}
	
	@SuppressWarnings("unchecked")
	//String parameter Value
	public static JSONObject requestParams(String...paramNameValue) {
		
		//requestParams.put(paramName, value);
		requestParams=new JSONObject();
		if(paramNameValue.length!=0) {
		for (int i = 0; i < paramNameValue.length-1; i++) {
			requestParams.put(paramNameValue[i],paramNameValue[++i]);
			
		}
		}
		return requestParams;
		
	}

	@SuppressWarnings("unchecked")
	public static JSONObject requestParams(List<String> list) {
		requestParams=new JSONObject();
		if(list.size()!=0) {
			for (int i = 0; i < list.size()-1; i++) {
				
				requestParams.put(list.get(i),list.get(i+1));
				//System.out.println(list.get(i)+","+list.get(++i));
				//--i;
				System.out.println(i);
			}
			}
		return requestParams;
	}
	
/*public static JSONObject requestParams(Integer...integers) {
		
		if(integers.length!=0) {
		for (int i = 0; i < integers.length; i++) {
			requestParams.put(integers[i],integers[i]);
			//i=i+1;
		}
		}
		return requestParams;
		
	}

public static JSONObject requestParams(Map...maps) {
	
	if(strings.length!=0) {
	for (int i = 0; i < strings.length; i++) {
		requestParams.put(strings[i],strings[i]);
		//i=i+1;
	}
	}
	return requestParams;
	}*/
	
/*	@BeforeClass
	public void className(){
		
		setsTestCaseName(this.getClass().getSimpleName());
		setupReports();
		//Constant.setIP("Test");
	}*/
	
	@BeforeSuite
	public void server() {
		String server = JOptionPane.showInputDialog("Enter server on which you want to run the test cases");
		Constant.setIP(server);
		System.out.println(Constant.IP);
		setsTestCaseName("SetUP");
		setupReports();
		logger.log(LogStatus.INFO, "Server set to: "+Constant.IP);
	}
	
	@AfterSuite
	public void stopExtent() {
		Constant.extent.endTest(logger);
		Constant.extent.flush();
	}
	
}
	

