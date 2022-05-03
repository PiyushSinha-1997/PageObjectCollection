package com.PageObjectModel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	public WebDriver driver;
	//constructor
	public ContactsPage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory
	@FindBy(xpath="//*[@id=\"dashboard-toolbar\"]/div[1]/text()")
	public WebElement contactsLabel;
	
	//Actions
	public boolean VerifyContactsPageLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void selectcheckboxName() {
		driver.findElement(By.xpath("//a[text()='Piyush Sinha']//preceding::td")).click();
		
	}
}
