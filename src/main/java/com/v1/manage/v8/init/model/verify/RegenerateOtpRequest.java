package com.v1.manage.v8.init.model.verify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegenerateOtpRequest {

    private String userId;
}
