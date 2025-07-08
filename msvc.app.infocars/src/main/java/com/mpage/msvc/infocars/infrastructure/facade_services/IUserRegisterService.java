package com.mpage.msvc.infocars.infrastructure.facade_services;

import com.mpage.msvc.infocars.api.models.request.LoginRequest;
import com.mpage.msvc.infocars.api.models.request.RegisterRequest;
import com.mpage.msvc.infocars.api.models.request.TokenResponse;

public interface IUserRegisterService extends CrudService<RegisterRequest, TokenResponse,Long> {

    TokenResponse register(RegisterRequest requestInsertUser);
}
