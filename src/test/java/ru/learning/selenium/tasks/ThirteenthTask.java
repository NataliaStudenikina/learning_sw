package ru.learning.selenium.tasks;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ThirteenthTask extends TestBase{

    private final String PAGE_GOODS = "http://192.168.64.2/public_html/en/";
    //    .//span[@class='quantity']


    @Test
    public void checkCart() throws InterruptedException{
        openPage(PAGE_GOODS);
        clickFirstElement();
        clickOnElement(".//button[@name='add_cart_product']");
        waitRenewalCart("1");
        clickOnElement(".//i[@class='fa fa-home']");
        clickFirstElement();
        clickOnElement(".//button[@name='add_cart_product']");
        waitRenewalCart("2");
        clickOnElement(".//i[@class='fa fa-home']");
        clickFirstElement();
        clickOnElement(".//button[@name='add_cart_product']");
        waitRenewalCart("2");
        clickOnElement(".//a[text()='Checkout »']");

        // .//button[@name='remove_cart_item']



    }

    private void waitRenewalCart(String quantity) {
        int refreshCount = 10;
        String element = driver.findElement(By.xpath(".//span[@class='quantity']")).getText();
        for (int i = 0; i < refreshCount; i++) {
            if (element.equals(quantity)) {
            } else {
                driver.navigate().refresh();
            }
        }
    }

    private void clickOnElement(String pathToElement){
        driver.findElement(By.xpath(pathToElement)).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            Assert.assertTrue(alert.getText().contains("Thanks."));
        } catch (Exception e) {
            //exception handling
        }
    }

    private void clickFirstElement() {
        driver.findElement(By.xpath(".//div[@class='image-wrapper']")).click();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

}
