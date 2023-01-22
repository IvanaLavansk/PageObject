package ru.netology.pageobject.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class PersonalAreaPage {

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement firstBalanceButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement secondBalanceButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");
    private final String idOne = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    private final String idTwo = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
    public static int CardBalance;
    public static String numberMax;

    public DashboardPage getMaxCardBalance() {
        if (getCardBalance(idOne) >= getCardBalance(idTwo)) {
            CardBalance = getCardBalance(idOne);
            numberMax = "first";
            secondBalanceButton.click();
        } else {
            CardBalance = getCardBalance(idTwo);
            numberMax = "second";
            firstBalanceButton.click();
        }
        return new DashboardPage();
    }


    public int getCardBalance(String id) {
        val text = cards.find(Condition.attribute("data-test-id", id)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
