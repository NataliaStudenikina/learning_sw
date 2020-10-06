package ru.learning.selenium.tasks.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Collection;
import java.util.stream.Collectors;

public class Good {
    private WebElement element;

    public Good(WebElement element) {
        this.element = element;
    }

    public Collection<Sticker> getStickers() {
        return element
                .findElements(By.className("sticker"))
                .stream()
                .map(Sticker::new)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Good{" +
                "element=" + element +
                '}';
    }
}
