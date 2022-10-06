package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppCardDeliveryTestTask1 {

    String validCity = "Казань";
    String invalidCity = "Зеленодольск";

    String generateDate(int days, String dateFormat) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(dateFormat));
    }

    String validDate = generateDate(3, "dd.MM.yyyy");
    String invalidDate = generateDate(0, "dd");
    String validName = "Составная-Фамилия Имя";
    String invalidName = "Something";
    String validPhone = "+79998887766";
    String invalidPhone = "123456";
    int validDuration = 15;
    String msgFieldNotFilled = "Поле обязательно для заполнения";
    String msgInvalidCity = "Доставка в выбранный город недоступна";
    String msgInvalidDate = "Неверно введена дата";
    String msgInvalidName = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
    String msgInvalidPhone = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
    String msgSuccess = "Успешно! Встреча успешно забронирована на " + validDate;

    @BeforeEach
    void host() {
        open("http://localhost:9999/");
    }

    @Test
    void positiveTest() {
        $("[data-test-id='city'] input").setValue(validCity);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(validDate);
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(validDuration));
        $("[data-test-id='notification']").shouldHave(Condition.text(msgSuccess));
    }

    @Test
    void noCity() {
        host();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(validDate);
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $("[data-test-id='city'] .input__sub").shouldHave(Condition.exactText(msgFieldNotFilled));
    }

    @Test
    void inValidCity() {
        $("[data-test-id='city'] input").setValue(invalidCity);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(validDate);
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $("[data-test-id='city'] .input__sub").shouldHave(Condition.exactText(msgInvalidCity));
    }

    @Test
    void noDate() {
        $("[data-test-id='city'] input").setValue(validCity);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $("[data-test-id='date'] .input__sub").shouldHave(Condition.exactText(msgInvalidDate));
    }

    @Test
    void inValidDate() {
        $("[data-test-id='city'] input").setValue(validCity);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(invalidDate);
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $("[data-test-id='date'] .input__sub").shouldHave(Condition.exactText(msgInvalidDate));
    }

    @Test
    void noName() {
        $("[data-test-id='city'] input").setValue(validCity);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(validDate);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText(msgFieldNotFilled));
    }

    @Test
    void invalidName() {
        $("[data-test-id='city'] input").setValue(validCity);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(validDate);
        $("[data-test-id='name'] input").setValue(invalidName);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText(msgInvalidName));
    }

    @Test
    void noPhone() {
        $("[data-test-id='city'] input").setValue(validCity);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(validDate);
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText(msgFieldNotFilled));
    }

    @Test
    void invalidPhone() {
        $("[data-test-id='city'] input").setValue(validCity);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(validDate);
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='phone'] input").setValue(invalidPhone);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText(msgInvalidPhone));
    }

    @Test
    void noAgreement() {
        $("[data-test-id='city'] input").setValue(validCity);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(validDate);
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $(".button_view_extra").click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(visible);
    }
}
