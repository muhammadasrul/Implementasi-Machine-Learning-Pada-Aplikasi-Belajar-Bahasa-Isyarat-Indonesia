package com.asrul.skripsi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestUtils {

    List<String> existingEmails() {
        List<String> email = new ArrayList<>();
        email.add("asrulaji@gmail.com");
        email.add("test@gmail.com");

        return email;
    }

    HashMap<String, String> emailPassword() {
        HashMap<String, String> emailPassword = new HashMap<>();
        emailPassword.put("asrulaji@gmail.com", "password123");
        emailPassword.put("test@gmail.com", "test123");

        return emailPassword;
    }

    public Boolean registerCheck(String name, String email, String password, String password2) {
        return !name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !(password.length() < 6) && password2.equals(password) && !existingEmails().contains(email);
    }

    public Boolean loginCheck(String email, String password) {
        return !email.isEmpty() && !password.isEmpty() && existingEmails().contains(email) && emailPassword().containsValue(password);
    }
}