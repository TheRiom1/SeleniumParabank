package parabank.tests;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.ITestResult;
import org.testng.annotations.*;
import parabank.AllureListener;
import parabank.model.PageElements;
import parabank.model.User;
import parabank.pages.*;
import parabank.utils.DriverFactory;
import parabank.utils.QaseIntegration;

import java.io.IOException;
import java.lang.reflect.Method;

import static parabank.utils.StringHelper.extractTestNumber;
import static parabank.utils.StringHelper.extractTestNumberAsString;
@Listeners({AllureListener.class})
public class BaseTest {

    public static WebDriver driver;
    public RegisterPage registerPage;

    public LoginPage loginPage;
    public ForgotLoginInfoPage forgotLoginInfoPage;
    public OpenNewAccountPage openNewAccountPage;
    public TranferFundsPage tranferFundsPage;
    public FindTransactionsPage findTransactionsPage;
    public BillPayPage billPayPage;
    public UpdateContactInfoPage updateContactInfoPage;
    public RequestLoanPage requestLoanPage;
    protected String description;

    protected User standardUser = new User("name", "surname","street","city","state","code","123456789","123","username","password");
    public PageElements pageElements = new PageElements();


    protected static final String BASE_URL = "https://parabank.parasoft.com/parabank";

    @BeforeMethod
    public void name(Method method) {
        description = method.getAnnotation(Test.class).description();
    }
   //zmienna statyczna przyjmująca driver

    //ustawienie przeglądarki przed każdym testem
    @BeforeClass
    public static void setUpChromeDriver() throws IOException {
        driver = DriverFactory.getDriver();
        driver.get(BASE_URL);
    }
    @AfterMethod
    public void afterTestMethod(ITestResult result) throws Exception {

        QaseIntegration qaseIntegration = new QaseIntegration();
        String testCaseId = extractTestNumberAsString(description);
        // Sprawdź status testu
        if (result.getStatus() == ITestResult.SUCCESS) {
            qaseIntegration.updateTestCaseStatus(testCaseId, "passed", "PB", "4");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            qaseIntegration.updateTestCaseStatus(testCaseId, "failed", "PB", "4");
        } else if (result.getStatus() == ITestResult.SKIP) {
            qaseIntegration.updateTestCaseStatus(testCaseId, "skipped", "PB", "4");
        }

        // Dodatkowe działania po zakończeniu testu
    }



    @AfterClass
    public static void tearDownDriver() {
        //driver.quit();
        DriverFactory.quitDriver();
    }
//    @AfterTest
//    public void clearBrowser() {
//        driver.manage().deleteAllCookies();
//        LocalStorage local = ((WebStorage) driver).getLocalStorage();
//        local.clear();
//    }

//    @BeforeClass
//    public void openSouceDemoPage() {
//        driver.get(BASE_URL);
//    }

    //otworzenie strony
//    @BeforeTest
//    public void openSouceDemoPage() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//        driver.get(BASE_URL);
//    }

    //wyczyszczenie przeglądarki przed każdym testem
//    @AfterMethod
//    public void clearBrowser() {
//        driver.manage().deleteAllCookies();
//        LocalStorage local = ((WebStorage) driver).getLocalStorage();
//        local.clear();
//
//    }

//    @BeforeTest
//    public static void driverSetup(){
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        driver = new ChromeDriver();
//    }
//    @AfterTest
//    public static void driverQuit(){
//
//        driver.quit();
//    }

}
