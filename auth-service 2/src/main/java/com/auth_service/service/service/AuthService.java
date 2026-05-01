package com.auth_service.service.service;

import com.auth_service.dto.request.LoginRequest;
import com.auth_service.dto.request.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    String login(LoginRequest request);
}