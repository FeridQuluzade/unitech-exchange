package az.unitech.development.exchange.service;

import az.unitech.development.exchange.dto.request.ExchangeRequest;
import az.unitech.development.exchange.dto.response.ExchangeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeServiceTest {

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private ExchangeService exchangeService;

    @Test
    void calculate_WhenFromCurrencyIsUSD() {
        Map<String, BigDecimal> currencyMap = Map.of("AZN", BigDecimal.valueOf(1.70192));

        var exchangeRequest = new ExchangeRequest();
        exchangeRequest.setFrom("USD");
        exchangeRequest.setTo("AZN");
        exchangeRequest.setAmount(BigDecimal.valueOf(1));

        var toUSD = currencyMap.get(exchangeRequest.getTo());
        var expected = ExchangeResponse.of(
                toUSD.multiply(exchangeRequest.getAmount(),
                        new MathContext(4, RoundingMode.HALF_UP)));

        when(cacheService.mapCurrenciesBaseToUSD()).thenReturn(currencyMap);

        var actual = exchangeService.calculate(exchangeRequest);

        assertEquals(expected, actual);
        verify(cacheService, times(1)).mapCurrenciesBaseToUSD();
    }

    @Test
    void calculate_WhenToCurrencyIsUSD() {
        Map<String, BigDecimal> currencyMap = Map.of("AZN", BigDecimal.valueOf(1.70192));

        var exchangeRequest = new ExchangeRequest();
        exchangeRequest.setFrom("AZN");
        exchangeRequest.setTo("USD");
        exchangeRequest.setAmount(BigDecimal.valueOf(1));

        var fromUSD = currencyMap.get(exchangeRequest.getFrom());
        var expected = ExchangeResponse.of(
                exchangeRequest.getAmount().divide(fromUSD, 4, RoundingMode.HALF_UP));

        when(cacheService.mapCurrenciesBaseToUSD()).thenReturn(currencyMap);

        var actual = exchangeService.calculate(exchangeRequest);

        assertEquals(expected, actual);
        verify(cacheService, times(1)).mapCurrenciesBaseToUSD();
    }

    @Test
    void calculate_WhenFromCurrencyAndToCurrencyAnotherIsUSD() {
        Map<String, BigDecimal> currencyMap =
                Map.of("AZN", BigDecimal.valueOf(1.70221),
                        "TRY", BigDecimal.valueOf(14.83242));

        var exchangeRequest = new ExchangeRequest();
        exchangeRequest.setFrom("TRY");
        exchangeRequest.setTo("AZN");
        exchangeRequest.setAmount(BigDecimal.valueOf(100));

        var toUSD =
                exchangeRequest.getAmount().divide(
                        currencyMap.get(exchangeRequest.getFrom()), 4, RoundingMode.HALF_UP);

        var fromUSD = toUSD.multiply(currencyMap.get(exchangeRequest.getTo()),
                new MathContext(4, RoundingMode.HALF_UP));

        var expected = ExchangeResponse.of(fromUSD);

        when(cacheService.mapCurrenciesBaseToUSD()).thenReturn(currencyMap);

        var actual = exchangeService.calculate(exchangeRequest);

        assertEquals(expected, actual);
        verify(cacheService, times(2)).mapCurrenciesBaseToUSD();
    }

}