package pageObjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//rahulonlinetutor@gmail.com
public class LandingPage {

	
	public WebDriver driver;
	
	//private By signin=By.cssSelector("a[href*='sign_in']");
	//private By title=By.cssSelector(".text-center>h2");
	//private By NavBar=By.cssSelector(".nav.navbar-nav.navbar-right>li>a");
	//private By header=By.cssSelector("div[class*='video-banner'] h3");
	
	//private By roundTrip=By.xpath("//input[@id='tripTypeTR']");
	private By roundTrip=By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/form[1]/h2[1]/span[1]/div[1]/label[1]");
	private By destination=By.xpath("//input[@id='to1']");
	private By search=By.xpath("//button[contains(@class,'btn large block')]");
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		
	}




	/*public LoginPage getLogin()
	{
		 driver.findElement(signin).click();
		 LoginPage lp=new LoginPage(driver);
		 return lp;
	}
	public WebElement getNavigationBar()
	{
		return driver.findElement(NavBar);
	}
	public WebElement getTitle()
	{
		return driver.findElement(title);
	}
	public WebElement getHeader()
	{
		return driver.findElement(header);
	}*/
	
	public WebElement getTrip()
	{
		return driver.findElement(roundTrip);
	}
	
	public WebElement getDestination()
	{
		return driver.findElement(destination);
	}
	public WebElement getSearchButton()
	{
		return driver.findElement(search);
	}
}
