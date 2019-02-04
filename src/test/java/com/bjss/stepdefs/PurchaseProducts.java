package com.bjss.stepdefs;

import com.bjss.models.Product;
import com.bjss.pageobjects.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.bjss.stepdefs.ServiceHooks.driver;

public class PurchaseProducts {

    LoginPage loginpage;
    ProductsPage productspage;
    OrderPage orderpage;
    OrderHistoryPage orderHistoryPage;
    OrderDetailsPage orderdetailsPage;
    ShoppingCartSummary summary;
    Map<String, Product> mapProduct = new HashMap<String, Product>();
    Double expectedTotalprice = 0.00;
    Double actualPrice = 0.00;
    Double deliveryCost = 2.00;
    Double Total = 0.00;
    String comment;

    @Given("User navigate to Login page")
    public void user_navigate_to_Login_page() {
        loginpage = new LoginPage(driver);
    }

    @When("login using emailaddress as {string} and passwprd as {string}")
    public void login_using_emailaddress_as_and_passwprd_as(String username, String password) {

        loginpage.login(username, password);
    }

    @Then("Should be sucessfully Loggedin")
    public void should_be_sucessfully_Loggedin() {


    }

    @Given("User navigates to products page")
    public void user_navigates_to_products_page() {

        productspage = loginpage.navigateToProductsPage();
    }

    @When("User clicks on quickView of {string}")
    public void user_clicks_on_quickView_of(String item) {
        orderpage = productspage.clickQuickView("Blouse");
    }


    @When("Enter Quantity {string} and Add that item to your basket")
    public void enter_Quantity_and_Add_that_item_to_your_basket(String qty) {
        orderpage.addItemToBasket(qty);
    }


    @When("Continue Shopping")
    public void continue_Shopping() {
        Product product = orderpage.clickContinueShopping();
        mapProduct.put(product.getName(), product);
    }

    @When("User clicks on quickview of {string}")
    public void user_clicks_on_quickview_of(String item) {
        orderpage = productspage.clickQuickView(item);
    }

    @When("Add item to your basket")
    public void add_item_to_your_basket() {
        orderpage.addItemToBasket();


    }

    @When("View basket")
    public void view_basket() {
        summary = productspage.viewCart();
    }

    @Then("correct sizes should be selected")
    public void correct_sizes_should_be_selected() {
        Iterator it = mapProduct.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            //   System.out.println(pair.getKey() + " = " + ((Product)pair.getValue()).getSize().split(",")[1]);
            //  System.out.println(pair.getKey() + " = " + summary.getColorAndSize(pair.getKey().toString()).split(",")[1]);
            Assert.assertEquals(((Product) pair.getValue()).getSize().split(",")[1], summary.getColorAndSize(pair.getKey().toString()).split(",")[1]);

        }

    }

    @Then("Prices should be as  expected")
    public void prices_should_be_as_expected() {
        Iterator it = mapProduct.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            //  System.out.println(pair.getKey() + " = " + ((Product)pair.getValue()).getPrice());
            // System.out.println(pair.getKey() + " = " + summary.getUnitPrice(pair.getKey().toString()));
            Assert.assertEquals(((Product) pair.getValue()).getPrice(), summary.getUnitPrice(pair.getKey().toString()));


        }

    }

    @Then("Total Products should be the sum of the two items")
    public void total_Products_should_be_the_sum_of_the_two_items() {
        Iterator it = mapProduct.entrySet().iterator();
        Double unitprice = 0.00;
        int qty = 1;
        expectedTotalprice = 0.00;
        actualPrice = 0.00;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            unitprice = Double.parseDouble(((Product) pair.getValue()).getPrice().replace("$", ""));
            qty = Integer.parseInt(((Product) pair.getValue()).getQty().replace("$", ""));

            expectedTotalprice = Double.parseDouble(summary.getTotalProducts());
            //  System.out.println(pair.getKey() + " = " + Double.parseDouble(((Product)pair.getValue()).getPrice().replace("$","")));
            // System.out.println(pair.getKey() + " = " + Integer.parseInt(((Product)pair.getValue()).getQty().replace("$","")));
            //System.out.println(pair.getKey() + " = " + summary.getTotalProducts());

            actualPrice = actualPrice + unitprice * qty;
        }
        Assert.assertEquals(expectedTotalprice, actualPrice);

    }

    @Then("Total should be the sum of Total Products and Shopping")
    public void total_should_be_the_sum_of_Total_Products_and_Shopping() {
        Assert.assertEquals(Double.parseDouble(summary.getTotal().replace("$", "")), actualPrice + deliveryCost);
    }

    @Then("Complete Order by wire")
    public void complete_Order_by_wire() {
        summary.completeOrder();
    }


    @Given("User navigate to Order History")
    public void user_navigate_to_Order_History() {
        orderHistoryPage = loginpage.navigateToOrderHistoryPage();

    }

    @Given("Select  item {string} from Orders")
    public void select_item_from_Orders(String index) {

        orderdetailsPage = orderHistoryPage.clickOrderDetailsByIndex(index);
    }

    @When("Add a comment {string}")
    public void add_a_comment(String comment) {
        orderdetailsPage.addComment(comment);
        this.comment = comment;

    }

    @Then("Comment should appear in Messages")
    public void comment_should_appear_in_Messages() {
        orderdetailsPage.checkCommentAddedToMessages(comment);
    }

    @When("Select recent order")
    public void select_recent_order() {
        orderdetailsPage = orderHistoryPage.clickOrderDetailsByIndex("1");

    }


    @Then("Qty selected should be {int} for {string}")
    public void qty_selected_should_be_for(Integer expectedQty, String item) {

        Assert.assertEquals(orderdetailsPage.getQtyItem(item), expectedQty);
    }
}
