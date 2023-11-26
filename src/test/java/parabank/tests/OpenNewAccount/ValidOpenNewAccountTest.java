package parabank.tests.OpenNewAccount;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parabank.pages.LoginPage;
import parabank.pages.OpenNewAccountPage;
import parabank.tests.BaseTest;
public class ValidOpenNewAccountTest extends BaseTest {
    @BeforeClass
    public void setup(){
        //Given
        Allure.step("Given: Użytkownik jest zalogowany");
        loginPage = new LoginPage(driver);
        loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());
    }
    @Test(description = "PB-26 - Poprawne otwarcie nowego konta")
    @Severity(SeverityLevel.BLOCKER)
    public void validOpening(){
        //When
        Allure.step("When: Użytkownik otwiera nowe konto");
        openNewAccountPage = new OpenNewAccountPage(driver);
        openNewAccountPage.openNew(1);
        openNewAccountPage.waitForNextMove();

        //Then
        Allure.step("Then: System wyświetla potwierdzenie otwarcia nowego konta");
        Assert.assertEquals(pageElements.getAccountOpenedTitle(), openNewAccountPage.getPageTitle());
    }
}