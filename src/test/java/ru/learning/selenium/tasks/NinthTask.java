package ru.learning.selenium.tasks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NinthTask extends TestBase{

    private final String LOCAL_HOST = "http://192.168.64.2/public_html/admin/";
    private final String USER_LOGIN = "admin";
    private final String USER_PASSWORD = "admin";

    @Before
    public void fillLogin() {
        driver.findElement(By.name("username")).sendKeys(USER_LOGIN);
        driver.findElement(By.name("password")).sendKeys(USER_PASSWORD);
        driver.findElement(By.name("login")).click();
        waitDocumentReady();
    }

    @Test
    public void verifyCountrySorting() {
        goToPage("Countries");

        checkSorting(getActualList(".//form[@name = 'countries_form']//tr//td[5]"));

        Collection<String> links = driver.findElements(By.xpath(".//form[@name = 'countries_form']//tr[contains(@class, 'row')]"))
                .stream()
                .filter(row -> Integer.parseInt(row.findElement(By.xpath(".//td[6]")).getText()) > 0)
                .map(row -> row.findElement(By.xpath(".//td[5]//a")).getAttribute("href"))
                .collect(Collectors.toList());

        links.forEach(link -> {
            goToUrl(link);
            checkSorting(getActualList(".//table[@id='table-zones']//tr//td[3]//input[@type='hidden']"));
        });
    }


    @Test
    public void verifyGeoZonesSorting() {
        goToPage("Geo Zones");
        Collection<String> links = driver.findElements(By.xpath(".//form[@name='geo_zones_form']//tr[contains(@class, 'row')]"))
                .stream()
                .map(row -> row.findElement(By.xpath(".//td[3]/a")).getAttribute("href"))
                .collect(Collectors.toList());

        links.forEach(link -> {
            goToUrl(link);
            checkSorting(getActualList(".//table[@id='table-zones']//tr//td[3]//option[@selected='selected']"));
        });


    }

    private List getActualList(String locator) {
        List actualCountries = driver
                .findElements(By.xpath(locator))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return actualCountries;
    }

    private void checkSorting(List actualCountries) {
        List expectedCountries = new ArrayList(actualCountries);
        expectedCountries.sort(Comparator.naturalOrder());

        Assert.assertEquals("The list of countries is not sorted", expectedCountries, actualCountries);
    }
}
