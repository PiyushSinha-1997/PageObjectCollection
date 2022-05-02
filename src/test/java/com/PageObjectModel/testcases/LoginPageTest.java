package com.PageObjectModel.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PageObjectModel.pages.HomePage;
import com.PageObjectModel.pages.LoginPage;

public class LoginPageTest extends BaseClass{
	public LoginPage loginpage;
	public HomePage homepage;
	@Test(priority=1)
	public void loginPageTitleTest() {
		loginpage= new LoginPage(driver);
		log.info("Login Page");
		String title=loginpage.validateLoginpagetitle();
		log.info("Taking LoginPage title");
		System.out.println(title);
		Assert.assertEquals(title, "Cogmento CRM");
	}
	@Test(priority=2)
	public void logintest() {
		homepage=loginpage.login(usrname, pwd);
		log.info("Successfully logged into application");
	}
}
