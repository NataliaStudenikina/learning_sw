package ru.learning.selenium.tasks;

import org.junit.Test;
import org.openqa.selenium.By;

public class ThirdTask extends TestBase {

    public static final String LOGIN_DETAILS ="admin";

    @Test
    public void fillingLogin() throws Exception {
        driver.get("http://192.168.64.2/public_html/admin/");
        driver.findElement(By.name("username")).sendKeys(LOGIN_DETAILS);
        driver.findElement(By.name("password")).sendKeys(LOGIN_DETAILS);
        driver.findElement(By.name("login")).click();
        Thread.sleep(1000);
    }
}
