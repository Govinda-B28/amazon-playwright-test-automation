package pages;

import com.microsoft.playwright.Page;

public class SearchResultsPage {

    Page page;

    public SearchResultsPage(Page page){
        this.page = page;
    }

    public boolean resultsVisible(){

        page.waitForSelector("div.s-main-slot",
            new Page.WaitForSelectorOptions().setTimeout(60000));

        return page.locator("div.s-main-slot").isVisible();
    }

    public void selectProduct(){

    page.waitForSelector("div.s-main-slot");

    page.locator("//div[@data-component-type='s-search-result']//h2//span[contains(text(),'Smart Tank 589')]")
        .first()
        .click();
}
}
