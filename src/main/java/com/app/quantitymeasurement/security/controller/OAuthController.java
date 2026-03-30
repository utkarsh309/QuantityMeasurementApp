package com.app.quantitymeasurement.security.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurement.security.entity.UserEntity;
import com.app.quantitymeasurement.security.jwt.JwtUtil;
import com.app.quantitymeasurement.security.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class OAuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // OAuth Success API
    @GetMapping("/oauth-success")
    public Map<String, Object> success(@AuthenticationPrincipal OAuth2User user) {

        //  Extract user details from Google
        String email = user.getAttribute("email");
        String name = user.getAttribute("name");

        //  Check if user already exists in DB
        UserEntity existingUser = userRepository.findByUsername(email).orElse(null);

        if (existingUser == null) {

            //  Create new user for OAuth login
            UserEntity newUser = new UserEntity();
            newUser.setUsername(email);

            //  Always encode password (even dummy)
            newUser.setPassword(passwordEncoder.encode("oauth_user"));

            userRepository.save(newUser);
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(email);

        //  Return clean response
        return Map.of(
                "message", "Login successful",
                "name", name,
                "email", email,
                "token", token
        );
    }
}