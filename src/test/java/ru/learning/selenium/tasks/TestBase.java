package ru.learning.selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void startDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drv/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stopDriver() {
        driver.quit();
        driver = null;
    }
}
