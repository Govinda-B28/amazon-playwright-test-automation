package tests;

import base.BaseTest;
import pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import com.aventstack.extentreports.*;
import utils.ExtentManager;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.nio.file.Path;

public class AmazonTest extends BaseTest {

    HomePage home;
    SearchResultsPage search;

    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup(Method method){

        setUp();

        home = new HomePage(page);
        search = new SearchResultsPage(page);

        extent = ExtentManager.getInstance();

        test = extent.createTest(method.getName());

        test.info("Test execution started");
    }

    @Test
    public void verifyAmazonPrinterFlow(){

        test.info("Opening Amazon homepage");
        home.openAmazon();

        test.info("Searching for product: HP smart tank");
        home.searchProduct("HP smart tank");

        test.info("Verifying search results");

        Assert.assertTrue(search.resultsVisible());

        Page productTab;

        try {

            test.info("Selecting Smart Tank 589 printer");

            productTab = context.waitForPage(() -> {
                search.selectProduct();
            });

        } catch (Exception e) {

            test.info("Product opened in same tab");

            search.selectProduct();
            productTab = page;
        }

        test.info("Waiting for product page to load");

        productTab.waitForLoadState(LoadState.DOMCONTENTLOADED);

        productTab.waitForSelector("#productTitle",
                new Page.WaitForSelectorOptions().setTimeout(60000));

        ProductPage productPage = new ProductPage(productTab);
        CartPage cartPage = new CartPage(productTab);

        test.info("Verifying product page");

        Assert.assertTrue(productPage.isProductPageOpen());

        test.info("Selecting quantity = 2");

        productPage.selectQuantity();

        test.info("Adding product to cart");

        productPage.addToCart();

        test.info("Verifying Proceed to Buy button");

        Assert.assertTrue(cartPage.verifyProceedToBuy());

        test.pass("Amazon printer flow test completed successfully");
    }

    @AfterMethod
public void close(ITestResult result) throws Exception {

    if(result.getStatus() == ITestResult.FAILURE){

        takeScreenshot(result.getName());
        attachScreenshot();
    }
    Path videoPath = page.video().path();

    tearDown();

    attachVideo(videoPath);
}
}