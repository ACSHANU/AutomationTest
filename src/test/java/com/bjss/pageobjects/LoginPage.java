package com.bjss.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.bjss.utilities.Common.WAIT_LONG_MS;


public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "email")
    WebElement txtUsername;

    @FindBy(css = "login")
    WebElement lnkLogin;


    @FindBy(id = "passwd")
    WebElement txtPassword;

    @FindBy(id = "SubmitLogin")
    WebElement btnLogin;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, WAIT_LONG_MS);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        txtUsername.sendKeys(username);
    }

    public void clicklognIn() {
        lnkLogin.click();
    }

    public void enterPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void clickSignIn() {

        btnLogin.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='Women']")));

    }

    public ProductsPage navigateToProductsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='Women']"))).click();
        return new ProductsPage(driver);
    }

    public OrderHistoryPage navigateToOrderHistoryPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='Orders']"))).click();
        return new OrderHistoryPage(driver);
    }

    public void login(String Username, String password) {

        this.enterUsername(Username);
        this.enterPassword(password);
        this.clickSignIn();

    }

}
