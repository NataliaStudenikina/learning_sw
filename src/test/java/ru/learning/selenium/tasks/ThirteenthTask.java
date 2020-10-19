package ru.learning.selenium.tasks;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class ThirteenthTask extends TestBase{

    private final String PAGE_GOODS = "http://192.168.64.2/public_html/en/";

    @Test
    public void checkCart() throws InterruptedException{
        openPage(PAGE_GOODS);
        clickFirstElement();
        addItemToCart("1");
        addItemToCart("2");
        addItemToCart("3");
        clickOnElement(".//a[text()='Checkout »']");
        deleteItems(3);
    }

    private void addItemToCart(String numberItem) {
        clickFirstElement();
        clickOnElement(".//button[@name='add_cart_product']");
        waitRenewalCart(numberItem);
        clickOnElement(".//i[@class='fa fa-home']");
    }


    private void deleteItems(Integer count) {
        for (int i = 0; i < count; i++){
            WebElement element = driver.findElement(By.xpath(".//div[@id='order_confirmation-wrapper']//td[3]"));
            clickOnElement(".//button[@name='remove_cart_item']");
            driver.navigate().refresh();
            new WebDriverWait(driver, 20/*seconds*/).until(ExpectedConditions.stalenessOf(element));
        }
    }

    private void waitRenewalCart(String quantity) {
        int refreshCount = 10;
        String element = driver.findElement(By.xpath(".//span[@class='quantity']")).getText();
        for (int i = 0; i < refreshCount; i++) {
            if (element.equals(quantity)) {
            } else {
                driver.navigate().refresh();
                new WebDriverWait(driver, 20/*seconds*/);
            }
        }
    }

    private void clickOnElement(String pathToElement){
        driver.findElement(By.xpath(pathToElement)).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        clickOnAAlert();
    }

    private void clickOnAAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
        }
    }

    private void clickFirstElement() {
        driver.findElement(By.xpath(".//div[@class='image-wrapper']")).click();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

}
