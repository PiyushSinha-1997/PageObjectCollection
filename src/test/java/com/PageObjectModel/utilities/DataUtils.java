package com.PageObjectModel.utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.PageObjectModel.testcases.BaseClass;



public class DataUtils extends BaseClass{

	@DataProvider(name="dp")
	public Object[][] getData(Method m) throws IOException {
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testdata/LoginData.xlsx";
		String sheetName = m.getName();
		System.out.println(sheetName);
		int rowcount = excel.getRowCount(path, sheetName);
		int colcount = excel.getCellCount(path, sheetName, rowcount);
		System.out.println("Total row count " + rowcount + "Total column count " + colcount);
		Object[][] data = new Object[rowcount][colcount];
		for (int rows = 1; rows <= rowcount; rows++) {
			for (int cols = 0; cols < colcount; cols++) {
				data[rows - 1][cols] = excel.getCellData(path, sheetName, rows, cols);
			}
		}

		return data;

	}
}
