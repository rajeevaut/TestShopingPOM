package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {
	static Workbook book;
	static Sheet sheet;
	// Read Excel file and return two dimension object
	 @DataProvider(name="SearchProvider")
			public static Object[][] getTestData(Method m){
				FileInputStream file = null;
				try {
					
					file =new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\shop\\qa\\testdata\\TestData.xlsx");
					//System.out.println(System.getProperty("user.dir") + "\\src\\main\\java\\com\\shop\\qa\\testdata\\TestData.xlsx");
					//file = new FileInputStream(TESTDATA_SHEET_PATH);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					book = WorkbookFactory.create(file);
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				sheet = book.getSheet("TC001_SearchProduct");
				Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				//System.out.println(sheet.getLastRowNum() + "--------" + sheet.getRow(0).getLastCellNum());
				for (int i = 0; i < sheet.getLastRowNum(); i++) {
					for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
						data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
						
						
						// System.out.println(data[i][k]);
					}
				}
				return data;
			}
			
}
