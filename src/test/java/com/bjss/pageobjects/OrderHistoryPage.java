package com.bjss.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.bjss.utilities.Common.WAIT_LONG_MS;


public class OrderHistoryPage {
    WebDriver driver;
    WebDriverWait wait;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, WAIT_LONG_MS);

    }

    public OrderDetailsPage clickOrderDetailsByIndex(String index) {
        //   System.out.println("clickOrderDetailsByIndex");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tbody//tr[" + index + "]//td[7]//a[1]//span[1]")))).click();
        return new OrderDetailsPage(driver);

    }


}
