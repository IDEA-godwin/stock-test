package com.example.stocks.resource;

import com.example.stocks.model.User;
import com.example.stocks.resource.vm.LoginVM;
import com.example.stocks.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "user", description = "User support for security")
public class UserResource {

    private UserService userService;

    @Autowired
    private void setBeans(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody LoginVM loginVM) {
        return ResponseEntity.ok(userService.loginUser(loginVM.getEmail(), loginVM.getPassword()));
    }

}
