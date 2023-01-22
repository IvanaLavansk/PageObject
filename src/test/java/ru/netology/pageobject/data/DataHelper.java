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

    public static AuthInfo getWrongAuthInfo(AuthInfo original) {
        return new AuthInfo("fedor", "123qwer45");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static VerificationCode getWrongVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("54321");
    }

    @Value
    public static class CardNumber {
        private String code;
    }

    public static CardNumber getCardNumberOne() {
        return new CardNumber("5559 0000 0000 0001");
    }
    public static CardNumber getCardNumberTwo() {
        return new CardNumber("5559 0000 0000 0002");
    }
}