package az.unitech.development.exchange.error.validation;

import az.unitech.development.exchange.dto.Currencies;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value))
            return false;

        try {
            Currencies.of(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return false;
        }

        return true;
    }

}
