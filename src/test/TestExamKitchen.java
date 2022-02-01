package test;

import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import base.BaseExamKitchen;

public class TestExamKitchen extends BaseExamKitchen {

	@Test(enabled = false)
	public void reserveHappyPath() throws ParseException {
		String url = exl.getStringData("Reserve", 1, 0);  			// Radi uniformnosti podataka i lakšeg rada sva polja 
		String name = exl.getStringData("Reserve", 1, 1); 			// u testData.xlsx dokumentu su formatirana kao tekst,
		String email = exl.getStringData("Reserve", 1, 2);			// pa ih ovde po potrebi naknadno konvertujem u druge tipove.
		String phone = exl.getStringData("Reserve", 1, 3);
		String date = exl.getStringData("Reserve", 1, 4);
		String time = exl.getStringData("Reserve", 1, 5);
		String person = exl.getStringData("Reserve", 1, 6);
		String parking = exl.getStringData("Reserve", 1, 7);

		utility.pageSetup(url);

		reservePage.enterName(name);
		reservePage.enterEmail(email);
		reservePage.enterPhoneNumber(phone);
		reservePage.enterDate(date);
		reservePage.enterTime(time);
		reservePage.enterPersonNum(person);
		reservePage.toggleParkingSwitch();
		reservePage.clickSubmitFormButton();

		assertEquals(utility.getFromLocalStorage("name"), name);
		assertEquals(utility.getFromLocalStorage("email"), email);
		assertEquals(utility.getFromLocalStorage("phone"), phone.replace(" ", "").replace("+", ""));
		assertEquals(reservePage.dateOutputFormat(utility.getFromLocalStorage("date")), date);
		assertEquals(utility.getFromLocalStorage("time"), time);
		assertEquals(Long.parseLong(utility.getFromLocalStorage("person")), Integer.parseInt(person));
		assertEquals(utility.getFromLocalStorage("parking"), parking);

	}

	@Test(enabled = true)
	public void menuHappyPath() throws InterruptedException {
		String url = exl.getStringData("Menu", 1, 0);
		int orderNumber = Integer.parseInt(exl.getStringData("Menu", 1, 1));
		int totalPrice = 0;
		
		utility.pageSetup(url);
		
		utility.scroll(0, 450);
		utility.waitUntilClickable(menuPage.getPlusButtons().get(2));
		
		for (int index = 0; index <= orderNumber; index++) {
			if (index == orderNumber) {													// Kada dođem do poslednjeg indeksa njega ću ubaciti dva puta,
				menuPage.ClickPlusButton(index + 4);									// ukupan broj porudžbina će onda biti 6, a poslednja će biti duplirana.
				totalPrice += menuPage.getMenuItemPrice(index + 4);
				menuPage.ClickPlusButton(index + 4);
				totalPrice += menuPage.getMenuItemPrice(index + 4);
			} else {
				menuPage.ClickPlusButton(index);
				totalPrice += menuPage.getMenuItemPrice(index);
			}
		}
		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#listaItema>li"), orderNumber + 2)); // Čeka da se sve stavke iz korpe učitaju
		assertEquals(menuPage.getTotalPrice(), totalPrice);													   	// pre nego što proveri ukupnu cenu
	}

	@Test(enabled = false)
	public void questionaireHappyPath() throws InterruptedException {
		String url = exl.getStringData("Questionaire", 1, 0);
		String reason = exl.getStringData("Questionaire", 1, 1);
		List<String> order = new ArrayList<>();
		for (int i = 1; i < 3; i++) {
			order.add(exl.getStringData("Questionaire", i, 2));
		}
		String rating = exl.getStringData("Questionaire", 1, 3);
		String feedbackText = exl.getStringData("Questionaire", 1, 4);
		String interiorDesign = exl.getStringData("Questionaire", 1, 5);
		String staffRating = exl.getStringData("Questionaire", 1, 6);
		String comment = exl.getStringData("Questionaire", 1, 7);
		
		List<String> outputVrificationList = Arrays.asList(reason, rating, feedbackText,		// Ovde formatiram string za proveru izlaza da bi se poklapao
				order.get(0), order.get(1),comment, interiorDesign, staffRating); 				// sa redosledom koji očekujem da izvučem iz cookie-ja.						
		String outputVerificationString = String.join(",", outputVrificationList);							
		
		utility.pageSetup(url);

		questionairePage.selectReasonForVisit(reason);
		utility.scroll(0, 650);
		utility.waitUntilClickable(questionairePage.getStaffRatingButtons().get(0));
		for (int index = 0; index < order.size(); index++) {
			questionairePage.checkOrderCheckbox(order.get(index));
		}
		questionairePage.clickFeedbackOpenButton();
		questionairePage.clickRatingRadioButton(rating);
		questionairePage.enterTextToMenuWhishTextField(feedbackText);
		questionairePage.clickFeedbackSubmitButton();
		questionairePage.inputInRangeSelector(interiorDesign);
		questionairePage.clickStaffRatingButton(staffRating);
		utility.scroll(0, 650);
		utility.waitUntilClickable(questionairePage.getSubmitQuestionaireButton());
		questionairePage.inputToCommentTextField(comment);
		questionairePage.clickSubmitQuestionaireButton();

		assertEquals(utility.getCookieValue(""), outputVerificationString);
	}
}
