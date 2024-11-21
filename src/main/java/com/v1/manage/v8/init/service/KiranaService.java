package com.v1.manage.v8.init.service;

import com.v1.manage.v8.init.entity.KiranaUser;
import com.v1.manage.v8.init.model.JwtTokenAuthDTO;

public interface KiranaService {

    KiranaUser register(KiranaUser kiranaUser);

    JwtTokenAuthDTO login(String username, String password);

    boolean verifyOtp(String userId, String otpInput);

    KiranaUser regenerateOtp(String userId);
}
