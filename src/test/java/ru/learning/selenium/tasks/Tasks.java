package ru.learning.selenium.tasks;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class ThirdTask extends TestBase {

    public static final String LOGIN_DETAILS ="admin";
    public static final String LOCAL_HOST = "http://192.168.64.2/public_html/admin/";

   /*
   * FirstTask
   * */
    @Test
    public void searchNewsSoftwareTesting() {
        driver.get("https://software-testing.ru/");
        driver.findElement(By.xpath(".//span[text() = 'Новости']")).click();
    }

    /*
    * ThirdTask
    * */
    @Test
    public void fillLogin() {
        driver.get(LOCAL_HOST);
        driver.findElement(By.name("username")).sendKeys(LOGIN_DETAILS);
        driver.findElement(By.name("password")).sendKeys(LOGIN_DETAILS);
        driver.findElement(By.name("login")).click();
    }

    /*
     * SeventhTask
     * */
    @Test
    public void verifyMenuClickability(){
        fillLogin();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        int menuItemsCount = driver.findElements(By.xpath(".//ul[@id='box-apps-menu']/child::li")).size();
        for (int menuItemIndex = 1; menuItemIndex <= menuItemsCount ; menuItemIndex++) {
            driver.findElement(By.xpath(".//ul[@id='box-apps-menu']/child::li[" + menuItemIndex + "]")).click();
            checkHeader();

            int submenuItemsCount = driver.findElements(By.xpath(".//ul[@id='box-apps-menu']/child::li[" + menuItemIndex + "]//li")).size();
            for (int submenuItemIndex = 1; submenuItemIndex < submenuItemsCount ; submenuItemIndex++) {
                driver.findElement(By.xpath(".//ul[@id='box-apps-menu']/child::li[" + menuItemIndex + "]//li[" + submenuItemIndex + "]")).click();
                checkHeader();
            }
        }
    }


    private void checkHeader() {
        Assert.assertTrue("Header doesn't exist on page",isElementExists(By.cssSelector("h1")));
    }

    private boolean isElementExists(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }



}
