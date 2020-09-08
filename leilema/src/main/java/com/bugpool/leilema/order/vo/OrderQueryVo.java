package com.bugpool.leilema.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderQueryVo {

    private String buyerName;

    private String buyerPhone;

    private BigDecimal orderAmount;

    private Integer status;

    private List<OrderDetailQueryVo> orderDetails;
}
