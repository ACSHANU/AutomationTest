package com.bjss.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.bjss.utilities.Common.WAIT_LONG_MS;


public class ProductsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    WebElement eleCart;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;

        wait = new WebDriverWait(driver, WAIT_LONG_MS);
        PageFactory.initElements(driver, this);
    }

    public OrderPage clickQuickView(String product) {

        moveToprodict(product);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + product + "']/../a[@class='quick-view']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fancybox-iframe")));
        driver.switchTo().frame(driver.findElement(By.cssSelector(".fancybox-iframe")));

        return new OrderPage(driver);

    }

    public void moveToprodict(String product) {
        WebElement eleProduct = driver.findElement(By.xpath(("//img[@title='" + product + "']")));

        Actions builder = new Actions(driver);
        builder.moveToElement(eleProduct).build().perform();
    }

    public String getItemPrice(String product) {
        moveToprodict(product);
        return driver.findElement(By.xpath("//h5/a[@title='" + product + "']/../../div[@class='content_price']/span")).getText();

    }


    public ShoppingCartSummary viewCart() {
        eleCart.click();
        return new ShoppingCartSummary(driver);
    }

}
