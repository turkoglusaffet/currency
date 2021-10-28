package com.openpayd.test.service;

import com.openpayd.test.controller.model.ExchangeModel;
import com.openpayd.test.exception.Error;
import com.openpayd.test.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalculatorService {

    private void validationCurrencyExist(ExchangeModel exchangeModel, String currency){
        if(!exchangeModel.getBase().equals(currency) && exchangeModel.getRates().entrySet().stream().noneMatch(r -> r.getKey().equals(currency))){
            throw new ResourceNotFoundException(Error.NON_EXISTENT_CURRENCY.getErrorCode(),Error.NON_EXISTENT_CURRENCY.getErrorMessage(),currency);
        }
    }
    public Double getRateFromAndTo(ExchangeModel exchangeModel , String from, String to){
        validationCurrencyExist(exchangeModel,from);
        validationCurrencyExist(exchangeModel,to);
        ExchangeModel convertedModel = convertCurrencyWithFromCurrency(exchangeModel, from);
        return convertedModel.getRates().entrySet().stream().filter(r -> r.getKey().equals(to)).findFirst().get().getValue();


    }
    public ExchangeModel convertCurrencyWithFromCurrency(ExchangeModel exchangeModel, String from) {

        Optional<Map.Entry<String, Double>> base =
                exchangeModel.getRates().entrySet().stream().filter(stringDoubleEntry -> stringDoubleEntry.getKey().equals(from)).findFirst();
        if (base.isPresent()) {
            Double divider = base.get().getValue();
            exchangeModel.setBase(from);
            exchangeModel.setRates(calculate(exchangeModel.getRates(), divider));

        }
        return exchangeModel;
    }

    private Map<String, Double> calculate(Map<String,Double> rates,Double divide) {
        return rates.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()/divide));
}

    public Double conversion(ExchangeModel exchangeModel,Double sourceAmount, String sourceCurrency, String targetCurrency) {
        Double rateFromAndTo = getRateFromAndTo(exchangeModel, sourceCurrency, targetCurrency);
        return sourceAmount*rateFromAndTo;

    }
}
