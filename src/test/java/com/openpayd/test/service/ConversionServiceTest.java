package com.openpayd.test.service;

import com.openpayd.test.accessor.FixerAccessor;
import com.openpayd.test.accessor.dto.FixerDto;
import com.openpayd.test.controller.model.ExchangeModel;
import com.openpayd.test.controller.resource.ConversionResource;
import com.openpayd.test.controller.resource.ExchangeResource;
import com.openpayd.test.utils.TestUtil;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.openpayd.test.utils.TestConstants.AMOUNT;
import static com.openpayd.test.utils.TestConstants.CURRENCY_1;
import static com.openpayd.test.utils.TestConstants.CURRENCY_2;
import static org.mockito.Mockito.when;

public class ConversionServiceTest {

    private ConversionService underTest;

    @Mock
    private ConverterService converterServiceMock;
    @Mock
    private CalculatorService calculatorServiceMock;
    @Mock
    private FixerAccessor fixerAccessorMock;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ConversionService(converterServiceMock,calculatorServiceMock,fixerAccessorMock);
    }

    @Test
    public void conversionSuccess(){

        //when
        ExchangeModel exchangeModel = TestUtil.createExchangeModel();
        FixerDto fixerDto = TestUtil.createFixerDto();

        when(fixerAccessorMock.getAllData()).thenReturn(fixerDto);
        when(converterServiceMock.convert(fixerDto,ExchangeModel.class)).thenReturn(exchangeModel);

        //given
        ConversionResource conversion = underTest.conversion(AMOUNT, CURRENCY_1, CURRENCY_2);


        //then
        Assert.assertEquals(conversion.getAmount().doubleValue(),0.0);
    }

}
