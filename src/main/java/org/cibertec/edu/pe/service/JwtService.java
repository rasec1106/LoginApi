package org.cibertec.edu.pe.service;

import org.cibertec.edu.pe.dto.JwtRequest;
import org.cibertec.edu.pe.dto.JwtResponse;
import org.cibertec.edu.pe.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

public interface JwtService extends UserDetailsService {
    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    public Set<SimpleGrantedAuthority> getAuthorities(User user);
    public void authenticate(String userName, String userPassword) throws Exception;
}
