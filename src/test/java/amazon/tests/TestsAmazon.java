package amazon.tests;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestsAmazon extends TestBase {

    @DisplayName("Тесты на 'https://www.amazon.com/'")

    @MethodSource("amazonShouldContainAllOfButtonsForGiveLocaleTest")
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    @Tag("web")
    void amazonShouldContainAllOfButtonsForGiveLocaleTest(
            AmazonLocale amazonLocale,
            List<String> buttons
    ) {
        $(".icp-nav-link-inner").hover();
        $(byText(String.valueOf(amazonLocale))).click();
        $$(".nav-progressive-content a")
                .filter(visible)
                .shouldHave(texts(buttons));
    }

    @CsvSource({
            "xbox, Video Games",
            "iphone 14, Cell Phones"
    })
    @ParameterizedTest(name = "При поиске по товару {0} появляются товары из раздела {1}")
    @Tag("web")
    void amazonSearchAndFindProductsTest(
            String Name,
            String Product
    ) {
        $("#twotabsearchtextbox").setValue(Name).pressEnter();
        $("#departments").shouldHave(text(Product));
    }

    //Вариант теста amazonSearchAndFindProductsTest с применением CsvFileSource
    @CsvFileSource(resources = "/amazonSearchAndFindProducts.csv")
    @ParameterizedTest(name = "При поиске по товару {0} появляются товары из раздела {1}")
    @Tag("web")
    void amazonSearchAndFindProductsWithCsvFileTest(
            String Name,
            String Product
    ) {
        $("#twotabsearchtextbox").setValue(Name).pressEnter();
        $("#departments").shouldHave(text(Product));
    }

    @ValueSource(
            strings = {"Electronics", "Computers"}
    )
    @ParameterizedTest(name = "Разделы каталога {0} больше 5")
    @Tag("web")
    void amazonCatalogsCountTest(
            String catalogs
    ) {
        $("#nav-hamburger-menu").click();
        $("#hmenu-content").$(byText(catalogs)).click();
        $$(".hmenu-item").shouldHave(CollectionCondition.sizeGreaterThan(5));
        $(".hmenu-close-icon").click();
    }




}
