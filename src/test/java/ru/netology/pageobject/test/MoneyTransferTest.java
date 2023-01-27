package ru.netology.pageobject.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pageobject.data.DataHelper;
import ru.netology.pageobject.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var refillPage = dashboardPage.getCardBalanceButton();
        var cardNumber = DataHelper.getCardNumber(refillPage.lookingForCardNumber());
        var dashboardPageNew = refillPage.getStartRefillPage(cardNumber);

        int expected1 = dashboardPageNew.mustBeBalanceOne();
        int actual1 = dashboardPageNew.getCardBalanceFirst();
        assertEquals(expected1, actual1);

        int expected2 = dashboardPageNew.mustBeBalanceTwo();
        int actual2 = dashboardPageNew.getCardBalanceSecond();
        assertEquals(expected2, actual2);
    }

    @Test
    void shouldGetOtherAuth() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getOtherAuthInfo(DataHelper.getAuthInfo());
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

}