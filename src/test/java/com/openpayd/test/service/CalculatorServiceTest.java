package com.openpayd.test.service;

import com.openpayd.test.controller.model.ExchangeModel;
import com.openpayd.test.exception.ResourceNotFoundException;
import com.openpayd.test.utils.TestConstants;
import com.openpayd.test.utils.TestUtil;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.openpayd.test.utils.TestConstants.BASE_CURRENCY;
import static com.openpayd.test.utils.TestConstants.CURRENCY_1;
import static com.openpayd.test.utils.TestConstants.CURRENCY_2;
import static com.openpayd.test.utils.TestConstants.WRONG_CURRENCY;

public class CalculatorServiceTest {

    private CalculatorService underTest;

    @BeforeMethod
    public void setUp() {
        underTest = new CalculatorService();
    }

    @Test
    public void getRateFromAndToSuccess(){
        //when
        ExchangeModel exchangeModel = TestUtil.createExchangeModel();
        //given
        Double rate = underTest.getRateFromAndTo(exchangeModel, BASE_CURRENCY, CURRENCY_1);
        //then
        Assert.assertEquals(rate.doubleValue(), 12.0);
    }

    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void shouldResourceNotFoundExceptionIfCurrencyIsNotExist(){
        //when
        ExchangeModel exchangeModel = TestUtil.createExchangeModel();
        //given //then
         underTest.getRateFromAndTo(exchangeModel, BASE_CURRENCY, WRONG_CURRENCY);

    }

    @Test
    public void convertCurrencyWithFromCurrencySuccess(){
        //when
        ExchangeModel exchangeModel = TestUtil.createExchangeModel();
        //given
        ExchangeModel convertedExchangeModel = underTest.convertCurrencyWithFromCurrency(exchangeModel, CURRENCY_1);
        //then
        Assert.assertEquals(convertedExchangeModel.getBase(),CURRENCY_1);

    }

    @Test
    public void conversionSuccess(){
        //when
        ExchangeModel exchangeModel = TestUtil.createExchangeModel();
        //given
        Double amount = underTest.conversion(exchangeModel, 10.0, CURRENCY_1, CURRENCY_2);
        //then
        Assert.assertEquals(amount.doubleValue(),8.333333333333334);

    }
}
