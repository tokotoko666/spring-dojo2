package com.example.blog;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

public class SomeTrialTest {

    @Test
    public void test() {
        var uuid = "a70368f7-0237-4684-9c96-ae7b81b0920a";
        var encoded64Bytes = Base64.getEncoder().encode(uuid.getBytes());
        var encoded64String = new String(encoded64Bytes);
        System.out.println(encoded64String);
        // YTcwMzY4ZjctMDIzNy00Njg0LTljOTYtYWU3YjgxYjA5MjBh
    }

    @Test
    public void bcrypt() {
        var encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("password"));
        System.out.println(encoder.encode("password"));
        System.out.println(encoder.encode("password"));
    }

}
