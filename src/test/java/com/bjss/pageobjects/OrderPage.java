package com.bjss.pageobjects;

import com.bjss.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.bjss.utilities.Common.WAIT_LONG_MS;

public class OrderPage {

    WebDriver driver;
    WebDriverWait wait;
    Product product;

    @FindBy(id = "quantity_wanted")
    WebElement txtQty;


    @FindBy(xpath = "//span[@id='our_price_display']")
    WebElement itemPrice;

    @FindBy(name = "Submit")
    WebElement btnSubmit;

    @FindBy(xpath = "//span[@id='our_price_display']")
    WebElement elePrice;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    WebElement eleContinueShopping;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, WAIT_LONG_MS);
        PageFactory.initElements(driver, this);
        product = new Product();
    }

    public void addItemToBasket() {
        addItemToBasket("");
    }

    public void enterQuntity(String qty) {
        txtQty.clear();
        txtQty.sendKeys(qty);
    }

    public void addToCart() {
        btnSubmit.click();
    }

    public void addItemToBasket(String Qty) {
        String price = "0";
        if (Qty.length() > 0) {
            enterQuntity(Qty);
        }
        addToCart();

    }

    public String getSelectedItemName() {
        return driver.findElement(By.cssSelector("div.layer_cart_product_info>span.product-name")).getText();
    }


    public String getItemPrice() {
        return itemPrice.getText();
    }

    public String getSelectedItemColorSize() {
        return driver.findElement(By.cssSelector("div.layer_cart_product_info>span#layer_cart_product_attributes")).getText().replace(" ", "");
    }

    public String getSelectedItemQty() {
        return driver.findElement(By.cssSelector("div.layer_cart_product_info>div>span#layer_cart_product_quantity")).getText();
    }

    public Product clickContinueShopping() {
        product.setPrice(getItemPrice());
        product.setColor(getSelectedItemColorSize());
        product.setSize(getSelectedItemColorSize());
        product.setQty(getSelectedItemQty());
        product.setName(getSelectedItemName());
        eleContinueShopping.click();
        driver.navigate().refresh();

        return product;
    }

}
