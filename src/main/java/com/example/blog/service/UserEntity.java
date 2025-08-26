package com.example.blog.service;

public record UserEntity(
        String username,
        String password,
        boolean enabled) {
}
