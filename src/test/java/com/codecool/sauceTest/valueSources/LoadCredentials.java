package com.codecool.sauceTest.valueSources;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class LoadCredentials {

    static String usernameRequired = "Epic sadface: Username is required";
    static String pwRequired = "Epic sadface: Password is required";
    static String notMatched = "Epic sadface: Username and password do not match any user in this service";
    static String lockedOut = "Epic sadface: Sorry, this user has been locked out.";


    public static Stream<Arguments> loadUsernames() {
        return  Stream.of(
                Arguments.of("standard_user", "https://www.saucedemo.com/inventory.html" ),
                Arguments.of("problem_user", "https://www.saucedemo.com/inventory.html" ),
                Arguments.of("performance_glitch_user", "https://www.saucedemo.com/inventory.html" ),
                Arguments.of("error_user",  "https://www.saucedemo.com/inventory.html"  ),
                Arguments.of("visual_user", "https://www.saucedemo.com/inventory.html" )
        );
    }

    public static Stream<Arguments> loadInvalidUsername() {

        return  Stream.of(
        Arguments.of("", "https://www.saucedemo.com/", usernameRequired ),
        Arguments.of("a", "https://www.saucedemo.com/", notMatched ),
        Arguments.of("locked_out_user", "https://www.saucedemo.com/", lockedOut )
                );
    }

    public static Stream<Arguments> loadInvalidPassword() {
        return  Stream.of(
                Arguments.of("standard_user", "", "https://www.saucedemo.com/", pwRequired ),
                Arguments.of("standard_user", "a", "https://www.saucedemo.com/", notMatched ),
                Arguments.of("locked_out_user", "", "https://www.saucedemo.com/", pwRequired ),
                Arguments.of("locked_out_user", "a", "https://www.saucedemo.com/", notMatched ),
                Arguments.of("problem_user", "", "https://www.saucedemo.com/", pwRequired ),
                Arguments.of("problem_user", "a", "https://www.saucedemo.com/", notMatched ),
                Arguments.of("performance_glitch_user", "", "https://www.saucedemo.com/", pwRequired ),
                Arguments.of("performance_glitch_user", "a", "https://www.saucedemo.com/", notMatched ),
                Arguments.of("error_user", "", "https://www.saucedemo.com/", pwRequired ),
                Arguments.of("error_user", "a", "https://www.saucedemo.com/", notMatched ),
                Arguments.of("visual_user", "", "https://www.saucedemo.com/", pwRequired ),
                Arguments.of("visual_user", "a", "https://www.saucedemo.com/", notMatched )
        );
    }
}
