package com.bugpool.leilema.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bugpool.leilema.product.entity.ProductInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductInfoService extends IService<ProductInfo>{
    List<ProductInfo> getByLikeName(String productName);

    List<ProductInfo> getByLikeName2(String productName);
}
