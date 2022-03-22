package az.unitech.development.exchange.dto.request;

import az.unitech.development.exchange.error.validation.Currency;
import az.unitech.development.exchange.error.validation.ErrorMessages;
import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class ExchangeRequest {

    @Currency
    private String from;

    @Positive(message = ErrorMessages.INVALID_AMOUNT)
    private BigDecimal amount;

    @Currency
    private String to;

    public boolean isFromCurrencyUSD() {
        return "USD".equals(from);
    }

    public boolean isToCurrencyUSD() {
        return "USD".equals(to);
    }

    public void setFrom(String from) {
        this.from = from.toUpperCase();
    }

    public void setTo(String to) {
        this.to = to.toUpperCase();
    }

}