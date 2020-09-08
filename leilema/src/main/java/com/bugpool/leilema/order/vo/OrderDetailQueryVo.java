package com.bugpool.leilema.order.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailQueryVo {
    private Integer detailId;

    /**
     * 订单主键
     */
    private Integer orderId;

    /**
     * 商品主键
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 当前价格
     */
    private BigDecimal productPrice;

    /**
     * 数量
     */
    private Integer productNumber;
}

