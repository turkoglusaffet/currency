package com.openpayd.test.controller;

import com.openpayd.test.controller.resource.ConversionResource;
import com.openpayd.test.controller.resource.ExchangeResource;
import com.openpayd.test.service.ConversionService;
import com.openpayd.test.service.ExchangeService;
import com.openpayd.test.utils.TestUtil;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.openpayd.test.utils.TestConstants.AMOUNT;
import static com.openpayd.test.utils.TestConstants.CURRENCY_1;
import static com.openpayd.test.utils.TestConstants.CURRENCY_2;
import static org.mockito.Mockito.when;

public class ConversionControllerTest {

    private ConversionController underTest;

    @Mock
    private ConversionService conversionService;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ConversionController(conversionService);
    }

    @Test
    public void success(){
        //given
        ConversionResource resource = TestUtil.createConversionResource();
        when(conversionService.conversion(AMOUNT,CURRENCY_1,CURRENCY_2)).thenReturn(resource);

        //when
        ResponseEntity<ConversionResource> conversion = underTest.conversion(AMOUNT, CURRENCY_1, CURRENCY_2);

        //then

        Assert.assertEquals(conversion.getBody().getAmount(),resource.getAmount());
        Assert.assertEquals(conversion.getBody().getTransactionId(),resource.getTransactionId());
    }
}
