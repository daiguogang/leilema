package com.bugpool.leilema.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单详情
 * </p>
 *
 * @author bugpool
 * @since 2020-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 详情主键
     */
    @TableId(value = "detail_id", type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private String updateUser;


}
