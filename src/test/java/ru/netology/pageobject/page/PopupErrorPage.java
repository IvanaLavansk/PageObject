package ru.netology.pageobject.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PopupErrorPage {
    private SelenideElement notification = $("[data-test-id=error-notification]");

    public PopupErrorPage() {
        notification.shouldBe(visible);
    }
}
