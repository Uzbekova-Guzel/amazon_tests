package web.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import web.pages.PageObjects;

public class TestBase {
    PageObjects pageObjects = new PageObjects();

//    @BeforeAll
//    static void beforeAll() {
//        WebDriverProvider provider = new WebDriverProvider();
//
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//    }

    @BeforeEach
    void clearCookies() {
        Selenide.clearBrowserCookies();
    }

    @AfterAll
    static void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
