package ru.learning.selenium.tasks.nineteenthTaskPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.learning.selenium.tasks.TestBase;

public class GoodsPage extends TestBase {

    Application application = new Application();
    MainPage mainPage = new MainPage();

    private WebElement addButton() {
        return driver.findElement(By.xpath(".//button[@name='add_cart_product']"));
    }

    private WebElement checkoutButton() {
        return driver.findElement(By.xpath(".//a[text()='Checkout »']"));
    }

    private WebElement quantityGoodsIcCard() {
        return driver.findElement(By.xpath(".//span[@class='quantity']"));
    }

    private void waitRenewalCart(String quantity) {
        String element = (quantityGoodsIcCard().getText());
        while(element.equals(quantity)) {
            driver.navigate().refresh();
            new WebDriverWait(driver, 30);
        }
    }

    public void addItemsToCart(Integer numberGood) {
        for (int i = 1; i <= numberGood; i++){
            addItemToCart(i);
        }
    }

    public void goToCart(){
        checkoutButton().click();
    }

    private void addItemToCart(Integer numberItem){
        mainPage.clickFirstElement();
        application.clickOnElement(addButton());
        waitRenewalCart(numberItem.toString());
        mainPage.goToHome();
        waitDocumentReady();
    }
}
