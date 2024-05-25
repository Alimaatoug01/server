package com.siwar.API_pointeuse.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordEmailRequest {

    @NotBlank
    @Email
    private String forgotPasswordEmail;


    private String newPassword;
    private String token;
}
