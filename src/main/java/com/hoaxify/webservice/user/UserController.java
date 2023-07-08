package com.hoaxify.webservice.user;

import com.hoaxify.webservice.error.ApiError;
import com.hoaxify.webservice.shared.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class UserController {
    private static final Logger Log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@CrossOrigin proxy kullandığımız için gerek kalmadı
    @PostMapping("/api/1.0/users")
    public ResponseEntity<?> save(@RequestBody User user){
        String username= user.getUsername();
        if (username ==null || username.isEmpty()) {
            ApiError error = new ApiError(400,"validation error ", "/api/1.0/users");
            Map<String, String> validationErrors = new HashMap<>();
            validationErrors.put("username","username cannot be null!");
            error.setValidationErrors(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        userService.save(user);
        return  ResponseEntity.ok(new GenericResponse("User Created"));

    }
}
