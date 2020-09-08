package com.bugpool.leilema.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品主键
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    /**
     * 商品名称
     */
    @NotNull(message = "商品名称不允许为空")
    private String productName;

    /**
     * 单价
     */
    @Min(value = 0, message = "商品价格不允许为负数")
    private BigDecimal productPrice;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 商品状态,0正常1下架
     */
    private Integer productStatus;

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
