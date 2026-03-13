package pages;

import com.microsoft.playwright.Page;

public class ProductPage {

    Page page;

    public ProductPage(Page page){
        this.page = page;
    }

    public boolean isProductPageOpen(){

        page.waitForSelector("span#productTitle",
            new Page.WaitForSelectorOptions().setTimeout(60000));

        return page.locator("span#productTitle").isVisible();
    }

    public void selectQuantity(){

        page.selectOption("#quantity", "2");
    }

    public void addToCart(){

        page.click("#add-to-cart-button");
    }
}