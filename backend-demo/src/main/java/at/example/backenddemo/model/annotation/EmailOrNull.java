package at.example.backenddemo.model.annotation;

import at.example.backenddemo.model.validator.EmailOrNullValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailOrNullValidator.class)
public @interface EmailOrNull {
    String message() default "Invalid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
