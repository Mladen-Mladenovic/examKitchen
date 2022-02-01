package base;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import page.MenuPage;
import page.QuestionairePage;
import page.ReservePage;

public class BaseExamKitchen {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor jsExec;

	public ExcelReader exl;
	public Utility utility;
	public ReservePage reservePage;
	public MenuPage menuPage;
	public QuestionairePage questionairePage;

	@BeforeClass
	public void setup() throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		exl = new ExcelReader("docs/testData.xlsx");
		utility = new Utility(driver, wait, jsExec);

		reservePage = new ReservePage(driver);
		menuPage = new MenuPage(driver, wait);
		questionairePage = new QuestionairePage(driver, wait);
	}

	@AfterClass
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

}
