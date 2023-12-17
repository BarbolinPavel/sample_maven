package runner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

public abstract class BaseTest {
    public WebDriver driver;

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleTest.class);

    @BeforeMethod
    protected void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) + ".jpg"));
        }
    }

    @AfterMethod
    public void takeLogsFile(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LOGGER.error("Тест " + result.getMethod().getMethodName() + " завершился неудачно.");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            LOGGER.info("Тест " + result.getMethod().getMethodName() + " успешно выполнен.");
        }
    }
//    @AfterMethod
//    protected void afterMethod() {
//        driver.quit();
//    }
}
