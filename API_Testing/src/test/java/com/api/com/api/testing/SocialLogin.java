package com.api.com.api.testing;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.com.api.testing.SubScripts.Dataprov;
import com.api.com.api.testing.SubScripts.Login1;
import com.relevantcodes.extentreports.LogStatus;


public class SocialLogin extends SetupClass {

	@BeforeClass
	public void className(){
		
		setsTestCaseName(this.getClass().getSimpleName());
		super.setupReports();
		//Constant.setIP("Test");
	}
	
	@Test(dataProvider="TestData",dataProviderClass=Dataprov.class)
	public void Login(String...args) throws Exception {
		if(args[0].equalsIgnoreCase("Y")) {
		Login1.Social(args);
		}
		else {
			System.out.println(logger);
			logger.log(LogStatus.SKIP, Constant.SKIP);
		}
	}
}
/*public void Log(String apiEndPoint,String smTokenKey,String smTokenValue,String smId,String run) throws Exception {
if(run.equalsIgnoreCase("Y")) {
Login1.Social(apiEndPoint,smTokenKey,smTokenValue, smId);
}
else {
	logger.log(LogStatus.SKIP, Constant.SKIP);
}
}*/