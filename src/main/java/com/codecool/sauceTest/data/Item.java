package com.codecool.sauceTest.data;

import org.openqa.selenium.WebElement;

public record Item(String name, String description, int cents, String quantity) {
}
