import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardTest {

    String generateDate(int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void shouldValidTest1(){
        String date = generateDate(3);
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        $("span[data-test-id='city'] input").setValue("Москва");
        $("span[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("span[data-test-id='date'] input").setValue(date);
        $("span[data-test-id='name'] input").setValue("Макаренко Алексей");
        $("span[data-test-id='phone'] input").setValue("+79876543210");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(withText("Встреча успешно забронирована")).shouldBe(visible, Duration.ofSeconds(16));
    }
}
