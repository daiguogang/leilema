package com.bugpool.leilema.order.service.impl;

import com.bugpool.leilema.order.entity.OrderDetail;
import com.bugpool.leilema.order.mapper.OrderDetailMapper;
import com.bugpool.leilema.order.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情 服务实现类
 * </p>
 *
 * @author bugpool
 * @since 2020-09-01
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
