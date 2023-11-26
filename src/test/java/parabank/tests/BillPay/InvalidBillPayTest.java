package parabank.tests.BillPay;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parabank.pages.BillPayPage;
import parabank.pages.LoginPage;
import parabank.tests.BaseTest;

public class InvalidBillPayTest extends BaseTest {
    @BeforeClass
    public void setup() {
        //Given
        Allure.step("Użytkownik jest zalogowany do systemu.");
        loginPage = new LoginPage(driver);
        loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());
    }
    @Test(description = "PB-14 - Niepoprawne opłacenie rachunków - pusty formularz")
    @Severity(SeverityLevel.BLOCKER)
    public void emptyFormTest() {
        //Given
        Allure.step("Given: Użytkownik jest zalogowany");

        //When
        Allure.step("When: Użytkownik pozostawia formularz opłacenia rachunku pusty");
        billPayPage = new BillPayPage(driver);
        billPayPage.clickBillPayLink();
        billPayPage.clickBillPayBtn();
        //Then
        Allure.step("Then: System wyświetla komunikaty błędów dla pustego formularza");
        billPayPage.compareErrors(billPayPage.getErrorMessages());
    }
    @Test(description = "PB-15 - Niepoprawne opłacenie rachunków - brak kodu pocztowego")
    @Severity(SeverityLevel.BLOCKER)
    public void emptyOneFieldTest() {
        // Given
        Allure.step("Given: Użytkownik jest zalogowany");

        //When
        Allure.step("When: Użytkownik zostawia pole kodu pocztowego puste");
        billPayPage = new BillPayPage(driver);
        String accNumber = "100032";
        billPayPage.sendBillPayForm("someName", standardUser.getAddress(), standardUser.getCity(), standardUser.getState(), "", standardUser.getPhone(), accNumber, accNumber,"1000");
        //Then
        Allure.step("Then: System wyświetla komunikat błędu dotyczący braku kodu pocztowego");
        billPayPage.errorMessageShouldBe(pageElements.getEmptyFieldError("Zip Code"));
    }
    @Test(description = "PB-16 - Niepoprawne opłacenie rachunków - niepoprawna wartość w polu z numerem konta")
    @Severity(SeverityLevel.BLOCKER)
    public void invalidAccountData() {
        billPayPage = new BillPayPage(driver);
        //When
        Allure.step("When: Użytkownik wysyła formularz opłacenia rachunku, wprowadzając niepoprawne dane, takie jak nieprawidłowa wartość w polu numeru konta.");
        String accNumber = "a";
        billPayPage.sendBillPayForm("someName", standardUser.getAddress(), standardUser.getCity(), standardUser.getState(), standardUser.getZipCode(), standardUser.getPhone(), accNumber, accNumber,"1000");
        //Then
        Allure.step("Then: System wyświetla komunikat błędu informujący użytkownika o konieczności wprowadzenia poprawnej wartości w polu numeru konta.");
        billPayPage.errorMessageShouldBe("Please enter a valid number.");
    }
    @Test(description = "PB-17 - Niepoprawne opłacenie rachunków - różne wartości w polach account i verify account")
    @Severity(SeverityLevel.BLOCKER)
    public void differentAccountAndVerifyAccountData() {
        billPayPage = new BillPayPage(driver);
        //When
        Allure.step("When: Użytkownik wysyła formularz opłacenia rachunku, wprowadzając różne wartości w polach account i verify account.");
        billPayPage.sendBillPayForm("someName", standardUser.getAddress(), standardUser.getCity(), standardUser.getState(), standardUser.getZipCode(), standardUser.getPhone(), "1", "2","1000");
        //Then
        Allure.step("Then: System wyświetla komunikat błędu informujący użytkownika, że numer konta i numer weryfikacyjny nie są zgodne.");
        billPayPage.errorMessageShouldBe("The account numbers do not match.");
    }
}
