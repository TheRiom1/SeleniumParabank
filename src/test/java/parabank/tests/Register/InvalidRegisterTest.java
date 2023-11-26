package parabank.tests.Register;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import parabank.pages.RegisterPage;
import parabank.tests.BaseTest;

public class InvalidRegisterTest extends BaseTest {
    @Test(description = "PB-5 - Niepoprawna rejestracja - pusty formularz")
    public void registerEmptyFormTest() {
        //Given
        Allure.step("Given: Użytkownik znajduje się na stronie rejestracji");
        //When
        Allure.step("When: Użytkownik nie wprowadza danych i klika przycisk register");
        registerPage = new RegisterPage(driver);
        registerPage.clickRegisterLink();
        registerPage.clickRegisterBtn();
        //Then
        Allure.step("Then: Pojawi się komunikat o błędzie");
        registerPage.compareErrors(registerPage.getErrorMessages());
    }
    @Test(description = "PB-6 - Niepoprawna rejestracja - brak podanej wartości")
    public void registerWithEmptyLastNameInput () {
        //Given
        Allure.step("Given: Użytkownik znajduje się na stronie rejestracji");
        //When
        Allure.step("When: Użytkownik nie wprowadzi wszystkich danych i klika przycisk register");
        registerPage = new RegisterPage(driver);
        registerPage.registerUser(standardUser.getFirstName(), "", standardUser.getAddress(), standardUser.getCity(), standardUser.getState(), standardUser.getZipCode(), standardUser.getPhone(), standardUser.getSsn(), standardUser.getUsername(), standardUser.getPassword(), standardUser.getPassword());

        //Then
        Allure.step("Then: Pojawi się komunikat o błędzie");
        registerPage.errorMessageShouldBe("Last name is required.");
    }
    @Test(description = "PB-7 - Niepoprawna rejestracja - istniejący username")
    public void registerWithExistingUsernameInput () {
        //Given
        Allure.step("Given: Użytkownik znajduje się na stronie rejestracji");

        //When
        Allure.step("When: Użytkownik wprowadza dane już użyte w rejestracji");
        registerPage = new RegisterPage(driver);
        registerPage.registerUser(standardUser.getFirstName(), standardUser.getLastName(), standardUser.getAddress(), standardUser.getCity(), standardUser.getState(), standardUser.getZipCode(), standardUser.getPhone(), standardUser.getSsn(), standardUser.getUsername(), standardUser.getPassword(), standardUser.getPassword());

        //Then
        Allure.step("Then: Pojawi się komunikat o błędzie");
        registerPage.secondErrorMessageShouldBe("This username already exists.");
    }
    @Test(description = "PB-8 - Niepoprawna rejestracja - różne hasła")
    public void registerWithDifferentPasswords () {
        //Given
        Allure.step("Given: Użytkownik znajduje się na stronie rejestracji");

        //When
        Allure.step("When: Użytkownik wprowadza dwie różne wartości dla hasła");
        registerPage = new RegisterPage(driver);
        registerPage.registerUser(standardUser.getFirstName(), standardUser.getLastName(), standardUser.getAddress(), standardUser.getCity(), standardUser.getState(), standardUser.getZipCode(), standardUser.getPhone(), standardUser.getSsn(), standardUser.getUsername(), standardUser.getPassword(), "differentPassword");
        //Then
        Allure.step("Then: Pojawi się komunikat o błędzie");
        registerPage.thirdErrorMessageShouldBe("Passwords did not match.");
    }
}