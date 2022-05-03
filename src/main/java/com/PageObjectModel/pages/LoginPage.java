package com.PageObjectModel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(name = "email")
	public WebElement username;
	@FindBy(name = "password")
	public WebElement password;
	@FindBy(xpath = "//div[text()='Login']")
	public WebElement loginbtn;

	// Actions
	public String validateLoginpagetitle() {
		return driver.getTitle();
	}

	public HomePage login(String usrname, String pwd) {
		username.sendKeys(usrname);
		Actions action = new Actions(driver);
		action.click(password);
		password.sendKeys(pwd);
		loginbtn.click();
		return new HomePage(driver);
	}
}
