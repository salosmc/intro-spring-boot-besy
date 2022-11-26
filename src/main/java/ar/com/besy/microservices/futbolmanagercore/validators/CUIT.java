package ar.com.besy.microservices.futbolmanagercore.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CUITValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CUIT {
    String message() default "Invalid CUIT Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

