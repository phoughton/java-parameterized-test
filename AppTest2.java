package com.investigatingsoftware.app;

import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class AppTest1
{
    WebDriver driver;
    String baseUrl;

    @BeforeMethod
    public void openWebDriver() {
        System.setProperty("webdriver.chrome.driver","/home/phoughton/projects_code/chromedriver/chromedriver");
		driver = new ChromeDriver();
    	
        baseUrl = "https://www.bbc.co.uk/news";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void checkSearchSuggestTW()
    {
        driver.get(baseUrl);

        WebElement searchBox = driver.findElement(By.id("orb-search-q"));

        searchBox.sendKeys("Tomorrows world");
////*[@id="suggestion-0"]
        WebElement suggestion = driver.findElement(By.xpath("//*[@id=\"suggestion-0\"]"));

        suggestion.click();

        WebElement secondSearchBox = driver.findElement(By.id("search-input"));

        Assert.assertEquals(secondSearchBox.getAttribute("value"), "Tomorrow's World");

    }

    @Test
    public void checkSearchSuggestEG()
    {
        driver.get(baseUrl);

        WebElement searchBox = driver.findElement(By.id("orb-search-q"));

        searchBox.sendKeys("Ever Given");
////*[@id="suggestion-0"]
        WebElement suggestion = driver.findElement(By.xpath("//*[@id=\"suggestion-0\"]"));

        suggestion.click();

        WebElement secondSearchBox = driver.findElement(By.id("search-input"));

        Assert.assertEquals(secondSearchBox.getAttribute("value"), "Will Britain Ever Have a Black Prime Minister?");

    }



    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
