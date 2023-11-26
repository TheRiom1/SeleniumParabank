package parabank.tests.UpdateContactInfo;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import parabank.pages.LoginPage;
import parabank.pages.RegisterPage;
import parabank.pages.UpdateContactInfoPage;
import parabank.tests.BaseTest;

public class ValidUpdateContactInfoTest extends BaseTest {
    @Test(description = "PB-30 - Poprawna aktualizacja informacji")
    @Severity(SeverityLevel.BLOCKER)
    public void validUpdateContactInfo() {
        //Given
        Allure.step("Given: Użytkownik jest zalogowany");
        loginPage = new LoginPage(driver);
        loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());
        //When
        Allure.step("When: Użytkownik dokonuje poprawnej aktualizacji informacji");
        updateContactInfoPage = new UpdateContactInfoPage(driver);
        updateContactInfoPage.changeAllData("change");
        updateContactInfoPage.waitForNextMove();
        String pageTitle = updateContactInfoPage.getPageTitle();
        //Then
        Allure.step("Then: System wyświetla potwierdzenie poprawnej aktualizacji informacji");
        Assert.assertEquals(pageElements.getUpdateProfileInfoTitle(), pageTitle);
    }

}
