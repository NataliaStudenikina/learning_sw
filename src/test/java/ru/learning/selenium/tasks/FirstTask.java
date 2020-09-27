package ru.learning.selenium.tasks;

import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTask extends TestBase {

    @Test
    public void searchNewsSoftwareTesting() {
        driver.get("https://software-testing.ru/");
        driver.findElement(By.xpath(".//span[text() = 'Новости']")).click();
    }
}
