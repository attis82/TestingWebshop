package com.codecool.sauceTest.util;

public class MoneyUtil {
    public static int asCents(String price) {
        return Integer.parseInt(price.replaceAll("[.,]", ""));
    }
}
