package ru.netology.pageobject.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.pageobject.data.DataHelper;
import static com.codeborne.selenide.Selenide.$;

public class RefillPage {

    private SelenideElement transferAmount = $("[data-test-id=amount] input");
    private SelenideElement cardNumberInput = $("[data-test-id=from] input");
    private SelenideElement confirmButton = $("[data-test-id=action-transfer]");

    public DashboardPage getStartRefillPage(DataHelper.CardNumber code) {
        transferAmount.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        transferAmount.setValue(String.valueOf(PersonalAreaPage.getDifferenceToReduce()));
        cardNumberInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        cardNumberInput.setValue(code.getCode());
        confirmButton.click();
        return new DashboardPage();
    }

}
