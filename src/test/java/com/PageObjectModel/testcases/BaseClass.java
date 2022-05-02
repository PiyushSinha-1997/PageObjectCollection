package com.PageObjectModel.testcases;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.PageObjectModel.utilities.ConfigReader;
import com.PageObjectModel.utilities.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public ConfigReader config;
	Properties prop;
	public static WebDriver driver;
	public static Logger log;
	public String browsername;
	public static String srcfilename;
	public ExcelReader excel = new ExcelReader();
	public String usrname;
	public String pwd;

	public static void capturescreenshot() {
		Date d = new Date();
		srcfilename = d.toString().replace(" ", "_").replace(":", "_") + ".png";

		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {

			FileUtils.copyFile(srcfile, new File("./Screenshot/" + srcfilename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void setup() {
		prop = ConfigReader.init_prop();
		browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			log = Logger.getLogger(BaseClass.class);
			PropertyConfigurator.configure("./Log4j.properties");
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--disable-notifications");
			option.addArguments("--disable-infobars");
			option.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

			driver = new ChromeDriver(option);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(ConfigReader.getApplicationURL());
			usrname = ConfigReader.getusername();
			pwd = ConfigReader.getpassword();

		} else if (browsername.equals("firefox")) {
			log = Logger.getLogger(BaseClass.class);
			PropertyConfigurator.configure("./Log4j.properties");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(ConfigReader.getApplicationURL());
			usrname = ConfigReader.getusername();
			pwd = ConfigReader.getpassword();

		} else {
			System.out.println("Please pass correct browser " + browsername);
		}

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public String randomInt() {
		String randomInt = RandomStringUtils.randomNumeric(5);
		return randomInt;
	}

	public void switchframe() {
		driver.switchTo().frame(driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0")));
		driver.switchTo().frame(driver.findElement(By.id("ad_iframe")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Frame switched");
		driver.findElement(By.id("dismiss-button")).click();
		driver.switchTo().defaultContent();
	}

}
