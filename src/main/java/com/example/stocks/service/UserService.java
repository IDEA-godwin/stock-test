package com.example.stocks.service;

import com.example.stocks.model.User;
import com.example.stocks.repository.UserRepository;
import com.example.stocks.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManagerBuilder authBuilder;

    @Autowired
    private void setBeans(UserRepository userRepository, PasswordEncoder passwordEncoder,
                          AuthenticationManagerBuilder authBuilder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authBuilder = authBuilder;
    }

    public User registerUser(User user) {
        userRepository.findByEmailIgnoreCase(user.getEmail())
            .ifPresent(u -> {
                throw new EmailAlreadyExistException(String.format("User with email %s already exist", u.getEmail()));
            });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Map<String, Object> loginUser(String email, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email,
                password
            );
            authBuilder.getObject().authenticate(authenticationToken);
            String token = new TokenProvider().createToken(email);
            return loginResponse(token, userRepository.getUserByEmail(email));
        } catch (Exception ex) {
            throw new LoginCredentialsInvalidException("email or password incorrect");
        }
    }

    private Map<String, Object> loginResponse(String token, User user) {
        Map<String, Object> loginResponse = new HashMap<>();
        loginResponse.put("token", token);
        loginResponse.put("user", user);
        return loginResponse;
    }
}
