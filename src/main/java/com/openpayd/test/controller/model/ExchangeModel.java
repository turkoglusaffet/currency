package com.openpayd.test.controller.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ExchangeModel {

    private Long timestamp;
    private String base;
    private String date;
    private Map<String,Double> rates;
}