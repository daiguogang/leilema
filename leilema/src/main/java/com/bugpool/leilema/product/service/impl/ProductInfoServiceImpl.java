package com.bugpool.leilema.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugpool.leilema.product.entity.ProductInfo;
import com.bugpool.leilema.product.mapper.ProductInfoMapper;
import com.bugpool.leilema.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService{

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getByLikeName(String productName) {
        return productInfoMapper.getByLikeName(productName);
    }

    @Override
    public List<ProductInfo> getByLikeName2(String productName) {
        return productInfoMapper.getByLikeName2(productName);
    }
}
