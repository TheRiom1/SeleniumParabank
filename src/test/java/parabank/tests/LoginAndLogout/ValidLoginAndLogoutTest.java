package parabank.tests.LoginAndLogout;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import parabank.pages.LoginPage;
import parabank.tests.BaseTest;
import parabank.utils.QaseIntegration;

public class ValidLoginAndLogoutTest extends BaseTest {
    @Test(description = "PB-1 - Poprawne logowanie")
    @Severity(SeverityLevel.BLOCKER)
    public void validLogin() {
        // Given
        Allure.step("Given: Użytkownik jest zerejestrowany i znajduje się na stronie logowania https://parabank.parasoft.com/parabank/index.html.");
        loginPage = new LoginPage(driver);

        //When
        Allure.step("When: // Jeżeli użytkownik zarejestrowany wprowadzi swoje dane i kliknie przycisk \"Zaloguj\"");
        loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());

        //Then
        Allure.step("Then: Zostanie przeniesiony na stronę dashboardu");
        Assert.assertEquals(pageElements.getAccountOpenedTitle(), openNewAccountPage.getPageTitle());
    }
    @Test(description = "PB-3 - Poprawne wylogowanie", dependsOnMethods = "validLogin")
    @Severity(SeverityLevel.BLOCKER)
    public void validLogout() throws Exception {
        //Given
        Allure.step("Given: Użytkownik jest zalogowany");

        //When
        Allure.step("When: Kiedy użytkownik kliknie przycisk logout");
        loginPage.logOutUser();

        //Then
        Allure.step("Then: Zostanie przeniesiony na strone logowania.");
        loginPage.loginPanelShouldBeVisible();
    }
}
