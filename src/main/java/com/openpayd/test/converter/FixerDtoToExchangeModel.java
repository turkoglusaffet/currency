package com.openpayd.test.converter;

import com.openpayd.test.accessor.dto.FixerDto;
import com.openpayd.test.controller.model.ExchangeModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class FixerDtoToExchangeModel implements Converter<FixerDto, ExchangeModel> {

    @Override
    public ExchangeModel convert(FixerDto source) {
        return ExchangeModel.builder()
                .base(source.getBase())
                .date(source.getDate())
                .timestamp(source.getTimestamp())
                .rates(source.getRates())
                .build();
    }

}
