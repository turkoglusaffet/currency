package com.openpayd.test.service;

import com.openpayd.test.accessor.FixerAccessor;
import com.openpayd.test.controller.model.ExchangeModel;
import com.openpayd.test.controller.resource.ExchangeResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ConverterService converterService;
    private final CalculatorService calculatorService;
    private final FixerAccessor fixerAccessor;
    public ExchangeResource getExchangeRate(String from,String to) {
        ExchangeModel exchangeModel = converterService.convert(fixerAccessor.getAllData(), ExchangeModel.class);
        Double rate = calculatorService.getRateFromAndTo(exchangeModel,from,to);
        return ExchangeResource.builder()
                .from(from)
                .to(to)
                .rate(rate)
                .build();
    }
}
