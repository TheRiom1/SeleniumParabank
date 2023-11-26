package parabank.tests.RequestLoan;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parabank.pages.LoginPage;
import parabank.pages.OpenNewAccountPage;
import parabank.pages.RequestLoanPage;
import parabank.tests.BaseTest;

public class InvalidRequestLoanTest extends BaseTest {
@BeforeClass
public void setup(){
    //Given
    Allure.step("Given: Użytkownik jest zalogowany");
    loginPage = new LoginPage(driver);
    openNewAccountPage = new OpenNewAccountPage(driver);
    loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());

}
    @Test(description = "PB-36 - Niepoprawnie udzielona pożyczka - niewystarczające środki na zaliczkę")
    @Severity(SeverityLevel.BLOCKER)
    public void requestLoanNotEnoughFundsForADownPayment() {
        //When
        Allure.step("When: Użytkownik składa wniosek o pożyczkę z niewystarczającymi środkami na zaliczkę");
        requestLoanPage = new RequestLoanPage(driver);
        requestLoanPage.getIntoRequestLink();
        requestLoanPage.enterAmount("1000000000");
        requestLoanPage.enterDownPayment("10000000");
        requestLoanPage.selectAccount();
        requestLoanPage.applyNow();
        //Then
        Allure.step("Then: System wyświetla komunikat o braku wystarczających środków na zaliczkę");
        requestLoanPage.secondErrorMessageShouldBe("You do not have sufficient funds for the given down payment.");
    }
    @Test(description = "PB-37 - Niepoprawnie udzielona pożyczka - niepoprawna wartość")
    @Severity(SeverityLevel.BLOCKER)
    public void requestLoanWrongValue() {
        //When
        Allure.step("When: Użytkownik składa wniosek o pożyczkę z niepoprawną wartością");
        requestLoanPage = new RequestLoanPage(driver);
        requestLoanPage.getIntoRequestLink();
        requestLoanPage.enterAmount("aa");
        requestLoanPage.enterDownPayment("aa");
        requestLoanPage.selectAccount();
        requestLoanPage.applyNow();
        //Then
        Allure.step("Then: System wyświetla komunikat o błędzie");
        requestLoanPage.errorMessageShouldBe("An internal error has occurred and has been logged.");
    }
}

