package org.cibertec.edu.pe.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.cibertec.edu.pe.dao.UserRepository;
import org.cibertec.edu.pe.dto.JwtRequest;
import org.cibertec.edu.pe.dto.JwtResponse;
import org.cibertec.edu.pe.entity.User;
import org.cibertec.edu.pe.service.JwtService;
import org.cibertec.edu.pe.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService{
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        final UserDetails userDetails = loadUserByUsername(userName);

        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userRepository.findByUserName(userName).get();

        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).get();

        if(user != null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthorities(user)
            );
        } else {
            throw new UsernameNotFoundException("Username is not valid");
        }
    }
    
    @Override
    public Set<SimpleGrantedAuthority> getAuthorities(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    @Override
    public void authenticate(String userName, String userPassword) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e){
            throw new Exception("User is disabled");
        } catch (BadCredentialsException e){
            throw new Exception("Bad credentials from user");
        }
    }
}
