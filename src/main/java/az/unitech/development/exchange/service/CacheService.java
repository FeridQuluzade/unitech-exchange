package az.unitech.development.exchange.service;

import az.unitech.development.exchange.client.CurrencyClient;
import az.unitech.development.exchange.client.model.CurrencyResponse;
import az.unitech.development.exchange.config.properties.ApplicationConstants;
import az.unitech.development.exchange.config.properties.CurrencyClientProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheService {

    private final CurrencyClient currencyClient;
    private final CurrencyClientProperties currencyClientProperties;

    @SneakyThrows
    @EventListener(classes = ApplicationStartedEvent.class)
    @Cacheable(ApplicationConstants.CACHE_CURRENCY)
    public Map<String, BigDecimal> mapCurrenciesBaseToUSD() {
        return getCurrenciesBaseToUSD().getResults();
    }

    private CurrencyResponse getCurrenciesBaseToUSD() {
        return currencyClient.getRatesBaseUSD(currencyClientProperties.getFastForex().getApiKey());
    }

}