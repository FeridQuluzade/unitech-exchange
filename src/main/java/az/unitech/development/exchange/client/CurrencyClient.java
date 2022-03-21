package az.unitech.development.exchange.client;

import az.unitech.development.exchange.client.model.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "fastforex",
        url = "${api.fastforex.io}",
        primary = false,
        contextId = "currency-rates"
)
public interface CurrencyClient {

    @GetMapping(value = "/fetch-all")
    CurrencyResponse getRatesBaseUSD(@RequestParam("api_key") String apiKey);

}
