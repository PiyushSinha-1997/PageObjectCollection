package com.PageObjectModel.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PageObjectModel.pages.HomePage;
import com.PageObjectModel.pages.LoginPage;

public class HomePageTest extends BaseClass {

	public LoginPage login;
	public HomePage homepage;
	@Test(priority = 1)
	public void verifyHomePagetitleTest() {
		
		login=new LoginPage(driver);
		log.info("Log in Page before HomePage");
		homepage = login.login(usrname, pwd);
		log.info("Log into application");
		String homepagetitle=homepage.verifyHomePagetitle();
		log.info("Taking HomePage title");
		Assert.assertEquals(homepagetitle, "Cogmento CRM","Home Page title not matched");
	}
	@Test(priority = 2)
	public void VerifyUserNameTest() {
		Assert.assertTrue(homepage.VerifyCorrectUserName());
		log.info("Verify User Name in HomePage");
	}
}
