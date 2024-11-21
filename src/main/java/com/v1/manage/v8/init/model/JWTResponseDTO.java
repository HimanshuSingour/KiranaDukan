package com.v1.manage.v8.init.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JWTResponseDTO {
    
    private String jwtToken;
    private String userName;
}
