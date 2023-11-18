package net.javaguides.springboot.springbootbackend.Request.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String accessToken;

}

