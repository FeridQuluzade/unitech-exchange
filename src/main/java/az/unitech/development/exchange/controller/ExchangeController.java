package az.unitech.development.exchange.controller;

import az.unitech.development.exchange.dto.request.ExchangeRequest;
import az.unitech.development.exchange.dto.response.ExchangeResponse;
import az.unitech.development.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("/calculate")
    public ExchangeResponse calculate(@Valid ExchangeRequest exchangeRequest) {
        return exchangeService.calculate(exchangeRequest);
    }

}
