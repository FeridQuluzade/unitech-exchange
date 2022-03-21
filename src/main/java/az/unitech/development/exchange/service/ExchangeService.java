package az.unitech.development.exchange.service;

import az.unitech.development.exchange.dto.request.ExchangeRequest;
import az.unitech.development.exchange.dto.response.ExchangeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final CacheService cacheService;

    public ExchangeResponse calculate(ExchangeRequest exchangeRequest) {
        if (exchangeRequest.isFromCurrencyUSD()) {
            return ifFromCurrencyIsUSD(exchangeRequest);
        }

        if (exchangeRequest.isToCurrencyUSD()) {
            return ifToCurrencyIsUSD(exchangeRequest);
        }

        return ifFromCurrencyAndToCurrencyAnotherIsUSD(exchangeRequest);
    }

    private ExchangeResponse ifFromCurrencyIsUSD(ExchangeRequest request) {
        BigDecimal toCurrencyBaseUSD = mapToValueBaseUSD(request.getTo());
        return ExchangeResponse.of(toCurrencyBaseUSD.multiply(request.getAmount(),
                new MathContext(4, RoundingMode.HALF_UP)));
    }

    private ExchangeResponse ifToCurrencyIsUSD(ExchangeRequest request) {
        BigDecimal fromCurrencyBaseUSD = mapToValueBaseUSD(request.getFrom());
        return ExchangeResponse.of(
                request.getAmount().divide(fromCurrencyBaseUSD, 4, RoundingMode.HALF_UP));
    }

    private ExchangeResponse ifFromCurrencyAndToCurrencyAnotherIsUSD(ExchangeRequest request) {
        BigDecimal toUSD = request.getAmount().divide(
                mapToValueBaseUSD(request.getFrom()), 4, RoundingMode.HALF_UP);

        BigDecimal fromUSD = toUSD.multiply(mapToValueBaseUSD(request.getTo()),
                new MathContext(4, RoundingMode.HALF_UP));

        return ExchangeResponse.of(fromUSD);
    }

    private BigDecimal mapToValueBaseUSD(String currency) {
        return cacheService.mapCurrenciesBaseToUSD().get(currency);
    }

}