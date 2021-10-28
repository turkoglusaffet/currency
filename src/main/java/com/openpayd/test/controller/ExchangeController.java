package com.openpayd.test.controller;

import com.openpayd.test.controller.resource.ExchangeResource;
import com.openpayd.test.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping(value = "/exchange",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeResource> getExchangeRate(@RequestParam String from,@RequestParam String to){

        return ResponseEntity.ok().body(exchangeService.getExchangeRate(from,to));

    }
}
