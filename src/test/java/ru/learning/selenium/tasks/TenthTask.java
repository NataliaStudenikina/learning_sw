package ru.learning.selenium.tasks;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TenthTask extends TestBase{

    private final String PAGE_GOODS = "http://192.168.64.2/public_html/en/";
    private final String DUCK_PAGE = "http://192.168.64.2/public_html/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1";

    @Test
    public void checkRightPage() {
        openPage(PAGE_GOODS);

        String nameGoods = driver.findElement(By.xpath(".//div[@id='box-campaigns']//a/div[2]")).getAttribute("textContent");
        String oldPrice = driver.findElement(By.xpath(".//div[@id='box-campaigns']//s[@class='regular-price']")).getAttribute("textContent");
        String newPrice = driver.findElement(By.xpath(".//div[@id='box-campaigns']//strong[@class='campaign-price']")).getAttribute("textContent");
        String oldPriceTextDecoration = driver.findElement(By.xpath(".//div[@id='box-campaigns']//s[@class='regular-price']")).getCssValue("text-decoration-line");
        String oldPriceColor = driver.findElement(By.xpath(".//div[@id='box-campaigns']//s[@class='regular-price']")).getCssValue("color");
        String newPriceFontWeight = driver.findElement(By.xpath(".//div[@id='box-campaigns']//strong[@class='campaign-price']")).getCssValue("font-weight");
        String newPriceColor = driver.findElement(By.xpath(".//div[@id='box-campaigns']//strong[@class='campaign-price']")).getCssValue("color");
        String newPriceSize = driver.findElement(By.xpath(".//div[@id='box-campaigns']//strong[@class='campaign-price']")).getCssValue("font-size");
        String oldPriceSize = driver.findElement(By.xpath(".//div[@id='box-campaigns']//s[@class='regular-price']")).getCssValue("font-size");

        Assert.assertEquals("Text for old price is't strikethrough out", "line-through", oldPriceTextDecoration);
        Assert.assertTrue("Text for new price is't bold", checkBold(newPriceFontWeight));
        checkGrayColor(oldPriceColor);
        checkRedColor(newPriceColor);
        checkSize(oldPriceSize, newPriceSize);

        openPage(DUCK_PAGE);

        String nameGoodsOnGoodsPage = driver.findElement(By.xpath(".//h1")).getAttribute("textContent");
        String oldPriceOnGoodsPage = driver.findElement(By.xpath(".//s[@class='regular-price']")).getAttribute("textContent");
        String newPriceOnGoodsPage = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getAttribute("textContent");
        String campaignPriceFontWeight = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-weight");
        String campaignPriceColor = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("color");
        String campaignPriceSize = driver.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-size");
        String oldPriceOnGoodsPageSize = driver.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("font-size");

        Assert.assertEquals("Goods name do not match", nameGoods,nameGoodsOnGoodsPage);
        Assert.assertEquals("Old prices do not match", oldPrice,oldPriceOnGoodsPage);
        Assert.assertEquals("New prices do not match", newPrice,newPriceOnGoodsPage);
        Assert.assertTrue("Text for new price is't bold", checkBold(campaignPriceFontWeight));
        checkRedColor(campaignPriceColor);
        checkSize(oldPriceOnGoodsPageSize, campaignPriceSize);
    }


    private void checkSize(String sizeOld,String sizeNew) {
        String priceOld = sizeOld.replace("px","");
        String priceNew = sizeNew.replace("px","");

        if (!(Float.parseFloat(priceNew) > Float.parseFloat(priceOld))){
            System.out.println("Size new price less than old price");
        }
    }

    private Stream<Integer> getColor(String rgb) {
        var matcher = Pattern.compile("(\\d{1,3}), (\\d{1,3}), (\\d{1,3})").matcher(rgb);
        matcher.find();
        return List
                .of(matcher.group(1), matcher.group(2), matcher.group(3))
                .stream()
                .map(Integer::parseInt);
    }

    private void checkGrayColor(String rgb) {
        var actualCount = getColor(rgb)
                .collect(Collectors.toSet())
                .size();

        Assert.assertEquals("Color should be grey", 1, actualCount); // Color is grey if all RGB values are same
    }

    private void checkRedColor(String rgb) {
        var colors = getColor(rgb)
                .collect(Collectors.toList());

        Assert.assertEquals("Color should be red", colors.get(1), colors.get(2)); // Color is red if second and third RGB values are same
    }

    private boolean checkBold(String value) {
        return value.equals("700") || value.equals("bold");
    }
}
