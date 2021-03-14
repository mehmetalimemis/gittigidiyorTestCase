package gittigidiyorCase;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class gittigidiyorTestCase {
	@Test
	public void f() {
		
//		Define Chrome Driver
		System.setProperty("webdriver.chrome.driver", "C:\\chrome\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		Manage time outs
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//		Open the link and maximize the window
		driver.manage().window().maximize();
		driver.get("https://www.gittigidiyor.com/");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.gittigidiyor.com/");
		
//		Go to login page
		driver.navigate().to("https://www.gittigidiyor.com/uye-girisi");
		
// 		Login to gittigidiyor
		driver.findElement(By.xpath("//*[@id=\"L-UserNameField\"]")).sendKeys("mehmetalimemis93@gmail.com");
		driver.findElement(By.xpath("//*[@id='L-PasswordField']")).sendKeys("messeto1447");
		driver.findElement(By.xpath("//*[@id=\"gg-login-enter\"]")).click();
		
// 		Check the login page
		WebElement userCheck = driver.findElement(By.xpath("/html//header[@id='main-header']/div[3]/div/div/div/div[3]/div/div[1]/div[@title='Hesabım']//span[.='mehmetalimemis9680']"));
		if ( userCheck.getText().contains("mehmetalimemis9680")) {
			System.out.println("Login islemi basariyla gerceklesti");
			System.out.println("-----------------------------");

		} else {
			System.out.println("Login islemi basarisiz oldu.");
		}
		
//		Search "Bilgisayar" 
		driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input")).sendKeys("Bilgisayar");
		driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button/span")).click();
		
//		Go to the second page 
		((JavascriptExecutor) driver).executeScript("scroll(0,9500)");
		driver.findElement(By.xpath("//*[@id=\"best-match-right\"]/div[5]/ul/li[7]/a")).click();
		
//		Check second page
		if (driver.getCurrentUrl().contains("sf=2")) {
			System.out.println("2. sayfaya gecis yaptiniz");
			System.out.println("-----------------------------");

		} else {
			System.out.println("2. sayfaya gidemediniz");
		}
		
//		Go to the item page
		driver.findElement(By.cssSelector("li:nth-of-type(1)  .cell-border-css > .product-info-con")).click();
		WebElement productPriceInDetails = driver.findElement(By.xpath("/html//div[@id='sp-price-lowPrice']"));
		String listPrice = productPriceInDetails.getText();
		
//		Add to basket 
		((JavascriptExecutor) driver).executeScript("scroll(0,400)");
		driver.findElement(By.xpath("/html//button[@id='add-to-basket']")).click();
		
// 		Go to the "Sepetim" page
		driver.findElement(By.xpath("/html//div[@id='header_wrapper']/div/div/div//div[@class='gg-d-12 pl0']/a[@href='https://www.gittigidiyor.com/sepetim']")).click();
		//driver.navigate().to("https://www.gittigidiyor.com/sepetim");
		
//		Check the product price
		WebElement productPriceInBasket = driver.findElement(By.xpath(".//*[@id='cart-price-container']/div[3]/p"));
		String basketPrice = productPriceInBasket.getText();
		Assert.assertEquals(listPrice, basketPrice);
		System.out.println("Urunun listelenen fiyati ile sepetteki fiyati esittir ve " + basketPrice + "'dir.");
		System.out.println("-----------------------------");
		
//		Product Quantity
		WebElement productQuantity = driver.findElement(By.xpath("//body/div[@id='main-content']/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[6]/div[2]/div[2]/div[4]/div[1]/div[2]/select[1]"));
		productQuantity.click();
		productQuantity.sendKeys("2");
		productQuantity.sendKeys(Keys.ENTER);
		
// 		Check the product count
		WebElement productCount = driver.findElement(By.xpath("//div[contains(text(),'Ürün Toplamı (2 Adet)')]"));
		System.out.println(productCount.getText());
		System.out.println("-----------------------------");
		
		if (productCount.getText().contains("2 Adet")) {
			System.out.println("Sepetteki urun adedi 2'dir.");
			System.out.println("-----------------------------");

		} else {
			System.out.println("Sepetteki urun adedi 2 degildir.");
		}
		
//		Delete the item
		driver.findElement(By.xpath("/html//div[@id='cart-items-container']/div[@class='products-container']/div/div//div[@class='row']/a[@title='Sil']/i[@class='gg-icon gg-icon-bin-medium']")).click();
		
//		Close the window 
		driver.quit();
		
	}
}
