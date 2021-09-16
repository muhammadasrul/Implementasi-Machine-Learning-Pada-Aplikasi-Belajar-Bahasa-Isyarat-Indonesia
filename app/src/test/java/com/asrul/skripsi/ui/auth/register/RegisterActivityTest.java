package com.asrul.skripsi.ui.auth.register;

import static com.google.common.truth.Truth.assertThat;

import com.asrul.skripsi.TestUtils;

import org.junit.Test;

public class RegisterActivityTest {

    @Test
    public void validInput_returnTrue() {
        Boolean result = new TestUtils().registerCheck(
                "Asrul",
                "asrul@gmail.com",
                "password123",
                "password123"
        );
        assertThat(result).isTrue();
    }

    @Test
    public void nameIsEmpty_returnFalse() {
        Boolean result = new TestUtils().registerCheck(
                "",
                "asrul@gmail.com",
                "password123",
                "password123"
        );
        assertThat(result).isFalse();
    }

    @Test
    public void emailIsEmpty_returnFalse() {
        Boolean result = new TestUtils().registerCheck(
                "Asrul",
                "",
                "password123",
                "password123"
        );
        assertThat(result).isFalse();
    }

    @Test
    public void passwordIsEmpty_returnFalse() {
        Boolean result = new TestUtils().registerCheck(
                "Asrul",
                "asrul@gmail.com",
                "",
                ""
        );
        assertThat(result).isFalse();
    }

    @Test
    public void passwordLessThan6Digit_returnFalse() {
        Boolean result = new TestUtils().registerCheck(
                "Asrul",
                "asrul@gmail.com",
                "pass",
                "pass"
        );
        assertThat(result).isFalse();
    }

    @Test
    public void passwordAndPasswordConfirmDoesNotMatch_returnFalse() {
        Boolean result = new TestUtils().registerCheck(
                "Asrul",
                "asrul@gmail.com",
                "password123",
                "test123"
        );
        assertThat(result).isFalse();
    }

    @Test
    public void emailAlreadyExists_returnFalse() {
        Boolean result = new TestUtils().registerCheck(
                "Asrul",
                "asrulaji@gmail.com",
                "password123",
                "password123"
        );
        assertThat(result).isFalse();
    }
}