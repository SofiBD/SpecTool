package com.pages;

import com.qa.factory.DriverFactory;
import com.qa.util.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SignupPage {

	private WebDriver driver;

	// 1. By Locators: OR
	private By login = By.xpath("//*[text()='Log In']");
	private By signup = By.xpath("//*[text()='Sign Up']");
	private By accept = By.xpath("//*[contains(text(),'Accept')]");

	private By continueButton = By.xpath("//*[text()='continue']");
	private By emailAddress = By.xpath("//input[@name='email']");
	private By nameInput = By.xpath
			("//input[contains(@class,'MuiInputBase-input') and @type='text' and @name!='email']");
	private By passwordInput = By.xpath("//input[@name='password']");

	private By companyName = By.xpath("//label[contains(text(),'Company Name')]//parent::div//following-sibling::div/input");

	private By companyAddress = By.xpath("//label[contains(text(),'Address')]//parent::div//following-sibling::div/input");

	private By countryInput = By.xpath("//label[contains(text(),'Country')]//parent::div//following-sibling::div//input");

	private By stateInput = By.xpath("//label[contains(text(),'State')]//parent::div//following-sibling::div//input");

	private By cityInput = By.xpath("//label[contains(text(),'City')]//parent::div//following-sibling::div//input");

	private By zipInput = By.xpath("//label[contains(text(),'Zip')]//parent::div//following-sibling::div//input");
	private By organization = By.xpath("//*[contains(@class,'MuiSelect-root')]");
	private By types = By.xpath("//ul[@role='listbox']//li");
	private By nextButton = By.xpath("//span[text()='Next']");
	private By roles = By.xpath("//div[contains(@class,'MuiCollapse-wrapper')]//following::div[contains(@class,'MuiCardContent-root')]/p");
	private By signupButton = By.xpath("//span[text()='sign up']");

	private By validateMessage = By.xpath("//p[contains(@class,'MuiTypography-body')]");

	// 2. Constructor of the page class:
	public SignupPage(WebDriver driver) {
		this.driver = driver;
	}

	// 3. page actions: features(behavior) of the page the form of methods:

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean homePageIsDisplayed() throws InterruptedException {
		driver.findElement(accept).click();
		Thread.sleep(1000);
		driver.findElement(login).isDisplayed();
		driver.findElement(signup).isDisplayed();
		return true;
	}
	public void userClicksSignUp(){
		driver.findElement(signup).click();
	}

	public void entersEmailId(String email){
		driver.findElements(emailAddress).get(0).sendKeys(email);
	}

	public void enterName(String fName, String lName) throws InterruptedException {
		driver.findElements(nameInput).get(0).sendKeys(fName);
		driver.findElements(nameInput).get(1).sendKeys(lName);
		Thread.sleep(500);
	}

	public void enterPassword(String pwd, String confirmPwd) {
		driver.findElements(passwordInput).get(0).sendKeys(pwd);
		driver.findElements(passwordInput).get(1).sendKeys(confirmPwd);
	}

	public void clickContinueButton(){
		driver.findElement(continueButton).click();
	}

	public void enterCompanyName(String name, String address) throws InterruptedException {
		driver.findElement(companyName).sendKeys(name);
		driver.findElement(companyAddress).sendKeys(address);
		Thread.sleep(1000);
	}

	public void enterCountry(String country) throws InterruptedException {
		driver.findElement(countryInput).sendKeys(country);
		Thread.sleep(1000);
		driver.findElements(types).get(0).click();
	}

	public void enterState(String state) throws InterruptedException {
		driver.findElement(stateInput).sendKeys(state);
		Thread.sleep(1000);
		driver.findElements(types).get(0).click();
	}

	public void enterCity(String city) throws InterruptedException {
		driver.findElement(cityInput).sendKeys(city);
		Thread.sleep(1000);
		driver.findElements(types).get(0).click();
	}

	public void enterZipcode(String zip) throws InterruptedException {
		driver.findElement(zipInput).sendKeys(zip);
		Thread.sleep(1000);
	}



	public void userSelectsOrganizationType() throws InterruptedException {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)","");
        driver.findElement(organization).click();
	    List<WebElement> list = driver.findElements(types);
		Thread.sleep(1000);
		for (WebElement we: list){
		  if (we.getText().equalsIgnoreCase(Constants.orgType)){
			  we.click();

		  }
	    }
	}

	public void clickNextButton(){
		DriverFactory.untilElementIsVisible(DriverFactory.getDriver(), driver.findElement(nextButton),2L);
		driver.findElement(nextButton).click();
	}

	public void selectRole() throws InterruptedException {
		List<WebElement> list = driver.findElements(roles);
		Thread.sleep(1000);
		for (WebElement we: list){
			if (we.getText().equalsIgnoreCase(Constants.role)){
				we.click();
			}
		}
		Thread.sleep(1000);
	}

	public void clicksFinalSignup() throws InterruptedException {
		driver.findElement(signupButton).click();

	}

	public String validateSuccessSignup() throws InterruptedException {
		Thread.sleep(3000);
		DriverFactory.untilElementIsVisible(DriverFactory.getDriver(), driver.findElement(validateMessage),10L);
		return driver.findElement(validateMessage).getText();
	}
}
