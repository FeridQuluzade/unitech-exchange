package az.unitech.development.exchange.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeResponse {

    private BigDecimal amount;

    public static ExchangeResponse of(BigDecimal amount) {
        return new ExchangeResponse(amount);
    }

}