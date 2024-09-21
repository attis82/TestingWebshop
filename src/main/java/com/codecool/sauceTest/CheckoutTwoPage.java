package com.codecool.sauceTest;

import com.codecool.sauceTest.data.Item;
import com.codecool.sauceTest.util.MoneyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutTwoPage extends PageBase{
    @FindBy(xpath="//div[@data-test='inventory-item']")
    private List<WebElement> inventoryItems;
    @FindBy(xpath="//div[@data-test='subtotal-label']")
    private WebElement subtotal;
    @FindBy(xpath="//div[@data-test='tax-label']")
    private WebElement tax;
    @FindBy(xpath="//div[@data-test='total-label']")
    private WebElement total;
    @FindBy(id = "cancel")
    private WebElement cancelButton;
    @FindBy(id = "finish")
    private WebElement finishButton;

    private List<Item> items;

    public CheckoutTwoPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
        items = findItems();
    }

    public List<Item> getItems() {
        return List.of(items.toArray(new Item[0]));
    }

    private List<Item> findItems() {
        return inventoryItems.stream()
            .map(this::buildItem)
            .collect(Collectors.toList());
    }

    private Item buildItem(WebElement item) {
        return new Item(
            getProperty(item, "inventory-item-name"),
            getProperty(item, "inventory-item-desc"),
            MoneyUtil.asCents(getProperty(item, "inventory-item-price")),
            getProperty(item, "item-quantity")
        );
    }

    private String getProperty(WebElement item, String property) {
        String xpath = ".//div[@data-test='%s']".formatted(property);
        return item.findElement(By.xpath(xpath)).getText();
    }
}
