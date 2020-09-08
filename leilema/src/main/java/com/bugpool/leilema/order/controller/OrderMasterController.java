package com.bugpool.leilema.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bugpool.leilema.order.entity.OrderMaster;
import com.bugpool.leilema.order.service.OrderMasterService;
import com.bugpool.leilema.order.vo.OrderAddDto;
import com.bugpool.leilema.order.vo.OrderQueryVo;
import com.bugpool.leilema.util.APIException;
import com.bugpool.leilema.util.AppCode;
import com.bugpool.leilema.util.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author bugpool
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/order/order-master")
public class OrderMasterController {

    @Autowired
    private OrderMasterService orderMasterService;

    @PostMapping("/findByVo")
    public OrderMaster findByVo(@RequestBody @Validated OrderMaster vo) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(vo, orderMaster);
        OrderMaster orderMaster1 = orderMasterService.getOne(new QueryWrapper(orderMaster));
        if (null == orderMaster1) {
            throw new APIException(AppCode.ORDER_NOT_EXIST, "订单号不存在：" + vo.getOrderId());
        }

        return  orderMaster1;
    }

    @PostMapping("/createOrder")
    public Map<String, Integer> createOrder(@RequestBody @Validated OrderAddDto dto) {
        Integer re = orderMasterService.createOrder(dto);
        if(null == re) {
            throw new APIException("创建订单失败！");
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("生成订单ID",re);
        return map;
    }

    @PostMapping("/findOrderById")
    public OrderQueryVo findOrderById(int id) {
        OrderQueryVo re = orderMasterService.findOrderById(id);
        return re;
    }

    @PostMapping("/cancelOrderById")
    public Map<String, Boolean> cancelOrderById(int id) {
        boolean re = orderMasterService.cancel(id);
        Map<String,Boolean> map = new HashMap<>();
        map.put("删除结果",re);
        return map;
    }

}
