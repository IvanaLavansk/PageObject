package ru.netology.pageobject.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.pageobject.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class RefillPage {

    private SelenideElement transferAmount = $("[data-test-id=amount] input");
    private SelenideElement cardNumberInput = $("[data-test-id=from] input");
    private SelenideElement confirmButton = $("[data-test-id=action-transfer]");

    public DashboardPage getStartRefillPage(DataHelper.CardNumber code) {
        transferAmount.setValue(String.valueOf(PersonalAreaPage.CardBalance-200));
        cardNumberInput.setValue(code.getCode());
        confirmButton.click();
        return new DashboardPage();
    }

}
