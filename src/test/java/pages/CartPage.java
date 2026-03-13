package pages;

import com.microsoft.playwright.Page;

public class CartPage {

    Page page;

    public CartPage(Page page){
        this.page = page;
    }

    public boolean verifyProceedToBuy(){
        page.waitForSelector("text=Proceed to Buy (2 items)",
                new Page.WaitForSelectorOptions().setTimeout(60000));

        return page.locator("text=Proceed to Buy").isVisible();
    }
}