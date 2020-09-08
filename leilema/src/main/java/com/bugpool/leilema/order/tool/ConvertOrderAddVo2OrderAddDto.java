package com.bugpool.leilema.order.tool;

import com.alibaba.fastjson.JSON;
import com.bugpool.leilema.order.entity.OrderDetail;
import com.bugpool.leilema.order.vo.OrderAddDto;
import com.bugpool.leilema.order.vo.OrderAddVo;

import java.util.List;

public class ConvertOrderAddVo2OrderAddDto {
    public static OrderAddDto convert(OrderAddVo orderAddVo) {
        OrderAddDto orderAddDto = new OrderAddDto();

        List<OrderDetail> orderDetails = JSON.parseArray(orderAddVo.getOrderDetails(), OrderDetail.class);

        orderAddDto.setBuyerName(orderAddVo.getBuyerName())
                .setBuyerPhone(orderAddVo.getBuyPhone())
                .setOrderDetails(orderDetails);

        return orderAddDto;

    }
}
