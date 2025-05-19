//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {
    private static final long MAX_FILE_SIZE = 2097152L;

    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > 2097152L) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("File Should be less then 2 MB").addConstraintViolation();
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
