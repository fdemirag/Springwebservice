package com.hoaxify.webservice.user;

import com.hoaxify.webservice.error.ApiError;
import com.hoaxify.webservice.shared.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    public GenericResponse save(@Validated @RequestBody User user){

        userService.save(user);
        return new GenericResponse("User Created");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception) {
        ApiError error = new ApiError(400,"validation error ", "/api/1.0/users");
        Map<String, String> validationErrors = new HashMap<>();
       for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
           validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
       }
       error.setValidationErrors(validationErrors);
        return error;
    }


}
