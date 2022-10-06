package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTestTask2 {

    String validCity = "Казань";
    String firstTwoLettersOfCity = validCity.substring(0, 2);
    int dateDeliveryMoreCurrentDateByDays = 7;
    String validName = "Составная-Фамилия Имя";
    String validPhone = "+79998887766";
    int validDuration = 15;
    String msgSuccess = "Успешно! Встреча успешно забронирована на " + LocalDate.now().plusDays(dateDeliveryMoreCurrentDateByDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Test
    void positiveTestUniversalDate() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(firstTwoLettersOfCity);
        $(".input__popup").find(byText(validCity)).click();
        $("[data-test-id='date'] .icon").click();
        if (LocalDate.now().getDayOfMonth() > (LocalDate.MAX.getDayOfMonth() - dateDeliveryMoreCurrentDateByDays)) {
            $("[data-step='1']").click();
        }
        String date = Integer.toString(LocalDate.now().plusDays(dateDeliveryMoreCurrentDateByDays).getDayOfMonth());
        $(byText(date)).click();
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(validDuration));
        $("[data-test-id='notification']").shouldHave(Condition.text(msgSuccess));
    }
}
