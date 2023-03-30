package amazon;

import amazon.config.WebDriverProvider;
import amazon.pages.AmazonWebPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    AmazonWebPage amazonWebPage = new AmazonWebPage();

    @BeforeAll
    static void beforeAll() {
        WebDriverProvider provider = new WebDriverProvider();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.browserSize = "1920x1080";
        open("https://www.amazon.com/");
    }

    static Stream<Arguments> amazonShouldContainAllOfButtonsForGiveLocaleTest() {
        return Stream.of(

                Arguments.of(AmazonLocale.ES, List.of("Ofertas del Día", "Servicio al Cliente", "Listas", "Tarjetas de Regalo", "Vender")),
                Arguments.of(AmazonLocale.EN, List.of("Today's Deals", "Customer Service", "Registry", "Gift Cards", "Sell"))
        );
    }

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
