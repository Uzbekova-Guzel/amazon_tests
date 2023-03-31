package amazon.pages;

import amazon.AmazonLocale;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AmazonWebPage {
    private static final String MAIN_PAGE_TEXT = "Gaming accessories";
    private static final String TODAYS_DEALS_TEXT = "Today's Deals";

    public AmazonWebPage openPageAmazon() {
        open("https://www.amazon.com/");
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

    public AmazonWebPage changeLanguage(AmazonLocale amazonLocale) {
        $(".icp-nav-link-inner").hover();
        $(byText(String.valueOf(amazonLocale))).click();
        return this;
    }

    public AmazonWebPage checkButtonsForGiveLocale(List<String> value) {
        $$(".nav-progressive-content a")
                .filter(visible)
                .shouldHave(texts(value));
        return this;
    }

    public AmazonWebPage searchProductName(String Name) {
        $("#twotabsearchtextbox").setValue(Name).pressEnter();
        return this;
    }

    public AmazonWebPage checkProductDepartment(String Product) {
        $("#departments").shouldHave(text(Product));
        return this;
    }


}

