package ru.learning.selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTask {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void startDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drv/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void searchNewsSoftwareTesting() {
        driver.get("https://software-testing.ru/");
        driver.findElement(By.xpath(".//span[text() = 'Новости']")).click();
    }

    @After
    public void stopDriver() {
        driver.quit();
        driver = null;
    }

}
