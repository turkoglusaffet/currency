package com.openpayd.test.controller;

import com.openpayd.test.controller.resource.ConversionResource;
import com.openpayd.test.controller.resource.ExchangeResource;
import com.openpayd.test.service.ConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionService conversionService;

    @GetMapping(value = "/conversion",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConversionResource> conversion(@RequestParam Double sourceAmount, @RequestParam String sourceCurrency, @RequestParam String targetCurrency){

        return ResponseEntity.ok().body(conversionService.conversion(sourceAmount,sourceCurrency,targetCurrency));

    }
}
