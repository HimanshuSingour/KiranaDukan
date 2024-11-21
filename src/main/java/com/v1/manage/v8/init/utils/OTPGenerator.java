package com.v1.manage.v8.init.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OTPGenerator {

    public String generateOTP() {

        String prefix = "DM-";
        Random random = new Random();
        int otp = random.nextInt(900000) + 100000;
        return prefix + otp;
    }
}
