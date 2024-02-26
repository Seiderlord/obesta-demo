package at.example.backenddemo.model.validator;

import at.example.backenddemo.model.annotation.EmailOrNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailOrNullValidator implements ConstraintValidator<EmailOrNull, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Allow null values
        }

        return value.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
    }
}

