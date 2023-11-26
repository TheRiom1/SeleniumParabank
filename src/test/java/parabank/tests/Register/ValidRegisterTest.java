package parabank.tests.Register;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import parabank.pages.RegisterPage;
import parabank.tests.BaseTest;

public class ValidRegisterTest extends BaseTest {
    @Test(description = "PB-4 - Poprawna rejestracja")
    @Severity(SeverityLevel.BLOCKER)
    public void validRegister() {
        //Given
        Allure.step("Given: Użytkownik znajduje się na stronie rejestracji");

        //When
        Allure.step("When: Po wprowadzeniu prawidłowych danych rejestracyjnych, użytkownik klika przycisk register");
        registerPage = new RegisterPage(driver);
        String pageTitle = registerPage.registerAsValidUser(standardUser.getFirstName(), standardUser.getLastName(), standardUser.getAddress(), standardUser.getCity(), standardUser.getState(), standardUser.getZipCode(), standardUser.getPhone(), standardUser.getSsn(), standardUser.getUsername(), standardUser.getPassword(), standardUser.getPassword()).getPageTitle();

        //Then
        Allure.step("Then: Użytkownik zostaje zarejestrowany i może się zalogować");
        Assert.assertEquals(pageElements.getRegisterAccountTitle(standardUser.getUsername()), pageTitle);
    }
}
