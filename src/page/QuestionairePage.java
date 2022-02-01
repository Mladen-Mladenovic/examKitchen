package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuestionairePage {

	private WebDriver driver;
	private WebDriverWait wait;
	public QuestionairePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public WebElement getReasonForVisit(String reason) {
		return driver.findElement(By.cssSelector("input[value = '" + reason + "']"));
	}

	public void selectReasonForVisit(String reason) {
		wait.until(ExpectedConditions.elementToBeClickable(getReasonForVisit(reason)));
		getReasonForVisit(reason).click();
	}

	public WebElement getOrderCheckboxField() {
		return driver.findElement(By.id("exampleModalLabel"));
	}

	public WebElement getOrderCheckboxList(String value) {
		driver.switchTo().frame("first");
		return driver.findElement(By.cssSelector("#fqForm>div.form-check>input[value='" + value + "']"));
	}

	public void checkOrderCheckbox(String value) {
		getOrderCheckboxList(value).click();
		driver.switchTo().defaultContent();
	}

	public WebElement getFeedbackOpenButton() {
		return driver.findElement(By.cssSelector("button[data-target = '#exampleModal']"));
	}

	public void clickFeedbackOpenButton() {
		getFeedbackOpenButton().click();
	}

	public WebElement getRatingRadiobutton(String rating) {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("radio4Example5")));
		switch (rating) { 
		case "vb":
			return driver.findElement(By.id("radio4Example5"));
		case "b":
			return driver.findElement(By.id("radio4Example4"));
		case "m":
			return driver.findElement(By.id("radio4Example3"));
		case "g":
			return driver.findElement(By.id("radio4Example2"));
		case "vg":
			return driver.findElement(By.id("radio4Example1"));
		default:
			throw new IllegalArgumentException("Error: wrong argument for getRatingRadiobutton method.");

		}
	}

	public void clickRatingRadioButton(String rating) {
		getRatingRadiobutton(rating).click();
	}

	public WebElement getMenuWhishTextField() {
		return driver.findElement(By.id("hwofTA"));
	}

	public void enterTextToMenuWhishTextField(String text) {
		getMenuWhishTextField().sendKeys(text);
	}

	public WebElement getFeedbackSubmitButton() {
		return driver.findElement(By.cssSelector("button[data-dismiss='modal']"));
	}

	public void clickFeedbackSubmitButton() {
		getFeedbackSubmitButton().click();
	}

	public WebElement getRangeSelector() {
		return driver.findElement(By.id("rng"));
	}

	public void inputInRangeSelector(String range) { 				// Odabrao sam da unosim strelice sa tastature pre nego broj direktno 
		int intRange = Integer.parseInt(range);					    // jer mislim da vernije simulira aktivnost korisnika.
		driver.switchTo().frame("third");
		getRangeSelector().click();
		if (intRange < 0) {
			throw new IllegalArgumentException("Error: wrong argument for inputInRangeSelector method.\n Negative numbers are not accepted.");
		}
		for (int i = 0; i < 3; i++) {								// Slajder se prvo vraća na početak, tj. na minimalnu vrednost.
			getRangeSelector().sendKeys(Keys.ARROW_LEFT);
		}
		for (int i = 0; i < intRange; i++) {
			getRangeSelector().sendKeys(Keys.ARROW_RIGHT);

		}
		driver.switchTo().defaultContent();
	}

	public List<WebElement> getStaffRatingButtons() {
		return driver.findElements(By.cssSelector("div[aria-label = 'First group']>button"));
	}

	public void clickStaffRatingButton(String index) {
		int intIndex = Integer.parseInt(index);
		getStaffRatingButtons().get(intIndex).click();
	}

	public WebElement getCommentTextField() {
		driver.switchTo().frame("second");
		return driver.findElement(By.id("frta"));
	}

	public void inputToCommentTextField(String text) {
		getCommentTextField().sendKeys(text);
		driver.switchTo().defaultContent();
	}

	public WebElement getSubmitQuestionaireButton() {
		return driver.findElement(By.id("submitQuestionaire"));
	}

	public void clickSubmitQuestionaireButton() {
		getSubmitQuestionaireButton().click();
	}

}
