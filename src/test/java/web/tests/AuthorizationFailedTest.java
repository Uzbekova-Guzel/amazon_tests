package web.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AuthorizationFailedTest {

    @Test
    @Tag("amazon")
    @Owner("Guzel Uzbekova")
    @DisplayName("Авторизация с некорректным email")
    void authFailWithEmailTest() {
        System.out.println("Test passed");
    }

}
