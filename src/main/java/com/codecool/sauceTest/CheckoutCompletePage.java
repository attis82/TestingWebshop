package com.codecool.sauceTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutCompletePage extends PageBase{
    @FindBy(id ="back-to-products")
    private WebElement backHomeButton;

    public CheckoutCompletePage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
    }

    public void clickOnBackHomeButton() {
        backHomeButton.click();
    }
}
