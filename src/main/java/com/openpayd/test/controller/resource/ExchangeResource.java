package com.openpayd.test.controller.resource;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ExchangeResource {
    private String from;
    private String to;
    private Double rate;
}
