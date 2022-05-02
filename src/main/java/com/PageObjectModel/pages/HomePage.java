package com.PageObjectModel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
public WebDriver driver;

public HomePage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
	//Locators
@FindBy(xpath="//span[text()='Piyush Golu']")
public WebElement usernameLabel;
@FindBy(xpath="//span[contains(text(),'Contacts')]")
public WebElement contactslink;
@FindBy(xpath="//span[contains(text(),'Deals')]")
public WebElement DealsLink;
@FindBy(xpath="//span[contains(text(),'Tasks')]")
public WebElement TasksLink;

//Actions
public String verifyHomePagetitle() {
	return driver.getTitle();
}
 public ContactsPage clickonContactLink() {
	 contactslink.click();
	 return new ContactsPage();
 }
public DealsPage clickonDealsPage() {
	DealsLink.click();
	return new DealsPage();
}

public TasksPage clickonTaskPagelink() {
	TasksLink.click();
	return new TasksPage();
}
public boolean VerifyCorrectUserName() {
	return usernameLabel.isDisplayed();
}
}
