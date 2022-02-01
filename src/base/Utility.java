package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor jsExec;
	
	public Utility(WebDriver driver, WebDriverWait wait, JavascriptExecutor jsExec) {
		super();
		this.driver = driver;
		this.wait = wait;
		this.jsExec = jsExec;
	}
	
	public void pageSetup(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public void scroll(int horizontal, int vertical) throws InterruptedException {
		jsExec.executeScript("javascript:window.scrollBy(" + horizontal + "," + vertical + ")");
	}
	
	public void waitUntilClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public String getFromLocalStorage(String key) {
		return (String) jsExec.executeScript(String.format("return window.localStorage.getItem('%s');", key));
	}
	
	public String getCookieValue(String name) {
		return driver.manage().getCookieNamed(name).getValue();
	}
}
