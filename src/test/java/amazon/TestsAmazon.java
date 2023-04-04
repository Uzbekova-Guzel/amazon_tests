package amazon;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static io.qameta.allure.Allure.step;

public class TestsAmazon extends TestBase {


    @DisplayName("Переход на главную страницу при клике на лого Amazon")
    @Test
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
    @Owner("UzbekovaGV")
    void amazonShouldContainAllOfButtonsForGiveLocaleTest(
            AmazonLocale amazonLocale,
            List<String> buttons
    ) {
        amazonWebPage.openPageAmazon()
                .changeLanguage(amazonLocale)
                .checkButtonsForGiveLocale(buttons);
    }


    @CsvFileSource(resources = "/amazonSearchAndFindProducts.csv")
    @ParameterizedTest(name = "При поиске по товару {0} появляются товары из раздела {1}")
    @Tag("web")
    @Owner("UzbekovaGV")
    void amazonSearchAndFindProductsTest(
            String Name,
            String Product
    ) {
        amazonWebPage.openPageAmazon()
                .searchProductName(Name)
                .checkProductDepartment(Product);
    }

    @ValueSource(
            strings = {"Electronics", "Computers"}
    )
    @ParameterizedTest(name = "Разделы каталога {0} больше 5")
    @Tag("web")
    @Owner("UzbekovaGV")
    void amazonCatalogsCountTest(
            String catalog
    ) {
        amazonWebPage.openPageAmazon()
                .openMainMenu()
                .openCatalog(catalog)
                .checkCatalogsItems()
                .closeMainMenu();
    }
}


