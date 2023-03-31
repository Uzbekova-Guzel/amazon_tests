package amazon;

import amazon.config.WebDriverConfig;
import amazon.config.WebDriverProvider;
import amazon.pages.AmazonWebPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class TestBase {
    AmazonWebPage amazonWebPage = new AmazonWebPage();
    private static WebDriverConfig config;


    @BeforeAll
    static void beforeAll() {

        WebDriverProvider provider = new WebDriverProvider();

    }

    @BeforeEach
    void addListener() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    static Stream<Arguments> amazonShouldContainAllOfButtonsForGiveLocale() {
        return Stream.of(

                Arguments.of(AmazonLocale.ES, List.of("Ofertas del Día", "Servicio al Cliente", "Listas", "Tarjetas de Regalo", "Vender")),
                Arguments.of(AmazonLocale.EN, List.of("Today's Deals", "Customer Service", "Registry", "Gift Cards", "Sell"))
        );
    }
}
