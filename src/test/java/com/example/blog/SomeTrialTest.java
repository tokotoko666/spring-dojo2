package com.example.blog;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

public class SomeTrialTest {

    @Test
    public void base64() {
        var uuid = "2471b8da-95d0-47f1-bbb6-fc3601d5ac86";
        var base64Bytes = Base64.getEncoder().encode(uuid.getBytes());
        var base64String = new String(base64Bytes);
        System.out.println(base64String);
        // MjQ3MWI4ZGEtOTVkMC00N2YxLWJiYjYtZmMzNjAxZDVhYzg2
        // MjQ3MWI4ZGEtOTVkMC00N2YxLWJiYjYtZmMzNjAxZDVhYzg2
    }

    @Test
    public void bcrypt() {
        var encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("password00"));
        System.out.println(encoder.encode("password00"));
        System.out.println(encoder.encode("password00"));
    }
}
