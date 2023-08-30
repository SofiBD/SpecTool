package com.qa.factory;

import com.qa.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the thradlocal driver on the basis of given
	 * browser
	 * 
	 * @param browser
	 * @return this will return tldriver.
	 */
	public WebDriver init_driver(String browser) throws MalformedURLException {

		System.out.println("browser value is: " + browser);
		if(new ConfigReader().init_prop().get("isLocal").equals("true")) {
			if (browser.equals("chrome")) {
				//WebDriverManager.chromedriver().setup();
				System.setProperty("webdriver.chrome.driver", "C:\\Browsers\\Chrome\\chromedriver-win64\\chromedriver.exe");
			//	driver = new ChromeDriver();
				tlDriver.set(new ChromeDriver());
			} else if (browser.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				tlDriver.set(new FirefoxDriver());
			} else if (browser.equals("safari")) {
				tlDriver.set(new SafariDriver());
			} else {
				System.out.println("Please pass the correct browser value: " + browser);
			}

		}else {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setCapability("browserVersion", "74");
			chromeOptions.setCapability("platformName", "Windows 10");
			 driver = new RemoteWebDriver(new URL("http://local:127.0.0.1/wd/hub"), chromeOptions);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().getCookies();
	//	getDriver().manage().timeouts().pageLoadTimeout(400, TimeUnit.MILLISECONDS);
		getDriver().get(new ConfigReader().init_prop().getProperty("url"));
		return getDriver();

	}

	public static void untilElementIsVisible(WebDriver webDriver, WebElement webElement, Long timeOutInSeconds) {
		new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
	}

	/**
	 * this is used to get the driver with ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
