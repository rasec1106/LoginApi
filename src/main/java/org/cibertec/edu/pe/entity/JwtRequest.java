package org.cibertec.edu.pe.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest { //LOGIN
    private String userName;
    private String userPassword;
}
