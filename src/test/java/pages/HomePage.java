package pages;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class HomePage {
    Page page;
    String searchBox = "#twotabsearchtextbox";
    String searchBtn = "#nav-search-submit-button";

    public HomePage(Page page){
        this.page = page;
    }

    public void openAmazon(){
        page.navigate("https://www.amazon.in");
    }

    public void searchProduct(String product){

    page.fill(searchBox, product);

    page.click(searchBtn);

    page.waitForLoadState(LoadState.DOMCONTENTLOADED);

    page.waitForSelector("div.s-main-slot", 
        new Page.WaitForSelectorOptions().setTimeout(60000));

    page.waitForTimeout(2000);
}
}
