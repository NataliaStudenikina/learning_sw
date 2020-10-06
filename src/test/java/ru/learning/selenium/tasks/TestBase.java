package ru.learning.selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected static WebDriver driver;
    public static final String LOCAL_HOST = "http://192.168.64.2/public_html/admin/";
    private final String USER_LOGIN = "admin";
    private final String USER_PASSWORD = "admin";

    @Before
    public void startDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drv/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(LOCAL_HOST);
        fillLogin();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MICROSECONDS);

        waitDocumentReady();
    }

    @After
    public void stopDriver() {
        driver.close();
    }

    private void fillLogin() {
        driver.findElement(By.name("username")).sendKeys(USER_LOGIN);
        driver.findElement(By.name("password")).sendKeys(USER_PASSWORD);
        driver.findElement(By.name("login")).click();
        waitDocumentReady();
    }

    protected void goToPage(String pageName) {
        driver
                .findElement(By.xpath(String.format(".//ul[@id = 'box-apps-menu']//span[text() ='%s']", pageName)))
                .click();

        waitDocumentReady();
    }

    protected void goToUrl(String url) {
        driver.get(url);
        waitDocumentReady();
    }


    protected void waitDocumentReady() {
        new WebDriverWait(driver, 20).until(
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException("Something wrong with thread sleep");
        }
    }

    protected void openPage(String page){
        driver.get(page);
        waitDocumentReady();
    }
}
