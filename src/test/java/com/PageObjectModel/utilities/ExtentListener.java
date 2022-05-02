package com.PageObjectModel.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.PageObjectModel.testcases.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentListener implements ITestListener {

	public ExtentSparkReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String screenshotname;

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + "@Test Case :" + result.getMethod().getMethodName());

		test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));

	}

	public void onTestFailure(ITestResult result) {

		BaseClass.capturescreenshot();

		test.fail(result.getThrowable());
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		screenshotname = BaseClass.srcfilename;
		test.fail("Screenshot is below", MediaEntityBuilder
				.createScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshot/" + screenshotname).build());

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.AMBER));
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repname = "Test-Report" + timestamp + ".html";
		htmlreporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repname);
		htmlreporter.config().setDocumentTitle("Automation Test Result");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter.config().setReportName("Automation Result");
		try {
			htmlreporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}

		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Automation Tester", "Piyush");
		extent.setSystemInfo("Build No", "234");
		extent.setSystemInfo("Organization", "Tata");
	}

	public void onFinish(ITestContext context) {
		if (extent != null)
			extent.flush();
	}

}
