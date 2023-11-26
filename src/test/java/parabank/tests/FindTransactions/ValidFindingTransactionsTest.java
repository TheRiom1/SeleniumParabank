package parabank.tests.FindTransactions;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parabank.pages.FindTransactionsPage;
import parabank.pages.LoginPage;
import parabank.tests.BaseTest;

public class ValidFindingTransactionsTest extends BaseTest {
@BeforeClass
public void setup(){
    //Given
    Allure.step("Given: Użytkownik jest zalogowany");
    loginPage = new LoginPage(driver);
    loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());
}
    @Test(description = "PB-18 - Poprawne znalezienie transakcji po numerze ID")
    @Severity(SeverityLevel.BLOCKER)
    public void validFindingById() {
        //When
        Allure.step("When: Użytkownik szuka transakcji po numerze ID");
        findTransactionsPage = new FindTransactionsPage(driver);
        findTransactionsPage.getIntoFindTransactionClick();
        findTransactionsPage.waitForNextMove();
        findTransactionsPage.selectAccount();
        findTransactionsPage.enterId("14476");
        findTransactionsPage.findTransactionsByIdClick();
        findTransactionsPage.waitForNextMove();
        //Then
        Allure.step("Then: System wyświetla wyniki wyszukiwania");
        Assert.assertEquals(pageElements.findTransactionsResultTitle(), findTransactionsPage.getPageTitle());
    }
    @Test(description = "PB-19 - Poprawne znalezienie transakcji po dniu wykonania transakcji")
    @Severity(SeverityLevel.BLOCKER)
    public void validFindingByDate() {
        //When
        Allure.step("When: Użytkownik szuka transakcji po dacie wykonania");
        findTransactionsPage = new FindTransactionsPage(driver);
        findTransactionsPage.getIntoFindTransactionClick();
        findTransactionsPage.waitForNextMove();
        findTransactionsPage.selectAccount();
        findTransactionsPage.enterDate("10-10-2023");
        findTransactionsPage.findTransactionsByDateClick();
        findTransactionsPage.waitForNextMove();
        //Then
        Allure.step("Then: System wyświetla wyniki wyszukiwania");
        Assert.assertEquals(pageElements.findTransactionsResultTitle(), findTransactionsPage.getPageTitle());
    }
    @Test(description = "PB-20 - Poprawne znalezienie transakcji po zakresie dat wykonania transakcji")
    @Severity(SeverityLevel.BLOCKER)
    public void validFindingByDateRange() {
        //When
        Allure.step("When: Użytkownik szuka transakcji w określonym zakresie dat");
        findTransactionsPage = new FindTransactionsPage(driver);
        findTransactionsPage.getIntoFindTransactionClick();
        findTransactionsPage.waitForNextMove();
        findTransactionsPage.selectAccount();
        findTransactionsPage.enterDateRange("10-09-2023","10-11-2023");
        findTransactionsPage.findTransactionsByDateRangeClick();
        findTransactionsPage.waitForNextMove();
        //Then
        Allure.step("Then: System wyświetla wyniki wyszukiwania");
        Assert.assertEquals(pageElements.findTransactionsResultTitle(), findTransactionsPage.getPageTitle());
    }
    @Test(description = "PB-21 - Poprawne znalezienie transakcji po kwocie transakcji")
    @Severity(SeverityLevel.BLOCKER)
    public void validFindingByAmount() {
        //When
        Allure.step("When: Użytkownik szuka transakcji po określonej kwocie");
        findTransactionsPage = new FindTransactionsPage(driver);
        findTransactionsPage.getIntoFindTransactionClick();
        findTransactionsPage.waitForNextMove();
        findTransactionsPage.selectAccount();
        findTransactionsPage.enterAmount("1000");
        findTransactionsPage.findTransactionsByAmountClick();
        findTransactionsPage.waitForNextMove();
        //Then
        Allure.step("Then: System wyświetla wyniki wyszukiwania");
        Assert.assertEquals(pageElements.findTransactionsResultTitle(), findTransactionsPage.getPageTitle());
    }
}

