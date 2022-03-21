package az.unitech.development.exchange.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "az.unitech.development.exchange.client")
public class FeignClientConfiguration {
}