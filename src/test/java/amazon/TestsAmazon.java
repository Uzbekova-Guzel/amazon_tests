package amazon;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static io.qameta.allure.Allure.step;

public class TestsAmazon extends TestBase {

    @Test
    @DisplayName("Переход на главную страницу при клике на лого Amazon")
    @Tag("web")
    @Owner("UzbekovaGV")
    void goMainPageByLogo() {
        step("Открыть страницу 'https://www.amazon.com/'", () -> {
            amazonWebPage.openPageAmazon();
        });
        step("Перейти во вкладку 'Today's Deals'", () -> {
            amazonWebPage.todaysDealsPage();
        });
        step("Нажать на логотип Amazon в левом верхнем углу", () -> {
            amazonWebPage.logoAmazon();
        });
        step("Переход осуществился на главную страницу Amazon", () -> {
            amazonWebPage.verifyMainPage();
        });

    }


    @DisplayName("Отображение набора кнопок в верхней панели на испанском и на английском языках")
    @MethodSource("amazonShouldContainAllOfButtonsForGiveLocale")
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    @Tag("web")
    void amazonShouldContainAllOfButtonsForGiveLocaleTest(
            AmazonLocale amazonLocale,
            List<String> buttons
    ) {
        amazonWebPage.openPageAmazon()
                .changeLanguage(amazonLocale)
                .checkButtonsForGiveLocale(buttons);
    }
}



//    @CsvSource({
//            "xbox, Video Games",
//            "iphone 14, Cell Phones"
//    })
//    @ParameterizedTest(name = "При поиске по товару {0} появляются товары из раздела {1}")
//    @Tag("web")
//    void amazonSearchAndFindProductsTest(
//            String Name,
//            String Product
//    ) {
//        $("#twotabsearchtextbox").setValue(Name).pressEnter();
//        $("#departments").shouldHave(text(Product));
//    }
//
//
//    //Вариант теста amazonSearchAndFindProductsTest с применением CsvFileSource
//    @CsvFileSource(resources = "/amazonSearchAndFindProducts.csv")
//    @ParameterizedTest(name = "При поиске по товару {0} появляются товары из раздела {1}")
//    @Tag("web")
//    void amazonSearchAndFindProductsWithCsvFileTest(
//            String Name,
//            String Product
//    ) {
//        $("#twotabsearchtextbox").setValue(Name).pressEnter();
//        $("#departments").shouldHave(text(Product));
//    }
//
//    @ValueSource(
//            strings = {"Electronics", "Computers"}
//    )
//    @ParameterizedTest(name = "Разделы каталога {0} больше 5")
//    @Tag("web")
//    void amazonCatalogsCountTest(
//            String catalogs
//    ) {
//        $("#nav-hamburger-menu").click();
//        $("#hmenu-content").$(byText(catalogs)).click();
//        $$(".hmenu-item").shouldHave(CollectionCondition.sizeGreaterThan(5));
//        $(".hmenu-close-icon").click();
//    }

