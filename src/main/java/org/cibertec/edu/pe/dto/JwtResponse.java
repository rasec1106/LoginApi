package org.cibertec.edu.pe.dto;

import org.cibertec.edu.pe.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {//login respuesta que me da al logearme
    private User user;
    private String jwtToken;
}
