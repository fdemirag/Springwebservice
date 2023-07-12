package com.hoaxify.webservice.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {

    UserRepository userRepository;

    public UniqueUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username , ConstraintValidatorContext context) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return false;
        }
        return true;
    }
}
