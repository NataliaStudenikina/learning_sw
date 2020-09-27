package ru.learning.selenium.tasks;

import org.junit.Test;
import org.openqa.selenium.By;

public class ThirdTask extends TestBase {

    public static final String LOGIN_DETAILS ="admin";
    public static final String LOCAL_HOST = "http://192.168.64.2/public_html/admin/";

    @Test
    public void fillLogin() throws Exception {
        driver.get(LOCAL_HOST);
        driver.findElement(By.name("username")).sendKeys(LOGIN_DETAILS);
        driver.findElement(By.name("password")).sendKeys(LOGIN_DETAILS);
        driver.findElement(By.name("login")).click();
    }
}
