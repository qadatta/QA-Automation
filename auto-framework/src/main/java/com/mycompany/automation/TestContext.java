package com.mycompany.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestContext {

	private WebDriver driver = null;
	private TestAction pageAction;
	private Map<String, Object> data = new HashMap<String, Object>();
	private Map<String, Object> testObject = new HashMap<String, Object>();

	public static ThreadLocal<TestContext> thread = new ThreadLocal<TestContext>()
	{
		protected TestContext initialValue() {
			return new TestContext();
		}
	};

	public TestContext()
	{
//		System.out.println(">>>>>>>>>>>"+System.getProperty("java.class.path"));
//		System.out.println("Configuration.REMOTE_EXECUTE>>"+Configuration.REMOTE_EXECUTE);
		if("true".equalsIgnoreCase(Configuration.REMOTE_EXECUTE))
		{
			createRemoteWebDriver();
		}
		else
		{
			createLocalWebDriver();
		}
		
		pageAction = new TestAction(driver);
	}
	
	public void createLocalWebDriver()
	{
		if("FF".equalsIgnoreCase(Configuration.BROWSER) || "firefox".equalsIgnoreCase(Configuration.BROWSER))
		{
			System.setProperty("webdriver.gecko.driver", Configuration.sProjectLocation + "/src/main/resources/drivers/geckodriver.exe");
			
			driver = new FirefoxDriver();
		}
		else if("chrome".equalsIgnoreCase(Configuration.BROWSER) )
		{
			//System.out.println(">>>>>>>>>"+Configuration.sProjectLocation + "/src/main/resources/drivers/chromedriver.exe");
			//System.out.println(Configuration.sProjectLocation + "/src/main/resources/drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", Configuration.sProjectLocation + "/src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();

		}
		else if("IE".equalsIgnoreCase(Configuration.BROWSER) || "Internet Explorer".equalsIgnoreCase(Configuration.BROWSER))
		{
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

			System.out.println(Configuration.sProjectLocation + "/src/main/resources/drivers/IEDriverServer.exe");
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", Configuration.sProjectLocation + "/src/main/resources/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);

		}
		
	}
	
	public void createRemoteWebDriver()
	{
		DesiredCapabilities capability = new DesiredCapabilities();
		
		if("firefox".equalsIgnoreCase(Configuration.BROWSER)|| "FF".equalsIgnoreCase(Configuration.BROWSER))
		{
			System.setProperty("webdriver.gecko.driver", Configuration.sProjectLocation + "/src/main/resources/drivers/geckodriver.exe");

			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
		}
		else if("chrome".equalsIgnoreCase(Configuration.BROWSER))
		{
			System.setProperty("webdriver.chrome.driver", Configuration.sProjectLocation + "/src/main/resources/drivers/chromedriver.exe");
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
		}
		
		if("windows".equalsIgnoreCase(Configuration.PLATFORM))
		{
			capability.setPlatform(Platform.WINDOWS);
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", Configuration.sProjectLocation + "/src/main/resources/drivers/IEDriverServer.exe");

		}
		else if("linux".equalsIgnoreCase(Configuration.PLATFORM))
			capability.setPlatform(Platform.LINUX);
		else if("android".equalsIgnoreCase(Configuration.PLATFORM))
			capability.setPlatform(Platform.ANDROID);
		else
			capability.setPlatform(Platform.ANY);

		try {
			
			driver = new RemoteWebDriver(new URL(Configuration.HUB_URL), capability);
			driver.manage().timeouts().implicitlyWait(Configuration.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			System.out.println("Error while initializing webdriver ");
			e.printStackTrace();
		}
		
	}
	
	
	public WebDriver getWebDriver()
	{
		return driver;
	}
	
	public static TestContext get()
	{
		return thread.get();
	}
	public TestAction getTestAction()
	{
		return this.pageAction;
	}
	
	public TestContext put(String key, Object value) {
		data.put(key, value);
		return this;
	}

	public Object get(String key) {
		return data.get(key);
	}
	
	public TestContext update(String key, Object newValue) {
		delete(key);
		put(key, newValue);
		return this;
	}

	public void delete(String key) {
		data.remove(key);
	}
	
	public Object getTestObject(String key) {
		return this.testObject.get(key);
	}
	public TestContext putTestObject(String key, Object value) {
		this.testObject.put(key, value);
		return this;
	}

}
