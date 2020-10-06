package ru.learning.selenium.tasks;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class Tasks extends TestBase {

    public static final String LOGIN_DETAILS = "admin";

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
        driver.findElement(By.name("username")).sendKeys(LOGIN_DETAILS);
        driver.findElement(By.name("password")).sendKeys(LOGIN_DETAILS);
        driver.findElement(By.name("login")).click();
    }

    /*
     * SeventhTask
     * */
    @Test
    public void verifyMenuClickability() {
        int menuItemsCount = driver.findElements(By.xpath(".//ul[@id='box-apps-menu']/child::li")).size();
        for (int menuItemIndex = 1; menuItemIndex <= menuItemsCount; menuItemIndex++) {
            driver.findElement(By.xpath(".//ul[@id='box-apps-menu']/child::li[" + menuItemIndex + "]")).click();
            checkHeader();

            int submenuItemsCount = driver.findElements(By.xpath(".//ul[@id='box-apps-menu']/child::li[" + menuItemIndex + "]//li")).size();
            for (int submenuItemIndex = 1; submenuItemIndex < submenuItemsCount; submenuItemIndex++) {
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
