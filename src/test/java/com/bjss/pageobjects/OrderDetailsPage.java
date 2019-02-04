package com.bjss.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.bjss.utilities.Common.WAIT_LONG_MS;

public class OrderDetailsPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "textarea.form-control")
    WebElement eleComment;


    @FindBy(css = "select.form-control")
    WebElement eleDropdpwn;

    @FindBy(css = "div.submit>button[type='submit']")
    WebElement btnSubmit;


    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, WAIT_LONG_MS);

    }

    public void selectOrderfromList() {
        Select dropdown = new Select(eleDropdpwn);
        dropdown.selectByIndex(0);
    }

    public void enterComment(String comment) {
        eleComment.sendKeys(comment);
    }


    public void clickSubmit() {
        btnSubmit.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p.alert.alert-success")));
    }

    public void checkCommentAddedToMessages(String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='table_block']//tr/td[text()='" + comment + "']")));
    }

    public String getQtyItem(String item) {
        return driver.findElement(By.xpath("//label[contains(text(),'" + item + "')]/../../td[3]/input")).getAttribute("value");
    }

    public void addComment(String comment) {
        selectOrderfromList();
        enterComment(comment);
        clickSubmit();


    }
}
