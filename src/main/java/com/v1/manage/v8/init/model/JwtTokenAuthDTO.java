package com.v1.manage.v8.init.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenAuthDTO {

    private String status;
    private JWTResponseDTO jwtResponseDTO;
}
