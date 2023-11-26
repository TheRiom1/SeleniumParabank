package parabank.tests.UpdateContactInfo;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parabank.pages.LoginPage;
import parabank.pages.UpdateContactInfoPage;
import parabank.tests.BaseTest;

public class InvalidUpdateContactInfoTest extends BaseTest {
    @BeforeClass
    public void setup(){
        loginPage = new LoginPage(driver);
        loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());
    }
    @Test(description = "PB-31 - Niepoprawna aktualizacja informacji - pusty formularz")
    @Severity(SeverityLevel.BLOCKER)
    public void emptyAllFields() {
        //Given
        Allure.step("Given: Użytkownik jest zalogowany");
        loginPage = new LoginPage(driver);
        loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());
        //When
        Allure.step("When: Użytkownik próbuje zaktualizować informacje, pozostawiając formularz pusty");
        updateContactInfoPage = new UpdateContactInfoPage(driver);
        updateContactInfoPage.clearAllFields();
        //Then
        Allure.step("Then: System wyświetla komunikaty błędów dla pustych pól");
        updateContactInfoPage.compareErrors(updateContactInfoPage.getErrorMessages());
    }
    @Test(description = "PB-32 - Niepoprawna aktualizacja informacji - puste pole")
    @Severity(SeverityLevel.BLOCKER)
    public void emptyOneField() {
        //Given
        Allure.step("Given: Użytkownik jest zalogowany");
        loginPage = new LoginPage(driver);
        loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());
        //When
        Allure.step("When: Użytkownik próbuje zaktualizować informacje, pozostawiając jedno pole puste");
        updateContactInfoPage = new UpdateContactInfoPage(driver);
        updateContactInfoPage.clickUpdateContactInfoLink();
        updateContactInfoPage.waitForNextMove();
        updateContactInfoPage.clearFieldAndSendKeys(updateContactInfoPage.addressField, "");
        //Then
        Allure.step("Then: System wyświetla komunikat błędu dotyczący wymaganego pola");
        updateContactInfoPage.errorMessageShouldBe("Address is required.");
    }
}