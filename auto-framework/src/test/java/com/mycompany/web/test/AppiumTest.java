package com.mycompany.web.test;

import org.openqa.selenium.WebDriver;

public class AppiumTest {
	WebDriver ad;
/*	
	@Test
	public void testOnAndroidChromeBrowserAndActualDevice_Test_1() throws MalformedURLException {
	
		AppiumDriver driver;
	
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.1.2");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"a06ace88");

		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);
		System.out.println(System.currentTimeMillis());
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		System.out.println(System.currentTimeMillis());

		driver.get("https://www.google.co.in/?gws_rd=ssl");
		driver.findElement(By.name("q")).sendKeys("appium test");
		
       // driver.f
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Page title is: "+ driver.getTitle());
		WebDriverWait  wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains("appium"));
		System.out.println("Page title after wait is: "+ driver.getTitle());

		driver.close();
		System.out.println("Test success...");
	}
	
	@Test
	public void testOnAndroidChromeBrowserAndActualDevice_Test_2() throws MalformedURLException
	{

		
		AppiumDriver driver;
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.1.2");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"a06ace88");
		
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
		//driver = new AndroidDriver(new URL("http://172.29.84.43:4444/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);
		System.out.println(System.currentTimeMillis());
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		System.out.println(System.currentTimeMillis());

		driver.get("http://www.searspartsdirect.com/");
		driver.findElement(By.id("modelSearchText")).sendKeys("9030");
		
       // driver.f
		driver.findElement(By.xpath("//*[@id='searchModelsMobile']/i")).click();
		WebDriverWait  wait = new WebDriverWait(driver, 120);
		System.out.println("::::::"+driver.findElementByCssSelector("div.pageTitleHeader").getText()+"-----");
		try {
			Thread.sleep(10000);
			System.out.println("::::::"+driver.findElementByCssSelector("div.pageTitleHeader").getText()+"-----");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElementByCssSelector("div.pageTitleHeader"), "Model # 9030 SINGER Mechanical Sewing Machines"));
		System.out.println("::::::"+driver.findElementByCssSelector("div.pageTitleHeader").getText()+"-----");

		driver.close();
		System.out.println("Test success...");
	
	}
	
	public void AppiumAndroidNativeBrowserTest() throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.BROWSER);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1.1");
		capabilities.setCapability(MobileCapabilityType.LAUNCH_TIMEOUT,"300000");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"300");
		try
		{
			ad = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			ad.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			ad.get("http://google.com");
			ad.findElement(By.name("q")).sendKeys("Appium Meetup Noida");
			ad.findElement(By.xpath("//button[@type='submit']")).click();
			try{
				ad.findElement(By.xpath("//li[1]/div/h3/a")).click();
			}catch(NoSuchElementException ne){
				ad.findElement(By.xpath("//article/section[1]/div/a")).click();
			}

			String meetupTitle = ad.findElement(By.xpath("//div[@class='doc-content ']/h1")).getText();
			Assert.assertEquals(meetupTitle, "Appium: Mobile Automation Made Awesome");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally{
//			ad.close();
			ad.quit();
		}
	}
*/
}
