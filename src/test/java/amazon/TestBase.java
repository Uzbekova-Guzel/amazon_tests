package amazon;

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

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    AmazonWebPage amazonWebPage = new AmazonWebPage();

    @BeforeAll
    static void setUp() {
        WebDriverProvider.setConfig();
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


    @AfterEach
    void close() {
        closeWebDriver();
    }

    static Stream<Arguments> amazonShouldContainAllOfButtonsForGiveLocale() {
        return Stream.of(

                Arguments.of(AmazonLocale.ES, List.of("Ofertas del Día", "Servicio al Cliente", "Listas", "Tarjetas de Regalo", "Vender")),
                Arguments.of(AmazonLocale.EN, List.of("Today's Deals", "Customer Service", "Registry", "Gift Cards", "Sell"))
        );
    }
}
