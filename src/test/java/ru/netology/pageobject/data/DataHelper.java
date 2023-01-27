package ru.netology.pageobject.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber {
        private String cardCode;
    }

    public static CardNumber getCardNumberOne() {
        return new CardNumber("5559 0000 0000 0001");
    }

    public static CardNumber getCardNumberTwo() {
        return new CardNumber("5559 0000 0000 0002");
    }

    public static CardNumber getCardNumber(String cardCode) {
        if (cardCode.contains("0001")) {
            cardCode = "5559 0000 0000 0002";
        } else if (cardCode.contains("0002")) {
            cardCode = "5559 0000 0000 0001";
        }
        return new CardNumber(cardCode);
    }

    public static int CardValueSum;

    public static int DifferenceToReduce = 200;
}