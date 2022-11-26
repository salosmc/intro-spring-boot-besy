package ar.com.besy.microservices.futbolmanagercore.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CUITValidator implements ConstraintValidator<CUIT, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        //que empiece con 30 y que tenga 11 digitos
        //aca se pueden validar mas cosas y usar expresion regular
        return value.startsWith("30") && value.length()==11;

    }

}

