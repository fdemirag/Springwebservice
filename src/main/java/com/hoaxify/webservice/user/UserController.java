package com.hoaxify.webservice.user;

import com.hoaxify.webservice.shared.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    private static final Logger Log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@CrossOrigin proxy kullandığımız için gerek kalmadı
    @PostMapping("/api/1.0/users")
    public GenericResponse save(@RequestBody User user){
        userService.save(user);
        return new GenericResponse("User Created");

    }
}
