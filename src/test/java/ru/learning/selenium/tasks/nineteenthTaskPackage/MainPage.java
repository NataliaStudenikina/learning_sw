package ru.learning.selenium.tasks.nineteenthTaskPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.learning.selenium.tasks.TestBase;

public class MainPage extends TestBase {

    private final String PAGE_GOODS = "http://192.168.64.2/public_html/en/";

    private WebElement firstElement() {
        return driver.findElement(By.xpath(".//div[@class='image-wrapper']"));
    }

    private WebElement backHome() {
        return driver.findElement(By.xpath(".//i[@class='fa fa-home']"));
    }


    public void openGoodsPage(){
        openPage(PAGE_GOODS);
    }

    public void clickFirstElement(){
        firstElement().click();
    }

    public void goToHome(){
        backHome().click();
    }



}
