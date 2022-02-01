package page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservePage {
	
	private WebDriver driver;
	public ReservePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getNameField() {
		return driver.findElement(By.id("name"));
	}

	public void enterName(String name) {
		getNameField().clear();					// Osigurava da je polje prazno
		getNameField().sendKeys(name);			// pre unosa
	}

	public WebElement getEmailField() {
		return driver.findElement(By.id("email"));
	}

	public void enterEmail(String email) {
		getEmailField().clear();
		getEmailField().sendKeys(email);
	}

	public WebElement getPhoneField() {
		return driver.findElement(By.id("phone"));
	}

	public void enterPhoneNumber(String phone) {
		getPhoneField().sendKeys(phone);
	}

	public WebElement getDateField() {						
		return driver.findElement(By.id("date"));			
	}

	public void enterDate(String date) {					// Vrednost za date, time i person polja nisam uspeo da 
		getDateField().sendKeys(date);						// unesem iznos preko picker-a pa sam ih samo poslao direktno
	}														// iako sam svestan da nije najbolja praksa za testiranje.

	public String dateOutputFormat(String date) throws ParseException {
		SimpleDateFormat dt0 = new SimpleDateFormat("yyyy-mm-dd");
		Date initial = dt0.parse(date);
		SimpleDateFormat dt = new SimpleDateFormat("mm/dd/yyyy");
		return dt.format(initial);
	}

	public WebElement getTimeField() {
		return driver.findElement(By.id("time"));
	}

	public void enterTime(String time) {
		getTimeField().sendKeys(time);
	}

	public WebElement getPersonField() {
		return driver.findElement(By.id("persons"));
	}

	public void enterPersonNum(String person) {
		getPersonField().sendKeys(person);
	}

	public WebElement getParkingToggle() {
		return driver.findElement(By.className("switch"));
	}

	public void toggleParkingSwitch() {
		getParkingToggle().click();
	}

	public WebElement getSubmitButton() {
		return driver.findElement(By.id("submitForm"));
	}

	public void clickSubmitFormButton() {
		getSubmitButton().click();
	}

}
