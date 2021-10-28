package com.openpayd.test.service;

import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConverterServiceTest {
    private ConverterService underTest;
    @Mock
    private ConversionService conversionService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ConverterService(conversionService);
    }

    @Test
    public void shouldReturnEmptyListWhenConvertingNullReferencedList() {
        final List<Object> convertedList = underTest.convertList(null, Object.class);
        assertThat(convertedList, is(notNullValue()));
        assertThat(convertedList, is(empty()));
        verify(conversionService, never()).convert(any(), any());
    }

    @Test
    public void shouldReturnIntegerListToStringList() {
        when(conversionService.convert(1, String.class)).thenReturn("1");
        when(conversionService.convert(2, String.class)).thenReturn("2");
        when(conversionService.convert(3, String.class)).thenReturn("3");

        List<Integer> integers = Lists.list(1, 2, 3);
        final List<String> convertedList = underTest.convertList(integers, String.class);
        assertThat(convertedList, is(notNullValue()));
        assertThat(convertedList, is(not(empty())));
        assertThat(convertedList, Matchers.hasItems("1", "2", "3"));
        verify(conversionService, times(3)).convert(any(), any());
    }


    @Test
    public void shouldReturnEmptyPageWhenConvertingNullReferencedPage() {
        Page<List> source = null;
        final Page<List> convertedPage = underTest.convertPage(source, List.class);
        assertThat(convertedPage, is(notNullValue()));
        assertThat(convertedPage.getTotalElements(), is(equalTo(0L)));
        assertThat(convertedPage.getTotalElements(), is(equalTo(0L)));
        verify(conversionService, never()).convert(any(), any());
    }

}
