package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
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
    String msgInvalidName = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
    String msgInvalidPhone = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
    String msgSuccess = "Успешно";
    String msgSuccessDate = "Встреча успешно забронирована на";


    @Test
    void positiveTest() {
        open("http://localhost:9999/");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] input").setValue(validCity);
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(validDate);
        form.$("[data-test-id='name'] input").setValue(validName);
        form.$("[data-test-id='phone'] input").setValue(validPhone);
        form.$("[data-test-id='agreement']").click();
        form.$(".button_view_extra").click();
        $(withText(msgSuccess)).shouldBe(visible, Duration.ofSeconds(validDuration));
        $("[data-test-id='notification']").shouldHave(Condition.text(msgSuccessDate));
        $("[data-test-id='notification']").shouldHave(Condition.text(validDate));
    }

    @Test
    void noCity() {
        open("http://localhost:9999/");
        SelenideElement form = $("#root");
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(validDate);
        form.$("[data-test-id='name'] input").setValue(validName);
        form.$("[data-test-id='phone'] input").setValue(validPhone);
        form.$("[data-test-id='agreement']").click();
        form.$(".button_view_extra").click();
        $("[data-test-id='city'] .input__sub").shouldHave(Condition.exactText(msgFieldNotFilled));
    }

    @Test
    void inValidCity() {
        open("http://localhost:9999/");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] input").setValue(invalidCity);
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(validDate);
        form.$("[data-test-id='name'] input").setValue(validName);
        form.$("[data-test-id='phone'] input").setValue(validPhone);
        form.$("[data-test-id='agreement']").click();
        form.$(".button_view_extra").click();
        $("[data-test-id='city'] .input__sub").shouldHave(Condition.exactText(msgInvalidCity));
    }

    @Test
    void noDate() {
        open("http://localhost:9999/");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] input").setValue(validCity);
        form.$("[data-test-id='name'] input").setValue(validName);
        form.$("[data-test-id='phone'] input").setValue(validPhone);
        form.$("[data-test-id='agreement']").click();
        form.$(".button_view_extra").click();
    }

    @Test
    void inValidDate() {
        open("http://localhost:9999/");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] input").setValue(validCity);
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(invalidDate);
        form.$("[data-test-id='name'] input").setValue(validName);
        form.$("[data-test-id='phone'] input").setValue(validPhone);
        form.$("[data-test-id='agreement']").click();
        form.$(".button_view_extra").click();
    }

    @Test
    void noName() {
        open("http://localhost:9999/");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] input").setValue(validCity);
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(validDate);
        form.$("[data-test-id='phone'] input").setValue(validPhone);
        form.$("[data-test-id='agreement']").click();
        form.$(".button_view_extra").click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText(msgFieldNotFilled));
    }

    @Test
    void invalidName() {
        open("http://localhost:9999/");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] input").setValue(validCity);
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(validDate);
        form.$("[data-test-id='name'] input").setValue(invalidName);
        form.$("[data-test-id='phone'] input").setValue(validPhone);
        form.$("[data-test-id='agreement']").click();
        form.$(".button_view_extra").click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText(msgInvalidName));
    }

    @Test
    void noPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] input").setValue(validCity);
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(validDate);
        form.$("[data-test-id='name'] input").setValue(validName);
        form.$("[data-test-id='agreement']").click();
        form.$(".button_view_extra").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText(msgFieldNotFilled));
    }

    @Test
    void invalidPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] input").setValue(validCity);
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(validDate);
        form.$("[data-test-id='name'] input").setValue(validName);
        form.$("[data-test-id='phone'] input").setValue(invalidPhone);
        form.$("[data-test-id='agreement']").click();
        form.$(".button_view_extra").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText(msgInvalidPhone));
    }

    @Test
    void noAgreement() {
        open("http://localhost:9999/");
        SelenideElement form = $("#root");
        form.$("[data-test-id='city'] input").setValue(validCity);
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(validDate);
        form.$("[data-test-id='name'] input").setValue(validName);
        form.$("[data-test-id='phone'] input").setValue(validPhone);
        form.$(".button_view_extra").click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(visible);
    }
}
