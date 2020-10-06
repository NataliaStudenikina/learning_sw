package ru.learning.selenium.tasks;

import org.junit.Assert;
import org.junit.Test;
import ru.learning.selenium.tasks.elements.Good;
import ru.learning.selenium.tasks.elements.GoodsContainer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;


public class EighthTask extends TestBase {

    public final String PAGE_GOODS = "http://192.168.64.2/public_html/en/";

    @Test
    public void verifyStickerOnGoods() {
        driver.get(PAGE_GOODS);
        waitDocumentReady();

        Collection<Good> latestGoods = new GoodsContainer(driver).get("latest-products").getGoods();
        Collection<Good> mostPopularGoods = new GoodsContainer(driver).get("most-popular").getGoods();
        Collection<Good> campaignsGoods = new GoodsContainer(driver).get("campaigns").getGoods();

        union(latestGoods, mostPopularGoods, campaignsGoods).forEach(good -> {
            Assert.assertEquals("Good should include one sticker. " + good.toString(), 1, good.getStickers().size());
        });
    }

    private <T> Collection<T> union(Collection<T> ... args) {
        List<T> list = new ArrayList<T>();
        Stream.of(args).forEach(list::addAll);

        return list;
    }

}
