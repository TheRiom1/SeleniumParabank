package parabank.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static parabank.utils.StringHelper.generateUniqueStringOfLettersUsingCurrentData;


public class SeleniumHelper {

    public static String takeScreenshot(WebDriver driver) throws IOException {
        String name = generateUniqueStringOfLettersUsingCurrentData();
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots/screenshot" + name + ".png";
        FileUtils.copyFile(file, new File(path));
        return path;
    }
}
