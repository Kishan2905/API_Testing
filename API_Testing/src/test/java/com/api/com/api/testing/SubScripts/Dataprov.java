package com.api.com.api.testing.SubScripts;

import org.testng.annotations.DataProvider;


import com.api.com.api.testing.Constant;
import com.api.com.api.testing.ExcelUtil;
import com.api.com.api.testing.SetupClass;
import com.relevantcodes.extentreports.LogStatus;


public class Dataprov extends SetupClass{

	
private static int iTestCaseRow;

@DataProvider(name="TestData")
	  public static  Object[][] TestData() throws Exception{
		 ExcelUtil.setExcelFile(Constant.Path_TestData,Constant.File_TestData);
		
			iTestCaseRow = ExcelUtil.getRowContains(getsTestCaseName(),0);
			
			if(iTestCaseRow==0) {
				System.out.println("No Params in Excel corresponding to this Class");
				logger.log(LogStatus.ERROR, "NO Data in Excel");
			}
			
       Object[][] testObjArray = ExcelUtil.getTableArray(Constant.Path_TestData,Constant.File_TestData,iTestCaseRow);

       return (testObjArray);
	 }
}
