package ru.learning.selenium.tasks.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.stream.Collectors;

public class CountryLInks {

    private WebDriver driver = null;
    private WebElement container = null;


    public CountryLInks(WebDriver driver) {
        this.driver = driver;
    }

    public CountryLInks get(String name) {
        container = driver.findElement(By.xpath(String.format(".//div[@id = 'box-%s']//ul", name)));
        return this;
    }

    public Collection<Good> getGoods() {
        if (container == null) throw new NullPointerException("Goods container doesn't exist. At first you should use GoodsContainer#get");

        return container
                .findElements(By.tagName("li"))
                .stream()
                .map(Good::new)
                .collect(Collectors.toList());
    }


}
