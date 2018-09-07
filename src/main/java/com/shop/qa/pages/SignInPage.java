package com.shop.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shop.qa.base.TestBase;

public class SignInPage extends TestBase {

	@FindBy(xpath="//input[@name='loginId']")
	WebElement emailid;
	

	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@name='submit-btn']")
	WebElement submitbtn;
	
	@FindBy(xpath="//div[@id='login-content']//div[@class='fm-label-wrap clr']//label[1]")
	WebElement accountlbl;
	
	
	@FindBy(xpath="//input[@name='_fm.m._0.k']")
	WebElement staysigninchkbox;
	
	
	// Intilise Page Factory Using Constructor
	
	public SignInPage() {
		PageFactory.initElements(driver, this);
	}

	
	
}
