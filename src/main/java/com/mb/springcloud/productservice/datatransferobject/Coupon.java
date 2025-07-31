package com.mb.springcloud.productservice.datatransferobject;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Coupon {
    private long id;
    private String code;
    private BigDecimal discount;
    private String expDate;
}
