package StepDefs;

import com.qa.util.ConfigReader;
import com.qa.util.Constants;
import io.cucumber.java.en.And;
import org.junit.Assert;

import com.pages.SignupPage;
import com.qa.factory.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignupSteps {

	private static String thankyouMessage;
	private SignupPage signupPage = new SignupPage(DriverFactory.getDriver());
	ConfigReader configReader=new ConfigReader();

	@Given("User is on the login page of spec")
	public void user_is_on_the_login_page_of_spec() throws InterruptedException {
	    signupPage.homePageIsDisplayed();
	}

	@Given("User clicks on Sign up")
	public void user_clicks_on_sign_up() {
		signupPage.userClicksSignUp();
	}

	@When("user enters valid {string} {string} {string} {string} {string}")
	public void user_enters_valid(String email, String firstName, String lastName, String password, String confirmPws) throws InterruptedException {

		signupPage.entersEmailId(email);
		signupPage.enterName(firstName, lastName);
		signupPage.enterPassword(password, confirmPws);
	}

	@Then("User clicks on Continue button")
	public void user_clicks_on_continue_button() {
		signupPage.clickContinueButton();
	}

	@And("User enters {string} and {string}")
	public void user_enters_company_details(String name, String address) throws InterruptedException {
		signupPage.enterCompanyName(name, address);
	}

	@Then("User enters {string} {string} {string} {string}")
	public void user_enters(String country, String state, String city, String zip) throws Exception {
		signupPage.enterCountry(country);
		signupPage.enterState(state);
		signupPage.enterCity(city);
		signupPage.enterZipcode(zip);
	}

	@Then("User selects Organization type")
	public void user_selects_organization_type() throws InterruptedException {
		signupPage.userSelectsOrganizationType();
	}

	@Then("User clicks on Next button")
	public void user_clicks_on_next_button() {
		signupPage.clickNextButton();
	}
	@Then("User selects the role")
	public void user_selects_the_role() throws InterruptedException {
		signupPage.selectRole();
	}
	@Then("User clicks on Sign up button")
	public void user_clicks_on_sign_up_button() throws InterruptedException {
		signupPage.clicksFinalSignup();
	}

	@And("User validates that Signup is successful")
	public void user_validate_signup_successful() throws InterruptedException {

		thankyouMessage = signupPage.validateSuccessSignup();
		System.out.println("Success Message is: " +thankyouMessage);
		Assert.assertTrue(thankyouMessage.contains(Constants.success));
	}



}
