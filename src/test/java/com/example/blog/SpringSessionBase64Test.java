package com.example.blog;

import org.junit.jupiter.api.Test;

import java.util.Base64;

public class SpringSessionBase64Test {

    @Test
    public void test() {
        var uuid = "2471b8da-95d0-47f1-bbb6-fc3601d5ac86";
        var base64Bytes = Base64.getEncoder().encode(uuid.getBytes());
        var base64String = new String(base64Bytes);
        System.out.println(base64String);
        // MjQ3MWI4ZGEtOTVkMC00N2YxLWJiYjYtZmMzNjAxZDVhYzg2
        // MjQ3MWI4ZGEtOTVkMC00N2YxLWJiYjYtZmMzNjAxZDVhYzg2
    }
}
