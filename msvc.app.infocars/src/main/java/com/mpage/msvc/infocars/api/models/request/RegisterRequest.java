package com.mpage.msvc.infocars.api.models.request;

public record RegisterRequest(
        String username,
        String password,
        String email
) {
}
