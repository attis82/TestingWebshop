import com.codecool.sauceTest.valueSources.LoadCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest extends TestBase{
    @BeforeEach
    public void setUp() {
        driver.get("https://www.saucedemo.com/");
    }


    @ParameterizedTest
    @MethodSource
    public void testLogin(
            String username,
            String expectedUrl
    ) {
        loginPage.login(username);
        String url = driver.getCurrentUrl();
        assertEquals(expectedUrl, url);
    }

    @ParameterizedTest
    @MethodSource("testInvalidPassword")
    public void testInvalidPasswords(
            String userName,
            String password,
            String expectedUrl,
            String errorMessage
    ){
        loginPage.loginWithPassword(userName, password);
        assertEquals(expectedUrl, driver.getCurrentUrl());
        assertEquals(errorMessage, loginPage.getErrorMessage());
    }

    @ParameterizedTest
    @MethodSource("testInvalidUsername")
    public void testInvalidUsernames(
            String username,
            String expectedUrl,
            String errorMessage
    ){
        loginPage.login(username);
        assertEquals(expectedUrl, driver.getCurrentUrl());
        assertEquals(errorMessage, loginPage.getErrorMessage());
    }

    @Test
    public void testErrorMessage(){
        loginPage.login("");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals("Epic sadface: Username is required", errorMessage);
    }

    private static Stream<Arguments> testLogin(){
        return LoadCredentials.loadUsernames();
    }

    private static Stream<Arguments> testInvalidPassword(){
        return LoadCredentials.loadInvalidPassword();
    }

    private static Stream<Arguments> testInvalidUsername(){
        return LoadCredentials.loadInvalidUsername();
    }
}
