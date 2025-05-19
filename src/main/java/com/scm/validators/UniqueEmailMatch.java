//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.validators;

import com.scm.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailMatch implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UserService userService;

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !this.userService.existsByEmail(email);
    }
}
