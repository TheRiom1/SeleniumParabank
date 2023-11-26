package parabank;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import parabank.utils.DriverFactory;

import java.io.ByteArrayInputStream;


public class AllureListener implements IInvokedMethodListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }


    @SneakyThrows
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && testResult.getStatus() == ITestResult.FAILURE) {
            Allure.step("Test zakończony niepowodzeniem", Status.FAILED);
            WebDriver driver = DriverFactory.getDriver();
            Allure.addAttachment("Any text", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }



}

