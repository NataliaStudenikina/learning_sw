package ru.learning.selenium.tasks;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Set;


public class FourteenthTask extends TestBase {

    @Test
    public void checkingLink() {
        clickOnElement("//span[text()='Countries']");
        clickOnElement("//a[text()='Afghanistan']");
        findLinks();
    }

    private void clickOnElement(String pathToElement){
        driver.findElement(By.xpath(pathToElement)).click();
    }

    private void findLinks(){
        List<WebElement> elements = driver.findElements(By.xpath("//form/table[1]//tr//a/i"));
        elements.forEach(webElement -> {
            webElement.click();
            String parentWindow = driver.getWindowHandle();
            Set<String> handles =  driver.getWindowHandles();
            for(String windowHandle  : handles)
            {
                if(!windowHandle.equals(parentWindow))
                {
                    driver.switchTo().window(windowHandle);
                    System.out.println("Выполнено переключение на " + windowHandle);
                    driver.close();
                    driver.switchTo().window(parentWindow);
                }
            }
        });
    }

}
