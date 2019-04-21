package com.api.com.api.testing;

import java.io.File;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.com.api.testing.SubScripts.Dataprov;
import com.api.com.api.testing.SubScripts.Login1;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Login extends SetupClass {

	@BeforeClass
	public void className() {

		setsTestCaseName(this.getClass().getSimpleName());
		super.setupReports();
		// Constant.setIP("Test");
	}

	@Test(dataProvider = "TestData", dataProviderClass = Dataprov.class, groups = { "app" })
	public static void Log(String... args) throws Exception {
		if (args[0].equalsIgnoreCase("Y")) {
			Login1.Login(args);
		} else {
			logger.log(LogStatus.SKIP, Constant.SKIP);
		}
	}

}