package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.dto.JwtRequest;
import org.cibertec.edu.pe.dto.JwtResponse;
import org.cibertec.edu.pe.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
	
    @Autowired
    private JwtService jwtService;
    
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    	JwtResponse response = new JwtResponse(null, null);
    	try {
    		response = jwtService.createJwtToken(jwtRequest);
    	}catch (Exception e) {
			response.setJwtToken(e.getMessage());
		}
        return response;
    }
}
