package com.example.blog.service;

public record UserEntity(
        long id,
        String username,
        String password,
        boolean enabled) {
}
