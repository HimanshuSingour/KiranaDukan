package com.v1.manage.v8.init.controller;

import com.v1.manage.v8.init.entity.KiranaUser;
import com.v1.manage.v8.init.model.JWTRequestDTO;
import com.v1.manage.v8.init.model.JwtTokenAuthDTO;
import com.v1.manage.v8.init.model.verify.RegenerateOtpRequest;
import com.v1.manage.v8.init.model.verify.VerifyOtpRequest;
import com.v1.manage.v8.init.service.KiranaService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DukaanController {

    private static final Logger logger = LoggerFactory.getLogger(DukaanController.class);

    @Autowired
    private KiranaService kiranaService;

    @CrossOrigin(origins = "*")
    @Operation(summary = "Create Account", description = "Endpoint to create an account.")
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<KiranaUser> saveUser(@RequestBody KiranaUser kiranaUser) {
        KiranaUser register = kiranaService.register(kiranaUser);
        return new ResponseEntity<>(register, HttpStatus.ACCEPTED);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
        boolean isVerified = kiranaService.verifyOtp(verifyOtpRequest.getUserId(), verifyOtpRequest.getOtp());
        if (isVerified) {
            return ResponseEntity.ok("OTP verified successfully. User is now verified.");
        } else {
            return ResponseEntity.status(400).body("Invalid or expired OTP.");
        }
    }

    @PostMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestBody RegenerateOtpRequest regenerateOtpRequest) {
        KiranaUser updatedUser = kiranaService.regenerateOtp(regenerateOtpRequest.getUserId());
        if (updatedUser != null) {
            return ResponseEntity.ok("New OTP sent to email.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }



    @Operation(summary = "User Login", description = "Authenticate user and generate JWT token.")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody JWTRequestDTO jwtRequestDTO) {
        JwtTokenAuthDTO response = kiranaService.login(jwtRequestDTO.getUsername
                (), jwtRequestDTO.getPassword());
        String jsonResponse = "{\"status\":\"" + response.getStatus() + "\", \"jwtResponseDTO\": {\"jwtToken\":\"" + response.getJwtResponseDTO().getJwtToken() + "\", \"userName\":\"" + response.getJwtResponseDTO().getUserName() + "\"}}";
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

}
