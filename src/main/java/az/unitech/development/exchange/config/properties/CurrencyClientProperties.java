package az.unitech.development.exchange.config.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("api")
public class CurrencyClientProperties {

    public FastForex fastForex = new FastForex();

    @Data
    public static class FastForex {
        private String apiKey;
    }

}