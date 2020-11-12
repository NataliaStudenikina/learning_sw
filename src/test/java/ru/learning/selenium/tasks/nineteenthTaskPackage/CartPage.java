package ru.learning.selenium.tasks.nineteenthTaskPackage;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.learning.selenium.tasks.TestBase;
import java.util.List;


public class CartPage extends TestBase {

    Application application = new Application();

    private List<WebElement> goods() {
        return driver.findElements(By.xpath(".//div[@id='checkout-cart-wrapper']//li[@class='item']"));
    }

    private WebElement goodsName() {
        return driver.findElement(By.xpath(".//div[@id='checkout-cart-wrapper']//li[@class='item']//strong"));
    }

    private WebElement removeButton() {
        return driver.findElement(By.xpath(".//button[@name='remove_cart_item']"));
    }


    public void deleteItem() {
        int count = goods().size();
        for (int i = 1; i <= count; i++) {
            String name = goodsName().getText();
            application.clickOnElement(removeButton());
            Assert.assertFalse(isElementPresent((name)));
        }
    }

    public boolean isElementPresent(String name) {
        try {
            driver.findElement(By.xpath(String.format("//td[text()='%s']",name )));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
