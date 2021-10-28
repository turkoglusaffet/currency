package com.openpayd.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConverterService {
    private final ConversionService conversionService;

    public <S, T> List<T> convertList(final Iterable<S> sourceList, final Class<T> targetClass) {
        if (sourceList == null) {
            return Collections.emptyList();
        }
        List<T> convertedList = new ArrayList<>();
        sourceList.forEach(o -> convertedList.add(conversionService.convert(o, targetClass)));
        return convertedList;
    }

    public <T> T convert(Object source, Class<T> targetType) {
        return conversionService.convert(source, targetType);
    }

    public <S, D> Page<D> convertPage(final Page<S> sourcePage, final Class<D> targetClass) {
        if (sourcePage == null) {
            return new PageImpl<>(Collections.emptyList());
        }

        return sourcePage.map(s -> convert(s, targetClass));
    }
}
