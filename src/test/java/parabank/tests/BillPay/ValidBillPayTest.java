package parabank.tests.BillPay;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import parabank.pages.BillPayPage;
import parabank.pages.LoginPage;
import parabank.tests.BaseTest;

public class ValidBillPayTest extends BaseTest {
    @Test(description = "PB-13 - Poprawne opłacenie rachunków")
    @Severity(SeverityLevel.BLOCKER)
    public void validBillPayTest() {
        //Given
        Allure.step("Given: Użytkownik jest zalogowany");
        loginPage = new LoginPage(driver);
        loginPage.loginUser(standardUser.getUsername(), standardUser.getPassword());
        //When
        Allure.step("When: Użytkownik opłaca rachunek");
        billPayPage = new BillPayPage(driver);
        String accNumber = "100032";
        billPayPage.sendBillPayForm("someName", standardUser.getAddress(), standardUser.getCity(), standardUser.getState(), standardUser.getZipCode(), standardUser.getPhone(), accNumber, accNumber,"100");
        //Then
        Allure.step("Then: System potwierdza poprawne opłacenie rachunku");
        Assert.assertEquals(pageElements.getBillPayTitle(), billPayPage.getBillPayTitle());
    }
}
