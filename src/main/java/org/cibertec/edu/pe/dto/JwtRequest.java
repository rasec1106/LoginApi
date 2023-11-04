package org.cibertec.edu.pe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest { //LOGIN
    private String userName;
    private String userPassword;
}
