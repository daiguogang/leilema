package com.bugpool.leilema.order.service.impl;

import com.bugpool.leilema.order.entity.OrderDetail;
import com.bugpool.leilema.order.entity.OrderMaster;
import com.bugpool.leilema.order.mapper.OrderMasterMapper;
import com.bugpool.leilema.order.service.OrderDetailService;
import com.bugpool.leilema.order.service.OrderMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugpool.leilema.order.tool.DateFormatTools;
import com.bugpool.leilema.order.tool.OrderStatusEnums;
import com.bugpool.leilema.order.vo.OrderAddDto;
import com.bugpool.leilema.order.vo.OrderDetailQueryVo;
import com.bugpool.leilema.order.vo.OrderQueryVo;
import com.bugpool.leilema.product.entity.ProductInfo;
import com.bugpool.leilema.product.mapper.ProductInfoMapper;
import com.bugpool.leilema.product.tool.ProductStatusEnums;
import com.bugpool.leilema.util.APIException;
import com.bugpool.leilema.util.AppCode;
import com.bugpool.leilema.util.BeanConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author bugpool
 * @since 2020-09-01
 */
@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements OrderMasterService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Override
    @Transactional
    public Integer createOrder(OrderAddDto orderAddDto) {

        // 订单总金额
        BigDecimal amount = BigDecimal.ZERO;
        // 订单详情
        List<OrderDetail> orderDetails = new ArrayList<>();

        // 从ids中查找所有商品信息
        for (OrderDetail orderDetail : orderAddDto.getOrderDetails()) {
            ProductInfo productInfo = productInfoMapper.selectById(orderDetail.getProductId());
            if (null == productInfo || ProductStatusEnums.DOWN.getCode() == productInfo.getProductStatus()) {
                throw new APIException(AppCode.PRODUCT_NOT_EXIST, "上架商品中无法查询到：" + orderDetail.getProductId());
            }
            // 计算订单总金额
            amount = amount.add(productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductNumber())));

            // 计算订单总金额
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetails.add(orderDetail);
        }

        // 设置主订单，状态是未支付
        OrderMaster orderMaster = BeanConvertUtils.convertTo(orderAddDto, OrderMaster::new);
        orderMaster.setOrderAmount(amount);
        orderMaster.setStatus(OrderStatusEnums.NO_PAY.getCode());
        orderMaster.setCreateTime(DateFormatTools.getNowLocalDateTime());
        orderMaster.setCreateUser("dgg");
        orderMaster.setUpdateUser("dgg");
        save(orderMaster);

        // 设置detail 的order主键
        orderDetails.stream().forEach(p -> p.setOrderId(orderMaster.getOrderId()));
        orderDetailService.saveBatch(orderDetails);

        return orderMaster.getOrderId();
    }

    @Override
    @Transactional
    public boolean cancel(int orderId) {
        OrderMaster orderMaster = getById(orderId);
        if (null == orderMaster) {
            throw new APIException(AppCode.ORDER_NOT_EXIST, "订单号不存在：" + orderId);
        }

        OrderMaster updateOrderMaster = new OrderMaster();
        updateOrderMaster.setOrderId(orderMaster.getOrderId())
                .setStatus(OrderStatusEnums.CANCEL.getCode());

        return updateById(updateOrderMaster);
    }

    @Override
    public OrderQueryVo findOrderById(int id) {
        OrderMaster orderMaster = orderMasterMapper.selectById(id);
        if(null == orderMaster) {
            throw new APIException(AppCode.ORDER_NOT_EXIST, "订单不存在：" + id);
        }

        List<OrderDetail> details = orderDetailService.lambdaQuery()
                .eq(OrderDetail::getOrderId, orderMaster.getOrderId()).list();

        if(null == details || 0 == details.size()) {
            throw new APIException(AppCode.ORDER_DETAILS_NOT_EXIST, "订单详情不存在：" +id);
        }

        OrderQueryVo orderQueryVo = new OrderQueryVo();
        orderQueryVo.setOrderDetails(BeanConvertUtils.convertListTo(details, OrderDetailQueryVo::new));
        BeanUtils.copyProperties(orderMaster, orderQueryVo);
        return orderQueryVo;
    }


}
