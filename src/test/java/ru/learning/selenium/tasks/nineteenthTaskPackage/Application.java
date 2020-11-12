package ru.learning.selenium.tasks.nineteenthTaskPackage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.learning.selenium.tasks.TestBase;

public class Application extends TestBase {

    public void clickOnElement(WebElement element){
        element.click();
        clickOnAAlert();
    }
    private void clickOnAAlert() {
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {}
    }






}
