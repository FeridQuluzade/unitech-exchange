package az.unitech.development.exchange.client.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class CurrencyResponse {

    private String base;
    private Map<String, BigDecimal> results;

}