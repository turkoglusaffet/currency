package com.openpayd.test.controller;

import com.openpayd.test.controller.resource.ExchangeResource;
import com.openpayd.test.service.ExchangeService;
import com.openpayd.test.utils.TestUtil;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.openpayd.test.utils.TestConstants.CURRENCY_1;
import static com.openpayd.test.utils.TestConstants.CURRENCY_2;
import static org.mockito.Mockito.when;

public class ExchangeControllerTest {

    private ExchangeController underTest;

    @Mock
    private ExchangeService exchangeService;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ExchangeController(exchangeService);
    }

    @Test
    public void success(){
        //given
        ExchangeResource resource = TestUtil.createExchangeResource();
        when(exchangeService.getExchangeRate(CURRENCY_1,CURRENCY_2)).thenReturn(resource);
        //when
        ResponseEntity<ExchangeResource> exchangeRate = underTest.getExchangeRate(CURRENCY_1, CURRENCY_2);
        //then

        Assert.assertEquals(exchangeRate.getBody().getRate(),resource.getRate());
        Assert.assertEquals(exchangeRate.getBody().getTo(),resource.getTo());
        Assert.assertEquals(exchangeRate.getBody().getFrom(),resource.getFrom());
    }
}
