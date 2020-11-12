package ru.learning.selenium.tasks.nineteenthTaskPackage;

import org.junit.Test;
import ru.learning.selenium.tasks.TestBase;

public class NineteenthTask extends TestBase {

    GoodsPage goodsPage = new GoodsPage();
    MainPage  mainPage = new MainPage();
    CartPage cartPage = new CartPage();

    @Test
    public void checkCart(){
        mainPage.openGoodsPage();
        goodsPage.addItemsToCart(3);
        goodsPage.goToCart();
        cartPage.deleteItem();
    }

}
