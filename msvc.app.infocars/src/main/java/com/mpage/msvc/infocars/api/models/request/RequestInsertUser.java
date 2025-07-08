package com.mpage.msvc.infocars.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RequestInsertUser {
    private String username;
    private String password;
    private String email;
}
