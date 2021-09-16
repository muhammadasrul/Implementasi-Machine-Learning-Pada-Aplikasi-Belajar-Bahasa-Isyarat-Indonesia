package com.asrul.skripsi.ui.auth.login;

import static com.google.common.truth.Truth.assertThat;

import com.asrul.skripsi.TestUtils;

import org.junit.Test;

public class LoginActivityTest {

    @Test
    public void emailIsEmpty_returnFalse() {
        Boolean result = new TestUtils().loginCheck(
                "",
                "test123"
        );
        assertThat(result).isFalse();
    }

    @Test
    public void passwordIsEmpty_returnFalse() {
        Boolean result = new TestUtils().loginCheck(
                "asrulaji@gmail.com",
                ""
        );
        assertThat(result).isFalse();
    }

    @Test
    public void emailDoesNotExist_returnFalse() {
        Boolean result = new TestUtils().loginCheck(
                "otheremail@gmail.com",
                "test123"
        );
        assertThat(result).isFalse();
    }

    @Test
    public void wrongPassword_returnFalse() {
        Boolean result = new TestUtils().loginCheck(
                "asrul@gmail.com",
                "wrongpassword"
        );
        assertThat(result).isFalse();
    }

    @Test
    public void validInput_returnTrue() {
        Boolean result = new TestUtils().loginCheck(
                "asrulaji@gmail.com",
                "password123"
        );
        assertThat(result).isTrue();
    }
}