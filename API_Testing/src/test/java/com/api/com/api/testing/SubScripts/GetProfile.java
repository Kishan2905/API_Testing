package com.api.com.api.testing.SubScripts;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;

import com.api.com.api.testing.Constant;
import com.api.com.api.testing.SetupClass;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.relevantcodes.extentreports.LogStatus;

public class GetProfile extends SetupClass{
	
	public static void Profile(String...args) {

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
	JsonPath js =  response.jsonPath(); 
	System.out.println(response.body().asString());
	int statusCode = response.getStatusCode();
	
	if(js.getInt("code")==0) {
		logger.log(LogStatus.PASS, "handle is: "+js.getString("data.twitterHandle"));
		//Reporter.log("Success : "+js.getString("data.twitterHandle"));
	}else {
		logger.log(LogStatus.FAIL, "Something went wrong "+ response.body().asString());
		//Reporter.log("Failed : "+js.getString("data.twitterHandle"));
		//Assert.assertEquals(statusCode, 200);
	}
	}
}
