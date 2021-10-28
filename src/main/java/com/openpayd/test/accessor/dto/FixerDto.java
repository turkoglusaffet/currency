package com.openpayd.test.accessor.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class FixerDto {
    private boolean success;
    private Long timestamp;
    private String base;
    private String date;
    private Map<String,Double> rates;

}
