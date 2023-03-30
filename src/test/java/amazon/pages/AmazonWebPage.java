package amazon.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AmazonWebPage {
    private static final String MAIN_PAGE_TEXT = "Gaming accessories";
    private static final String TODAYS_DEALS_TEXT = "Today's Deals";

    public AmazonWebPage openPageAmazon() {
        open("https://www.amazon.com/");
        $(".gw-card-layout").shouldHave(text(MAIN_PAGE_TEXT));
        return this;
    }

    public AmazonWebPage todaysDealsPage() {
        $(".nav-swm-text-widget").click();
        $("#slot-2").shouldHave(text(TODAYS_DEALS_TEXT));
        return this;
    }

    public AmazonWebPage logoAmazon() {
        $("#nav-logo-sprites").click();
        return this;
    }

    public AmazonWebPage verifyMainPage() {
        $(".gw-card-layout").shouldHave(text(MAIN_PAGE_TEXT));
        return this;
    }



}

