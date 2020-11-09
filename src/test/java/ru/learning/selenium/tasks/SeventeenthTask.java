package ru.learning.selenium.tasks;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntry;
import java.util.List;

public class SeventeenthTask extends TestBase{

    @Test
    public void checkMessagesInLog() {
        clickOnElement("//span[text()='Catalog']");
        clickOnElement("//li[@id='doc-catalog']");
        clickOnElement("//form[@name='catalog_form']//tr//td[3]/a");
        clickOnElement(".//a[text()='Subcategory']");
        openLinks();
    }

    private void clickOnElement(String pathToElement) {
        driver.findElement(By.xpath(pathToElement)).click();
    }

    private void openLinks() {
        String itemSelector = ".//form[@name='catalog_form']//tr//td[3]/a";
        int elementsCount = driver.findElements(By.xpath(itemSelector)).size();
        for (int i = 1; i <= elementsCount; i++) {
            driver.findElement(By.xpath(String.format("(%s)[%s]", itemSelector, i))).click();
            waitDocumentReady();
            checkNullableLogs();
            driver.navigate().back();
        }
    }

    private void checkNullableLogs() {
        getLogs().forEach(Assert::assertNull);
    }

    private List<LogEntry> getLogs() {
        return driver.manage().logs().get("browser").getAll();
    }
}
