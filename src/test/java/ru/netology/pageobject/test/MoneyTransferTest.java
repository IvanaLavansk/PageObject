package ru.netology.pageobject.test;

import com.codeborne.selenide.Configuration;
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
        var loginPage = new LoginPageV2();                      //оставлено для примера  var loginPage = open("http://localhost:9999", LoginPageV3.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var personalPage = new PersonalAreaPage();
        personalPage.getMaxCardBalance();
        if (personalPage.getNumberMax().equals("first")) {
            var cardNumber = DataHelper.getCardNumberOne();
            var refillPage = new RefillPage();
            refillPage.getStartRefillPage(cardNumber);
        } else if (personalPage.getNumberMax().equals("second")) {
            var cardNumber = DataHelper.getCardNumberTwo();
            var refillPage = new RefillPage();
            refillPage.getStartRefillPage(cardNumber);
        }
        var personalPageNew = new PersonalAreaPage();
        if (PersonalAreaPage.getNumberMax().equals("first")) {
            int expected1 = PersonalAreaPage.getCardBalanceMax()-PersonalAreaPage.getDifferenceToReduce();
            int actual1 = personalPageNew.getCardBalanceFirst();
            assertEquals(expected1, actual1);
            int expected2 = PersonalAreaPage.getCardBalanceMin()+PersonalAreaPage.getDifferenceToReduce();
            int actual2 = personalPageNew.getCardBalanceSecond();
            assertEquals(expected2, actual2);
        } else if (PersonalAreaPage.getNumberMax().equals("second")) {
            int expected1 = PersonalAreaPage.getCardBalanceMax()-PersonalAreaPage.getDifferenceToReduce();
            int actual1 = personalPageNew.getCardBalanceSecond();
            assertEquals(expected1, actual1);
            int expected2 = PersonalAreaPage.getCardBalanceMin()+PersonalAreaPage.getDifferenceToReduce();
            int actual2 = personalPageNew.getCardBalanceFirst();
            assertEquals(expected2, actual2);
        }
    }

    @Test
    void shouldGetOtherAuth() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getOtherAuthInfo(DataHelper.getAuthInfo());
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldNotAuth() {
        var loginPage = new LoginPageError();
        loginPage.notValidLogin(DataHelper.getWrongAuthInfo(DataHelper.getAuthInfo()));
    }

    @Test
    void shouldNotVerification() {
        var loginPage = new LoginPageVerificationError();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPageError = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getWrongVerificationCodeFor(authInfo);
        verificationPageError.notValidVerify(verificationCode);
    }
}