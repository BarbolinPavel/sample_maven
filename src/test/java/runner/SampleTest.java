package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
public class SampleTest extends BaseTest{
    @Test(description="этот тест проверяет вход на сайт")
    public void firstTest(){
        driver.get("https://banki.ru");

        WebElement tagLine = driver.findElement(By.cssSelector("[data-test='main-seo-block__title']"));
        String tagText = tagLine.getText();


        Assert.assertEquals(tagText, "Банки.ру - финансовый маркетплейс*");
    }
}