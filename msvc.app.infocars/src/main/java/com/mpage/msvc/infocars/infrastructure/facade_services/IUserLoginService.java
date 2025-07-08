package com.mpage.msvc.infocars.infrastructure.facade_services;

import com.mpage.msvc.infocars.api.models.request.LoginRequest;
import com.mpage.msvc.infocars.api.models.request.TokenResponse;

public interface IUserLoginService extends CrudService<LoginRequest, TokenResponse,Long>{
    TokenResponse login(LoginRequest request);
}
