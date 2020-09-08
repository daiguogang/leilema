package com.bugpool.leilema.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugpool.leilema.product.entity.ProductInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductInfoMapper extends BaseMapper<ProductInfo>{
    List<ProductInfo> getByLikeName(String productName);

    @Select(" SELECT * FROM product_info WHERE product_name LIKE concat(concat('%',#{productName}),'%');")
    List<ProductInfo> getByLikeName2(String productName);
}
