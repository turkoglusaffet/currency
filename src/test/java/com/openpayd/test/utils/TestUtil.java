package com.openpayd.test.utils;

import com.openpayd.test.accessor.dto.FixerDto;
import com.openpayd.test.controller.model.ExchangeModel;
import com.openpayd.test.controller.resource.ConversionResource;
import com.openpayd.test.controller.resource.ExchangeResource;

import java.util.HashMap;
import java.util.Map;

import static com.openpayd.test.utils.TestConstants.AMOUNT;
import static com.openpayd.test.utils.TestConstants.ANY_TRANSACTION_ID;
import static com.openpayd.test.utils.TestConstants.BASE_CURRENCY;
import static com.openpayd.test.utils.TestConstants.CURRENCY_1;
import static com.openpayd.test.utils.TestConstants.CURRENCY_2;
import static com.openpayd.test.utils.TestConstants.TIMESTAMP;
import static com.openpayd.test.utils.TestConstants.DATE;

public class TestUtil {
    public static ExchangeModel createExchangeModel() {
        return ExchangeModel.builder()
                .base(BASE_CURRENCY)
                .timestamp(TIMESTAMP)
                .date(DATE)
                .rates(createRates())
                .build();
    }

    public static FixerDto createFixerDto(){
        return FixerDto.builder().
                base(BASE_CURRENCY)
                .timestamp(TIMESTAMP)
                .date(DATE)
                .rates(createRates())
                .build();
    }

    private static Map<String, Double> createRates() {
     Map<String,Double> rates = new HashMap<>();
     rates.put(CURRENCY_1,12.0);
     rates.put(CURRENCY_2,10.0);
     return rates;
    }

    public static ExchangeResource createExchangeResource() {
        return ExchangeResource.builder()
                .rate(AMOUNT)
                .to(CURRENCY_2)
                .from(CURRENCY_1)
                .build();
    }

    public static ConversionResource createConversionResource() {
        return ConversionResource.builder()
                .transactionId(ANY_TRANSACTION_ID)
                .amount(AMOUNT)
                .build();
    }
}
