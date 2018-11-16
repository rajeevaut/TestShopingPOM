package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.commons.io.FileUtils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.shop.qa.base.TestBase;

public class TestUtil extends TestBase{

	public static long PAGE_LOAD_TIMEOUT = 200;
	public static long IMPLICIT_WAIT = 100;
	public static int EXPLICIT_TIMEOUT = 100; 
	
	
	
	
		public static void takeScreenshotAtEndOfTest(String methodname) throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			
			
			FileUtils.copyFile(scrFile, new File(currentDir + "/Screenshots/" + methodname + ".png"));
			
			}
		
}
