package parabank.tests.LoginAndLogout;

import io.qameta.allure.Allure;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parabank.pages.LoginPage;
import parabank.tests.BaseTest;
public class InvalidLoginTest extends BaseTest {
    @Test(description = "PB-9 - Niepoprawne logowanie - nieprawidłowe hasło")
    public void loginWithIncorrectPasswordInput() {
        //Given
        Allure.step("Given: Użytkownik znajduje się na stronie logowania");
        loginPage = new LoginPage(driver);

        //When
        Allure.step("When: Po wprowadzeniu nieprawidłowych danych logowania, użytkownik klika przycisk login");
        loginPage.loginUser(standardUser.getUsername(), "wrongPassword");

        //Then
        Allure.step("Then: Pojawia się komunikat błędu. Użytkownik nie zostanie zalogowany i pozostanie na stronie logowania.");
        loginPage.secondErrorMessageShouldBe();
    }
    @Test(description = "PB-10 - Niepoprawne logowanie - nieprawidłowy login")
    public void loginWithIncorrectLoginInput() {
        //When
        Allure.step("When: Po wprowadzeniu nieprawidłowych danych logowania, użytkownik klika przycisk login");
        loginPage = new LoginPage(driver);
        loginPage.loginUser("wrongLogin", standardUser.getPassword());

        //Then
        Allure.step("Then: Pojawia się komunikat błędu. Użytkownik nie zostanie zalogowany i pozostanie na stronie logowania.");
        loginPage.secondErrorMessageShouldBe();
    }

    @Test(description = "PB-11 - Niepoprawne logowanie - brak nazwy użytkownika")
    public void loginWithoutUsernameShouldDisplayError() {

        //When
        Allure.step("When: Po wprowadzeniu nieprawidłowych danych logowania, użytkownik klika przycisk login");
        loginPage = new LoginPage(driver);
        loginPage.loginUser("", standardUser.getPassword());

        //Then
        Allure.step("Then: Pojawia się komunikat błędu. Użytkownik nie zostanie zalogowany i pozostanie na stronie logowania.");
        loginPage.errorMessageShouldBe();

    }
    @Test(description = "PB-12 - Niepoprawne logowanie - brak hasła")
    public void loginWithoutPasswordShouldDisplayError() {
        //When
        Allure.step("When: Po wprowadzeniu nieprawidłowych danych logowania, użytkownik klika przycisk login");
        loginPage = new LoginPage(driver);
        loginPage.loginUser(standardUser.getUsername(), "");

        //Then
        Allure.step("Then: Pojawia się komunikat błędu. Użytkownik nie zostanie zalogowany i pozostanie na stronie logowania.");
        loginPage.errorMessageShouldBe();

    }
}
