package Academy;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.ITestResult;


import pageObjects.LandingPage;

import resources.base;

public class HomePage extends base{
	public  WebDriver driver;
	
	 
	@BeforeTest
	public void initialize() throws IOException
	{
	//to invoke browser
		 driver =initializeDriver();

	}
	@Test
	
	public void basePageNavigation() throws IOException, InterruptedException
	{
		

		
		System.out.println("inside Home page navigation");
		String urlName=prop.getProperty("url");
		System.out.println(urlName);
		
		//driver.get(prop.getProperty("url"));
		driver.get(urlName);
		System.out.println("homePageNavigation_inProgress");
		
		//Implementing page object model in the framework:
		// creating object to that class and invoke methods of it
		LandingPage l=new LandingPage(driver);
		
		
		
		//To select radiobutton for roundtrip:
		if(driver.findElement(By.xpath("//label[contains(text(),'Dates')]")) != null) {
		l.getTrip().click();
		}
		System.out.println(l.getTrip().isEnabled());
		
		//To enter source in autosuggestive dropdown:
		driver.findElement(By.id("from1")).clear();
		driver.findElement(By.id("from1")).sendKeys(prop.getProperty("source"));
		Thread.sleep(3000);
		List<WebElement> options=driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
		for(WebElement option: options) {
			if(option.getText().equalsIgnoreCase("Bengaluru, India")) {
				option.click();
				break;
			}
		}
		
		
		//To enter destination in autosuggestive dropdown:
		driver.findElement(By.id("to1")).sendKeys(prop.getProperty("destination"));
		Thread.sleep(3000);
		List<WebElement> options1=driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
		for(WebElement option: options1) {
			if(option.getText().equalsIgnoreCase("Mumbai, India")) {
				option.click();
				break;
			}
		}
		//while(driver.findElement(By.cssSelector("[class='ui-datepicker-month']")).getText().contains("September")) {
		//	while(driver.findElement(By.cssSelector("[class=' ui-datepicker-week-end '] [class='ui-state-default']")).getText().contains("12")
		//}
		
		
		//To select Calendar departure date:
		driver.findElement(By.xpath("//span[contains(@class,'depart-date')]")).click();
		List<WebElement> dates=driver.findElements(By.cssSelector("[class=' ui-datepicker-week-end '] [class='ui-state-default']"));
		int count=driver.findElements(By.cssSelector("[class=' ui-datepicker-week-end '] [class='ui-state-default']")).size();
		
		for(int i=0;i<count;i++)
		{
			String text=driver.findElements(By.cssSelector("[class=' ui-datepicker-week-end '] [class='ui-state-default']")).get(i).getText();
			System.out.println(text);
			if(text.equalsIgnoreCase("12"))
			{
				driver.findElements(By.cssSelector("[class=' ui-datepicker-week-end '] [class='ui-state-default']")).get(i).click();
				break;
			}
		}
		
		//To select Calendar return date:
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'return-date')]")));
		driver.findElement(By.xpath("//span[contains(@class,'return-date')]")).click();
		driver.findElement(By.cssSelector("[class='ui-state-default ui-state-active']")).click();
		
		
		//Click Search button
		l.getSearchButton().click();
		/*List<WebElement> returnDates=driver.findElements(By.cssSelector("[class=' ui-datepicker-week-end '] [class='ui-state-default']"));
		int countReturn=driver.findElements(By.cssSelector("[class=' ui-datepicker-week-end '] [class='ui-state-default']")).size();
		
		for(int j=0;j<countReturn;j++)
		{
			String text=driver.findElements(By.cssSelector("[class=' ui-datepicker-week-end '] [class='ui-state-default']")).get(j).getText();
			System.out.println(text);
			if(text.equalsIgnoreCase("30"))
			{
				driver.findElements(By.cssSelector("[class=' ui-datepicker-week-end '] [class='ui-state-default']")).get(j).click();
				break;
			}
		}*/
		
		
		
	//Since page opens in new window,switch to new window:
	Set<String> ids=driver.getWindowHandles();
	Iterator<String> it=ids.iterator();
	String parentId=it.next();
	String childId=it.next();
	driver.switchTo().window(childId);
	
	//Handle the window popup:
	System.out.println(driver.findElement(By.xpath("//span[contains(@class,'link fltactmdl-signup-no-thanks')]")).getText());
	driver.findElement(By.xpath("//span[contains(@class,'link fltactmdl-signup-no-thanks')]")).click();
	
	//to sort ticket price in ascending order
	driver.findElement(By.xpath("//span[@class='fltrt-sort'][contains(text(),'Price')]")).click();
	System.out.println("price sorted in ascending order");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	//select ticket on first result/select departure flight
	driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[3]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[1]/span[1]")).click();
	System.out.println("select ticket on first result");
	//driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	
	
	
	//DesiredCapabilities capabilities = new  DesiredCapabilities();
	//capabilities.setCapability("overlappingCheckDisabled", true);
	
	//wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[3]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[1]/span[1]")));
	
	//driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[3]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[1]/span[1]")).click();
	
	//to select return flight and checkout
	try {
		WebDriverWait wait1 = new WebDriverWait(driver,10);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='btnfare btn withsub']")));
	}
	catch(Exception e) {
		Actions actions = new Actions(driver);
		WebElement target = driver.findElement(By.xpath("//span[@class='fltrt-sort active']"));
		actions.moveToElement(target).perform();
	}
	driver.findElement(By.xpath("//div[@class='btnfare btn withsub']")).click();
	System.out.println("checked out");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	//To verify date in next page
	
	System.out.println(driver.findElement(By.xpath("//body[@id='body']/div[@class='page-row page-row-expanded']/div[@class='fill-space-container']/div[@class='flex-row-container']/div[@class='for-print-sibling']/div[@id='flightReviewTarget']/div[@class='MainContainer__MainContainerStyled-sc-1oaq8lp-0 joKfsx']/div[@class='MainContent__MainContentStyled-sc-1dsn5rw-0 ezxCrA']/div[@class='MainContent__FlexItem-sc-1dsn5rw-1 cnBqQZ']/div[@class='src__Box-sc-1sbtrzs-0 jRCDLh']/div[2]/div[1]/div[1]/div[1]/div[2]")).getText());
	Assert.assertEquals(driver.findElement(By.xpath("//body[@id='body']/div[@class='page-row page-row-expanded']/div[@class='fill-space-container']/div[@class='flex-row-container']/div[@class='for-print-sibling']/div[@id='flightReviewTarget']/div[@class='MainContainer__MainContainerStyled-sc-1oaq8lp-0 joKfsx']/div[@class='MainContent__MainContentStyled-sc-1dsn5rw-0 ezxCrA']/div[@class='MainContent__FlexItem-sc-1dsn5rw-1 cnBqQZ']/div[@class='src__Box-sc-1sbtrzs-0 jRCDLh']/div[2]/div[1]/div[1]/div[1]/div[2]")).getText(), "Sat - Sep 12th, 2020");
	 
	 System.out.println("validateDate test completed");

//to tk screenshot of final page
	 
		try {
			System.out.println("entered pass");
			getScreenshotPath("FinalPageScreenshot",driver);
			System.out.println("executed pass fn");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public void teardown()
	{
		//Exit the browser
		driver.quit();
		driver=null;
		
	}

	
	
	
}
