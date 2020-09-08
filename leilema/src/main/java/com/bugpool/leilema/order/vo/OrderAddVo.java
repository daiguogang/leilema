package com.bugpool.leilema.order.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class OrderAddVo {

    /**
     * 买家名字
     */
    @NotBlank(message = "买家姓名不能为空")
    private String buyerName;

    /**
     * 买家电话
     */
    @NotBlank(message = "买家电话不能为空")
    private String buyPhone;

    @NotBlank(message = "订单详情不能为空")
    private String orderDetails;
}
