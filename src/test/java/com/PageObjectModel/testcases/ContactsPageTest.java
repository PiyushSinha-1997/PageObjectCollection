package com.PageObjectModel.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PageObjectModel.pages.ContactsPage;
import com.PageObjectModel.pages.HomePage;
import com.PageObjectModel.pages.LoginPage;

public class ContactsPageTest extends BaseClass {
	public LoginPage login;
	public HomePage homepage;
	public ContactsPage contactpage;

	@Test(priority = 1)
	public void verifycontactPagelabelTest() {
		login = new LoginPage(driver);
		login.login(usrname, pwd);
		contactpage = homepage.clickonContactLink();
		Assert.assertTrue(contactpage.VerifyContactsPageLabel(), "Contact Page Label is missing in Page");
	}

	@Test(priority = 2)
	public void selectcheckboxNameTest() {
		contactpage.selectcheckboxName();
	}
}
