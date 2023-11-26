package parabank.tests.FindTransactions;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parabank.pages.FindTransactionsPage;
import parabank.pages.LoginPage;
import parabank.pages.TranferFundsPage;
import parabank.tests.BaseTest;

public class InvalidFindingTransactionsTest extends BaseTest {
    @BeforeClass
    public void setup(){
        //Given
        Allure.step("Given: Użytkownik jest zalogowany");
        loginPage = new LoginPage(driver);
        loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());
    }
    @Test(description = "PB-22 - Niepoprawne znalezienie transakcji - nieprawidłowa wartość")
    @Severity(SeverityLevel.BLOCKER)
    public void invalidFindingTransaction() {
        //When
        Allure.step("When: Użytkownik szuka transakcji wpisując nieprawidłową wartość");
        findTransactionsPage = new FindTransactionsPage(driver);
        findTransactionsPage.getIntoFindTransactionClick();
        findTransactionsPage.waitForNextMove();
        findTransactionsPage.selectAccount();
        findTransactionsPage.enterId("asdfg");
        findTransactionsPage.findTransactionsByIdClick();

        //Then
        Allure.step("Then: System wyświetla komunikat o błędzie");
        findTransactionsPage.errorMessageShouldBe("An internal error has occurred and has been logged.");
    }
}
