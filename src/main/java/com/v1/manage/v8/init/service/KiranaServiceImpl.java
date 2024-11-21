package com.v1.manage.v8.init.service;

import com.v1.manage.v8.init.entity.KiranaUser;

import com.v1.manage.v8.init.security.filters.JwtMethodsHelper;
import com.v1.manage.v8.init.model.JWTResponseDTO;
import com.v1.manage.v8.init.model.JwtTokenAuthDTO;
import com.v1.manage.v8.init.repositories.KiranaUserRepository;
import com.v1.manage.v8.init.utils.OTPGenerator;
import com.v1.manage.v8.init.utils.OtpSender;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class KiranaServiceImpl implements KiranaService {

    @Autowired
    private KiranaUserRepository kiranaUserRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtMethodsHelper helper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private OTPGenerator otpGenerator;

    @Autowired
    private OtpSender otpSender;


    @Scheduled(fixedRate = 700000)
    public void checkOtpExpirationAndDelete() {
        List<KiranaUser> users = kiranaUserRepository.findAll();
        for (KiranaUser user : users) {
            if (!user.isVerified()) {
                kiranaUserRepository.delete(user);
            }
        }
    }

    @Override
    public KiranaUser register(KiranaUser kiranaUser) {
        KiranaUser savingUser = new KiranaUser();
        savingUser.setEmail(kiranaUser.getEmail());
        savingUser.setUserId(UUID.randomUUID().toString());
        savingUser.setUsername(kiranaUser.getUsername());
        savingUser.setPassword(passwordEncoder.encode(kiranaUser.getPassword()));

        String otp = otpGenerator.generateOTP();
        savingUser.setOtp(otp);
        otpSender.sendOtp(savingUser.getEmail(), otp);
        return kiranaUserRepository.save(savingUser);
    }

    @Override
    public boolean verifyOtp(String userId, String otpInput) {
        KiranaUser user = kiranaUserRepository.findById(userId).orElse(null);
        if (user != null && Objects.equals(user.getOtp(), otpInput)) {
            user.setVerified(true);
            kiranaUserRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public KiranaUser regenerateOtp(String userId) {
        KiranaUser user = kiranaUserRepository.findById(userId).orElse(null);
        if (user != null) {
            String newOtp = otpGenerator.generateOTP();
            user.setOtp(newOtp);
            otpSender.sendOtp(user.getEmail(), newOtp);
            kiranaUserRepository.save(user);
            return user;
        }
        return null;
    }

    @Override
    public JwtTokenAuthDTO login(String username, String password) {
        KiranaUser user = kiranaUserRepository.findByUserName(username);
        if (user == null) {
            throw new BadCredentialsException("Invalid Username or Password");
        }
        UserDetails userDetails = authenticateUser(username, password);
        String token = generateToken(userDetails);
        JWTResponseDTO responseDTO = JWTResponseDTO.builder().jwtToken(token).userName(userDetails.getUsername()).build();

        JwtTokenAuthDTO jwtTokenAuthDTO = new JwtTokenAuthDTO();
        jwtTokenAuthDTO.setStatus(responseDTO != null ? "Success" : "Failed");
        jwtTokenAuthDTO.setJwtResponseDTO(responseDTO);
        return jwtTokenAuthDTO;
    }

    private UserDetails authenticateUser(String username, String password) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return userDetailsService.loadUserByUsername(username);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }

    private String generateToken(UserDetails userDetails) {
        return helper.generateToken(userDetails);
    }
}
