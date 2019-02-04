package com.bjss.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.bjss.utilities.Common.WAIT_LONG_MS;


public class ShoppingCartSummary {

    WebDriver driver;
    WebDriverWait wait;


    @FindBy(css = ".logout")
    WebElement eleLogout;



   /* Qty : //a[contains(text(),'Printed Summer Dress')]/../../../td[5]//input
    Total cost : //a[contains(text(),'Printed Summer Dress')]/../../../td[6]/span
    net price : //a[contains(text(),'Printed Summer Dress')]/../../../td[4]//span[@xpath='1']
    Total Price : //td[@id='total_product']
    Total shipping : //td[@id='total_shipping']
    Total Price : //td[@id='total_price_without_tax']
    Total : //span[@id='total_price']
*/

    public ShoppingCartSummary(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, WAIT_LONG_MS);
    }

    public boolean checkIfItemExists(String itemname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p/a[text()='" + itemname + "']")));
        return true;
    }

    public String getQty(String itemName) {
        return driver.findElement(By.xpath("//p/a[contains(text(),'" + itemName + "')]/../../../td[5]//input[@type='text']")).getAttribute("Value");
    }


    public String getColorAndSize(String itemName) {
        return driver.findElement(By.xpath("//p/a[contains(text(),'" + itemName + "')]/../../small[2]/a")).getText().replace("Color : ", "").replace("Size : ", "").replace(" ", "");
    }


    public String getUnitPrice(String itemName) {
        return driver.findElement(By.xpath("//p/a[contains(text(),'" + itemName + "')]/../../../td[4]//span/span")).getText();
    }

    public String getTotal_Item(String itemName) {
        return driver.findElement(By.xpath("//p/a[contains(text(),'" + itemName + "')]/../../../td[6]/span/span")).getText();
    }


    public String getTotalShipping() {
        return driver.findElement(By.xpath("//td[@id='total_shipping']")).getText();
    }

    public String getTotalProducts() {
        return driver.findElement(By.cssSelector("td#total_product")).getText().replace("$", "");
    }

    public String getTotal() {
        return driver.findElement(By.cssSelector("span#total_price")).getText();
    }

    public void clickCheckOut() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Proceed to checkout']"))).click();
    }

    public void acceptTerms() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".checker"))).click();
    }

    public void clickPayBankWire() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.bankwire"))).click();

    }


    public void confirmOrder() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.button[type='Submit']>span"))).click();

    }

    public void checkOrderConformation() {
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector(".cheque-indent>strong")), "Your order on My Store is complete"));
    }

    public void completeOrder() {
        clickCheckOut();

        clickCheckOut();
        acceptTerms();
        //   clickCheckOut();
        confirmOrder();
        clickPayBankWire();
        confirmOrder();
        checkOrderConformation();

    }

    public void logout() {
        eleLogout.click();

    }
}
