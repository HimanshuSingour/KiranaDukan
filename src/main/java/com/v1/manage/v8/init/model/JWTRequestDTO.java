package com.v1.manage.v8.init.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JWTRequestDTO {

    private String username;
    private String password;
}
