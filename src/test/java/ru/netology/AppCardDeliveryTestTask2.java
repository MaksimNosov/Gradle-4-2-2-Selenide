package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.Keys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTestTask2 {

    String validCity = "Казань";
    String firstTwoLettersOfCity = validCity.substring(0, 2);
    int dateDeliveryMoreCurrentDateByDays = 7;
    String validDate;
    String unitDateInDate = "27.10.2022";
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    Date unitDate = formatter.parse(unitDateInDate);
    String validName = "Составная-Фамилия Имя";
    String validPhone = "+79998887766";
    int validDuration = 15;
    String msgSuccess = "Успешно";
    String msgSuccessDate = "Встреча успешно забронирована на";

    public AppCardDeliveryTestTask2() throws ParseException {
    }

    String generateDate(int days, String dateFormat) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(dateFormat));
    }

    String validDateRequirements = generateDate(7, "dd.MM.yyyy");

    @Test
    void positiveTestUniversalDate() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(firstTwoLettersOfCity);
        $(".input__popup").find(byText(validCity)).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] .icon").click();
        int today = Integer.parseInt($(".calendar__day_state_today").getText());
        int lastDay = Integer.parseInt($$("[data-day]").last().getText());
        if (today <= lastDay - dateDeliveryMoreCurrentDateByDays) {
        validDate = LocalDate.now().plusDays(dateDeliveryMoreCurrentDateByDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } else {
            $("[data-step='1']").click();
            int difference = Integer.parseInt($$("[data-day]").last().getText()) - today + dateDeliveryMoreCurrentDateByDays;
        validDate = Integer.toString(Integer.parseInt($("[data-day]").getText()) + difference);
        }
        $("[data-test-id='date'] input").setValue(validDate);
        $("[data-test-id='name'] input").setValue(validName);
        $("[data-test-id='phone'] input").setValue(validPhone);
        $("[data-test-id='agreement']").click();
        $(".button_view_extra").click();
        $(withText(msgSuccess)).shouldBe(visible, Duration.ofSeconds(validDuration));
        $("[data-test-id='notification']").shouldHave(Condition.text(msgSuccessDate));
        $("[data-test-id='notification']").shouldHave(Condition.text(validDate));
        System.out.println(validDate);
    }

    @Test
    void positiveTestUnitDate() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(firstTwoLettersOfCity);
        $(".input__popup").find(byText(validCity)).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] .icon").click();
//        int today = Integer.parseInt(String.valueOf(unitDate));
        System.out.println(unitDate);
//        System.out.println(today);
//        int lastDay = Integer.parseInt($$("[data-day]").last().getText());
//        if (today <= lastDay - dateDeliveryMoreCurrentDateByDays) {
////            validDate = LocalDate.now().plusDays(dateDeliveryMoreCurrentDateByDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//            validDate = unitDate.plusDays(dateDeliveryMoreCurrentDateByDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        } else {
//            $("[data-step='1']").click();
//            int difference = Integer.parseInt($$("[data-day]").last().getText()) - today + dateDeliveryMoreCurrentDateByDays;
//            validDate = Integer.toString(Integer.parseInt($("[data-day]").getText()) + difference);
//        }
//        $("[data-test-id='date'] input").setValue(validDate);
//        $("[data-test-id='name'] input").setValue(validName);
//        $("[data-test-id='phone'] input").setValue(validPhone);
//        $("[data-test-id='agreement']").click();
//        $(".button_view_extra").click();
//        $(withText(msgSuccess)).shouldBe(visible, Duration.ofSeconds(validDuration));
//        $("[data-test-id='notification']").shouldHave(Condition.text(msgSuccessDate));
//        $("[data-test-id='notification']").shouldHave(Condition.text(validDate));
//        System.out.println(validDate);
    }

}
