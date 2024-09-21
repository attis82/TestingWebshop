import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryItemPageTest extends TestBase {
    @BeforeEach
    public void setUp() {
        driver.get("https://www.saucedemo.com/");
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    }

    @ParameterizedTest
    @ValueSource(strings = {"standard_user", "performance_glitch_user", "error_user"})
    @DisplayName("Adding an item to cart from the Inventory Item page working properly.")
    public void testAddItemToCartFromItemPageHappyCases(String username) {
        loginPage.login(username);
        addItemToCartFromItemPage();
    }
    @ParameterizedTest
    @ValueSource(strings = { "problem_user"})
    @DisplayName("Adding an item to cart from the Inventory Item page not working properly.")
    public void testAddItemToCartFromItemPageSadCases(String username) {
        loginPage.login(username);
        addItemToCartFromItemPage();
    }

    @ParameterizedTest
    @ValueSource(strings = {"standard_user", "performance_glitch_user"})
    @DisplayName("Remove an item from the cart on the InventoryItemPage working properly.")
    public void testRemoveItemFromCartFromItemPageHappyCases(String username) {
        loginPage.login(username);
        removeItemFromCartFromItemPage();
    }
    @ParameterizedTest
    @ValueSource(strings = {"problem_user", "error_user"})
    @DisplayName("Remove an item from the cart on the InventoryItemPage not working properly.")
    public void testRemoveItemFromCartFromItemPageSadCases(String username) {
        loginPage.login(username);
        removeItemFromCartFromItemPage();
    }

    @ParameterizedTest
    @ValueSource(strings = {"standard_user", "performance_glitch_user"})
    @DisplayName("Adding an item to cart from Inventory page and remove it from the Inventory Item page working properly.")
    public void testAddItemToCartFromInventoryPageAndRemoveFromItemPageHappyCases(String username) {
        loginPage.login(username);
        addToCartFromInventoryPageRemoveFromItemPage();
    }

    @ParameterizedTest
    @ValueSource(strings = {"problem_user", "error_user"})
    @DisplayName("Adding an item to cart from Inventory page and remove it from the Inventory Item page not working properly.")
    public void testAddItemToCartFromInventoryPageAndRemoveFromItemPageSadCases(String username) {
        loginPage.login(username);
        addToCartFromInventoryPageRemoveFromItemPage();
    }

    @ParameterizedTest
    @ValueSource(strings = {"standard_user", "performance_glitch_user"})
    @DisplayName("Adding an item to cart from Inventory Item page and remove it from Inventory page working properly.")
    public void testAddItemToCartFromItemPageAndRemoveFromInventoryPageHappyCases(String username) {
        loginPage.login(username);
        addToCartFromItemPageRemoveFromInventoryPage();
    }
    @ParameterizedTest
    @ValueSource(strings = {"problem_user", "error_user"})
    @DisplayName("Adding an item to cart from Inventory Item page and remove it from Inventory page not working properly.")
    public void testAddItemToCartFromItemPageAndRemoveFromInventoryPageSadCases(String username) {
        loginPage.login(username);
        addToCartFromItemPageRemoveFromInventoryPage();
    }

    @ParameterizedTest
    @ValueSource(strings = {"standard_user", "performance_glitch_user", "error_user"})
    @DisplayName("Checking if every items Inventory Item page content is correct")
    public void checkEveryItemsPage(String username) {
        loginPage.login(username);
        checkEveryItem();
    }

    @Test
    @DisplayName("Checking if every items Inventory Item page content is correct with problem_user")
    public void checkEveryItemsPageWithProblemUser() {
        loginPage.login("problem_user");
        checkEveryItem();
    }

    public void checkEveryItem() {
        inventoryPage.showProductDetails("Sauce Labs Backpack");
        assertTrue(inventoryItemPage.isProductNameEqual("Sauce Labs Backpack"));
        inventoryItemPage.backToProductsPage();

        inventoryPage.showProductDetails("Sauce Labs Bike Light");
        assertTrue(inventoryItemPage.isProductNameEqual("Sauce Labs Bike Light"));
        inventoryItemPage.backToProductsPage();

        inventoryPage.showProductDetails("Sauce Labs Bolt T-Shirt");
        assertTrue(inventoryItemPage.isProductNameEqual("Sauce Labs Bolt T-Shirt"));
        inventoryItemPage.backToProductsPage();

        inventoryPage.showProductDetails("Sauce Labs Fleece Jacket");
        assertTrue(inventoryItemPage.isProductNameEqual("Sauce Labs Fleece Jacket"));
        inventoryItemPage.backToProductsPage();

        inventoryPage.showProductDetails("Sauce Labs Onesie");
        assertTrue(inventoryItemPage.isProductNameEqual("Sauce Labs Onesie"));
        inventoryItemPage.backToProductsPage();

        inventoryPage.showProductDetails("Test.allTheThings() T-Shirt (Red)");
        assertTrue(inventoryItemPage.isProductNameEqual("Test.allTheThings() T-Shirt (Red)"));
    }

    public void addItemToCartFromItemPage() {
        inventoryPage.showProductDetails("Sauce Labs Backpack");
        inventoryItemPage.addToCart();
        inventoryItemPage.goToShoppingCartPage();
        assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"));
        cartPage.deleteItem("Sauce Labs Backpack");
    }

    private void removeItemFromCartFromItemPage() {
        inventoryPage.showProductDetails("Sauce Labs Backpack");
        inventoryItemPage.addToCart();
        inventoryItemPage.removeFromCart();
        inventoryItemPage.goToShoppingCartPage();
        assertFalse(cartPage.isItemInCart("Sauce Labs Backpack"));
    }

    private void addToCartFromInventoryPageRemoveFromItemPage() {
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.showProductDetails("Sauce Labs Backpack");
        inventoryItemPage.removeFromCart();
        inventoryItemPage.goToShoppingCartPage();
        assertFalse(cartPage.isItemInCart("Sauce Labs Backpack"));
    }

    private void addToCartFromItemPageRemoveFromInventoryPage() {
        inventoryPage.showProductDetails("Sauce Labs Backpack");
        inventoryItemPage.addToCart();
        inventoryItemPage.backToProductsPage();
        inventoryPage.removeProductFromCart("Sauce Labs Backpack");
        inventoryPage.goToShoppingCartPage();
        assertFalse(cartPage.isItemInCart("Sauce Labs Backpack"));
    }
}
