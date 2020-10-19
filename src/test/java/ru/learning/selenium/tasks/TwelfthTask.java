package ru.learning.selenium.tasks;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TwelfthTask extends TestBase {

    private final String PAGE_GOODS =  "http://192.168.64.2/public_html/admin/?app=catalog&doc=catalog/";

    @Test
    public void checkForAddingGoods() {
        clickOnElement(".//span[text()='Catalog']");
        clickOnElement(".//a[text()=' Add New Product']");
        clickOnElement("//label[text()=' Enabled']");
        fillField("name[en]","New test product");
        fillField("code","New test product");
        fillField("code","New test product");
        clickOnElement("//input[@name='product_groups[]']");
        fillField("quantity","4");
        selectValueFromBox("sold_out_status_id","Temporary sold out");
        attachFile("new_images[]");
        fillField("date_valid_from","07102020");
        fillField("date_valid_to","07122020");
        clickOnElement(".//a[text()='Information']");
        selectValueFromBox("manufacturer_id", "ACME Corp.");
        fillField("keywords","Dog");
        fillField("short_description[en]","Funny little dog ");
        fillFieldByPath("//div[@class='trumbowyg-editor']",
                "The Rottweiler is a service breed of dogs of the Molossian group." +
                " Described in the city of Rottweil in the middle of the 18th century. " +
                "A large, strong-boned dog with a stable strong character.");
        fillField("head_title[en]","Dog");
        fillField("meta_description[en]", "Material: Textile, Faux Fur");
        clickOnElement("//a[text()='Prices']");
        fillField("purchase_price","30");
        selectValueFromBox("purchase_price_currency_code","US Dollars");
        fillField("prices[USD]","20");
        fillField("gross_prices[USD]","15");
        clickOnElement(".//a[@id='add-campaign']");
        fillField("campaigns[new_1][start_date]","07102020");
        fillField("campaigns[new_1][end_date]","07122020");
        fillField("campaigns[new_1][percentage]","07");
        fillField("campaigns[new_1][USD]","08");
        fillField("campaigns[new_1][EUR]","05");
        clickOnElement("//button[@name='save']");
    }

    public void attachFile( String elementName) {
        String path = getClass().getResource("/picture/dog.jpg").getPath();
        WebElement webElement = getWebElement(elementName);
        webElement.sendKeys(path);
    }

    private void selectValueFromBox(String elementName, String value) {
        WebElement webElement = getWebElement(elementName);
        final Select selectBox = new Select(webElement);
        selectBox.selectByVisibleText(value);
    }

    private void clickOnElement(String pathToElement){
        driver.findElement(By.xpath(pathToElement)).click();
        sleep(50);
    }

    private void fillField(String elementName, String value) {
        WebElement webElement = getWebElement(elementName);
        webElement.clear();
        webElement.sendKeys( value );
        sleep(400);
    }


    private void fillFieldByPath(String pathToElement, String value) {
        WebElement webElement = driver.findElement(By.xpath(pathToElement));
        webElement.clear();
        webElement.sendKeys( value );
        sleep(400);
    }

    private WebElement getWebElement(String elementName) {
        return driver.findElement(By.name(elementName));
    }

    private void sleep(Integer second){
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            throw new RuntimeException("Something wrong with thread sleep");
        }
    }

}
