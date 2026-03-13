package base;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import io.qameta.allure.Attachment;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    public void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(50)
        );

        context = browser.newContext(
            new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/"))
                .setRecordVideoSize(1440, 920)
        );

        page = context.newPage();

        page.setDefaultTimeout(30000);

        page.setDefaultNavigationTimeout(60000);

        page.navigate("https://www.amazon.in");

        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void takeScreenshot(String testName) {

        page.screenshot(
            new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/" + testName + ".png"))
                .setFullPage(true)
        );
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot() {

        return page.screenshot(
            new Page.ScreenshotOptions().setFullPage(true)
        );
    }

    @Attachment(value = "Execution Video", type = "video/webm")
    public byte[] attachVideo(Path videoPath) throws IOException {

        return Files.readAllBytes(videoPath);
    }

    public void tearDown() {

        if (page != null) {
            page.close();
        }

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}