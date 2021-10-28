package com.openpayd.test.service;

import com.openpayd.test.accessor.FixerAccessor;
import com.openpayd.test.controller.model.ExchangeModel;
import com.openpayd.test.controller.resource.ConversionResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversionService {

    private final ConverterService converterService;
    private final CalculatorService calculatorService;
    private final FixerAccessor fixerAccessor;
    public ConversionResource conversion(Double sourceAmount, String sourceCurrency, String targetCurrency) {

        ExchangeModel exchangeModel = converterService.convert(fixerAccessor.getAllData(), ExchangeModel.class);
        Double amount = calculatorService.conversion(exchangeModel,sourceAmount,sourceCurrency,targetCurrency);
        return ConversionResource.builder()
                .amount(amount)
                .transactionId(UUID.randomUUID().toString())
                .build();
    }
}
