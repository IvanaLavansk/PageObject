package ru.netology.pageobject.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pageobject.data.DataHelper;
import ru.netology.pageobject.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    public static int CardValueSum;

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
        var cardNumber = DataHelper.getCardNumber();
        var dashboardPageNew = refillPage.getStartRefillPage(cardNumber);

        int expected1 = (CardValueSum + dashboardPageNew.getCardBalance(dashboardPageNew.getIdOne()) - dashboardPageNew.getCardBalance(dashboardPageNew.getIdTwo()))/2;
        int actual1 = dashboardPageNew.getCardBalance(dashboardPageNew.getIdOne());
        assertEquals(expected1, actual1);

        int expected2 = (CardValueSum - dashboardPageNew.getCardBalance(dashboardPageNew.getIdOne()) + dashboardPageNew.getCardBalance(dashboardPageNew.getIdTwo()))/2;
        int actual2 = dashboardPageNew.getCardBalance(dashboardPageNew.getIdTwo());
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