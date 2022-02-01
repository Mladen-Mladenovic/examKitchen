package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage {

	private WebDriver driver;
	private WebDriverWait wait;
	public MenuPage(WebDriver driver, WebDriverWait wait) {
		super();
		this.driver = driver;
		this.wait = wait;
	}

	public List<WebElement> getPlusButtons() {
		return driver.findElements((By.className("btnPlus")));
	}

	public void ClickPlusButton(int index) {
		getPlusButtons().get(index).click();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath(getCartItemNameLocator(getMenuItemName(index))))); // Čeka da se u korpi pojavi ime poslednje porudžbine, 
	}																													// da se ne bi dešavalo da doda još jednu
																														// pre nego što se učitala prethodna.
	public String getCartItemNameLocator(String menuItemName) {
		return "//li[text()='" + menuItemName + " ']"; 						// Uzima ime iz menija i koristi ga 
	}																		// da bi našao istu porudžbinu u korpi.

	public List<WebElement> getListOfItemNames() {
		return driver.findElements(By.cssSelector("div.one-half h3"));
	}

	public String getMenuItemName(int index) {
		if (0 <= index) { 											// Ostavlja prostor za slučaj da se meni proširi, 
			return getListOfItemNames().get(index).getText();		// ali osigurava da indeks ne ode u negativnu vrednost
		} else {
			throw new IllegalArgumentException("Error: wrong argument for getMenuItemName method.");
		}
	}

	public List<WebElement> getListOfItemPrices() {
		return driver.findElements(By.className("price"));
	}

	public int getMenuItemPrice(int index) {
		if (index >= 0) {
			return Integer.parseInt(getListOfItemPrices().get(index).getText().replace("$", "")); // Izbacujem znak $ da bih mogao čistu cenu da prebacim u int.
		} else {
			throw new IllegalArgumentException("Error: wrong argument for getMenuItemPrice method.");
		}
	}

	public int getTotalPrice() {
		return Integer.parseInt(driver.findElement(By.id("ukupno")).getText().replace("$", ""));
	}

}
