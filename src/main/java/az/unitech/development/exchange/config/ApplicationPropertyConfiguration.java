package az.unitech.development.exchange.config;

import az.unitech.development.exchange.config.properties.CurrencyClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CurrencyClientProperties.class)
public class ApplicationPropertyConfiguration {
}
