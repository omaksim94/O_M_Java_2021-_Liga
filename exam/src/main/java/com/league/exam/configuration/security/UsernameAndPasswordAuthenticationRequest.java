package com.league.exam.configuration.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsernameAndPasswordAuthenticationRequest {
    private String email;
    private String password;

    public UsernameAndPasswordAuthenticationRequest() {
    }
}
