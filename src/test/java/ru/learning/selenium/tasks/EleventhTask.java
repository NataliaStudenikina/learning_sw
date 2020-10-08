package ru.learning.selenium.tasks;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.Random;

public class EleventhTask extends TestBase{

    private final String PAGE_GOODS = "http://192.168.64.2/public_html/en/";
    private final String PAGE_NEW_CUST = "http://192.168.64.2/public_html/en/create_account";
    @Test
    public void createNewUser() {
        openPage(PAGE_GOODS);
        String email = createRandomMail();
        String password = createRandomMail();

        clickOnElement("//a[text() = 'New customers click here']");
        fillField(getWebElement("tax_id"), "1000");
        fillField(getWebElement("firstname"), "Angelina");
        fillField(getWebElement("lastname"), "Jolie");
        fillField(getWebElement("address1"), "Los Angeles");
        fillField(getWebElement("address2"), "San Francisco");
        fillField(getWebElement("postcode"), "12345");
        fillField(getWebElement("city"), "California");
        selectCountryCode(getWebElement("country_code"),"United States");
        selectZone(getWebElement("zone_code"),"Alabama");
        fillField(getWebElement("company"), "Warner brothers");
        fillField(getWebElement("email"), email);
        fillField(getWebElement("phone"), "+109203820193");
        fillField(getWebElement("password"), password);
        fillField(getWebElement("confirmed_password"), password);
        clickOnElement(".//button[text()='Create Account']");
        clickOnElement(".//div[@id='box-account']//li[4]/a");
        fillField(getWebElement("email"), email);
        fillField(getWebElement("password"), password);
        clickOnElement(".//button[text()='Login']");
        clickOnElement(".//div[@id='box-account']//li[4]/a");

    }



    private void unhide( WebElement element) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].removeAttribute('disabled');"
                + "arguments[0].setAttribute('type', 'text');"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    private void selectZone(WebElement element, String value){
        unhide(element);
        fillField(element,value);
    }

    private String createRandomValue() {
        Random rnd = new Random(System.currentTimeMillis());
        int number = 100 + rnd.nextInt(10000 - 100 + 1);
        return String.valueOf(number);
    }
    private String createRandomMail(){
        return createRandomValue() + "@gmail.com";
    }

    private void selectCountryCode(WebElement webElement, String value) {
        final Select selectBox = new Select(webElement);
        selectBox.selectByVisibleText(value);
    }


    private void fillField(WebElement webElement, String value) {
        webElement.sendKeys(value );
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException("Something wrong with thread sleep");
        }
    }

    private WebElement getWebElement(String elementName) {
       return driver.findElement(By.name(elementName));
    }

    private void clickOnElement(String pathToElement){
        driver.findElement(By.xpath(pathToElement)).click();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException("Something wrong with thread sleep");
        }
    }

}
