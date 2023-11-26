package parabank.tests.RequestLoan;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parabank.pages.FindTransactionsPage;
import parabank.pages.LoginPage;
import parabank.pages.OpenNewAccountPage;
import parabank.pages.RequestLoanPage;
import parabank.tests.BaseTest;

public class ValidRequestLoanTest extends BaseTest {
    @BeforeClass
    public void setup() {
        //Given
        Allure.step("Given: Użytkownik jest zalogowany");
        loginPage = new LoginPage(driver);
        openNewAccountPage = new OpenNewAccountPage(driver);
        loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());

    }

    @Test(description = "PB-35 - Poprawnie wypełniony formularz o udzielenie pożyczki")
    @Severity(SeverityLevel.BLOCKER)
    public void validRequestLoan() {
        //When
        Allure.step("When: Użytkownik składa poprawny wniosek o pożyczkę");
        requestLoanPage = new RequestLoanPage(driver);
        requestLoanPage.getIntoRequestLink();
        requestLoanPage.enterAmount("100");
        requestLoanPage.enterDownPayment("10");
        requestLoanPage.selectAccount();
        requestLoanPage.applyNow();
        //Then
        Allure.step("Then: System wyświetla potwierdzenie poprawnego wniosku o pożyczkę");
        Assert.assertEquals(pageElements.getSuccesfulRequestLoanMessage(), requestLoanPage.getRequestLoanMessage());
    }

}

