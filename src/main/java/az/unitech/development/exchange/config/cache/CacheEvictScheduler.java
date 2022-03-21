package az.unitech.development.exchange.config.cache;

import az.unitech.development.exchange.config.properties.ApplicationConstants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheEvictScheduler {

    @Scheduled(fixedDelay = ApplicationConstants.TIME_UNIT_MINUTE)
    @CacheEvict(ApplicationConstants.CACHE_CURRENCY)
    public void evictCurrencyCache() {
    }

}