package com.belkhiria.mystore.services;


import com.belkhiria.mystore.Repositories.RoleRepository;
import com.belkhiria.mystore.Repositories.UserRepository;
import com.belkhiria.mystore.dtos.AuthenticationRequest;
import com.belkhiria.mystore.dtos.AuthenticationResponse;
import com.belkhiria.mystore.dtos.RegisterRequest;
import com.belkhiria.mystore.models.Role;
import com.belkhiria.mystore.models.RoleName;
import com.belkhiria.mystore.models.User;
import com.belkhiria.mystore.security.JwtService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostConstruct
    public void init() {
        if (roleRepository.findByName(RoleName.USER).isEmpty()) {
            roleRepository.save(new Role(null, RoleName.USER, null));
        }

        if (roleRepository.findByName(RoleName.ADMIN).isEmpty()) {
            roleRepository.save(new Role(null, RoleName.ADMIN, null));
        }
    }
    public void register(RegisterRequest request){

        var userRole = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new IllegalStateException("User role not found"));

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(true)
                .role(userRole)
                .build();
        userRepository.save(user);

    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("firstname", user.getFirstname());
        var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

}

