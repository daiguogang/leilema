package com.bugpool.leilema.order.vo;

import com.bugpool.leilema.order.entity.OrderDetail;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderAddDto {

    private String buyerName;

    private String buyerPhone;

    private List<OrderDetail> orderDetails;
}


