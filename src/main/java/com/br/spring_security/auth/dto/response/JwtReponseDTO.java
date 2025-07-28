package com.br.spring_security.auth.dto.response;

import lombok.Builder;

@Builder
public record JwtReponseDTO(String token,
                            String username,
                            String tokenType)
{
    public JwtReponseDTO(String token, String username, String tokenType) {
        this.token = token;
        this.username = username;
        this.tokenType = tokenType;
    }
}
