package az.unitech.development.exchange.service;

import az.unitech.development.exchange.client.CurrencyClient;
import az.unitech.development.exchange.client.model.CurrencyResponse;
import az.unitech.development.exchange.config.properties.CurrencyClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CacheServiceTest {

    @Mock
    private CurrencyClient currencyClient;

    @Mock
    private CurrencyClientProperties currencyClientProperties;

    @InjectMocks
    private CacheService cacheService;

    @Test
    void mapCurrenciesBaseToUSD_ShouldReturnCurrencies() {
        final var apiKey = "asfhjkahdfj";
        var fastForex = new CurrencyClientProperties.FastForex();
        fastForex.setApiKey(apiKey);
        Map<String, BigDecimal> expected = Map.of("AZN", BigDecimal.valueOf(1.7));
        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.setResults(expected);

        when(currencyClientProperties.getFastForex()).thenReturn(fastForex);
        when(currencyClient.getRatesBaseUSD(apiKey)).thenReturn(currencyResponse);

        var actual = cacheService.mapCurrenciesBaseToUSD();

        assertEquals(expected, actual);
        verify(currencyClientProperties, times(1)).getFastForex();
        verify(currencyClient, times(1)).getRatesBaseUSD(apiKey);
    }

}