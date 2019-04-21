package com.api.com.api.testing.SubScripts;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.json.simple.JSONObject;
import org.openqa.selenium.remote.ProtocolHandshake.Result;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.api.com.api.testing.Constant;
import com.api.com.api.testing.SetupClass;
import com.google.gson.JsonObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
//import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Login1 extends SetupClass{

	 
	//@Test(dataProvider="TestData")

	@SuppressWarnings("unchecked")
	public static void Login(String...args) {

		
		RestAssured.baseURI =SetupClass.getbaseUri(Constant.IP, args[1]);

		RequestSpecification rs= SetupClass.requestSpecification();
		
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			list.add(args[i]);
		}
		list.remove(0); // removes the first item
		list.remove(0); //removes 1st element of updated list
		System.out.println(list);
		Response response = SetupClass.getPostResponse(SetupClass.requestParams(list), rs);
		

	//Case with correct details 	
		//Response response = SetupClass.getPostResponse(SetupClass.requestParams(list), rs);
		JsonPath js =  response.jsonPath(); 
		System.out.println(response.body().asString());
		int statusCode = response.getStatusCode();
		
		if(js.getInt("code")==0) {
			logger.log(LogStatus.PASS, "Login successful, token is "+js.getString("data.token"));
			Reporter.log("Auth Key is : "+js.getString("data.token"));
			SetupClass.authKey=js.getString("data.token");
		}else {
			logger.log(LogStatus.FAIL, "Something went wrong "+ response.body().asString());
			//Assert.assertEquals(statusCode, 200);
		}
	
	}


	public static void Social(String[] args) {
		
		
		RestAssured.baseURI =SetupClass.getbaseUri(Constant.IP, args[1]);

		RequestSpecification rs= SetupClass.requestSpecification();
	
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			
			list.add(args[i]);

		}
		
		list.remove(0); // removes the first item
		list.remove(0); //removes 1st element of updated list
		Response response = SetupClass.getPostResponse(SetupClass.requestParams(list), rs);
		
		JsonPath js =  response.jsonPath();

		System.out.println(response.body().asString());
		
		if(js.getInt("code")==0) {
			
			logger.log(LogStatus.PASS, "Social Test, id is "+js.getString("data.profile._id"));
			Reporter.log("User id: "+js.getString("data.profile._id"));
			SetupClass.authKey=js.getString("data.token");
			
		}else {
			logger.log(LogStatus.FAIL, "something went wrong  "+ response.body().asString());
		}
		
	}
}
