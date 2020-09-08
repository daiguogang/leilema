package com.bugpool.leilema.order.service;

import com.bugpool.leilema.order.entity.OrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bugpool.leilema.order.vo.OrderAddDto;
import com.bugpool.leilema.order.vo.OrderQueryVo;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author bugpool
 * @since 2020-09-01
 */
public interface OrderMasterService extends IService<OrderMaster> {

    // 新增订单
    public Integer createOrder(OrderAddDto orderAddDto);

    // 取消订单
    public boolean cancel(int orderId);

    // 查询订单
    public OrderQueryVo findOrderById(int id);
}
