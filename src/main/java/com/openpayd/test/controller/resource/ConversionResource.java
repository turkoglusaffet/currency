package com.openpayd.test.controller.resource;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ConversionResource {
    private Double amount;
    private String transactionId;
}
