package com.bugpool.leilema.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugpool.leilema.product.entity.ProductInfo;
import com.bugpool.leilema.product.service.ProductInfoService;
import com.bugpool.leilema.util.BeanConvertUtils;
import com.bugpool.leilema.product.tool.ProductStatusEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product/product-info")
@Slf4j
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    @PostMapping("/findByVo")
    public ProductInfo findByVo(@RequestBody @Validated ProductInfo vo) {
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(vo, productInfo);
        ProductInfo rsProductInfo = productInfoService.getOne(new QueryWrapper(productInfo));
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        String fileName = stackTraceElements[0].getFileName();
        int lineNumber = stackTraceElements[0].getLineNumber() - 1;
        log.error("查询商品出错：文件名 " + fileName + " 行号 " + lineNumber);

        try {
            int test = 12 / 0;
        } catch (Exception e) {
            log.error("查询商品出错2", e);
        }
        return rsProductInfo;
    }

    @PostMapping("/findByLikeName")
    public List findByLikeName(String productName) {
        List<ProductInfo> list = productInfoService.getByLikeName(productName);
        return list;
    }

    @PostMapping("/findByLikeName2")
    public List findByLikeName2(String productName) {
        // List<ProductInfo> list = productInfoService.getByLikeName(productName);

        //lambda query 写法
        List<ProductInfo> list = productInfoService.lambdaQuery().like(ProductInfo::getProductName, productName).list();
        return list;
    }

    @PostMapping("/findById")
    public ProductInfo findById(Integer id) {
        return BeanConvertUtils.convertTo(productInfoService.getById(id), ProductInfo::new);
    }

    @PostMapping("/page")
    public IPage findPage(Page page, @Validated ProductInfo vo) {
        // 将vo => po,进行page查询
//        productInfoService.page(page,new QueryWrapper<ProductInfo>(BeanConvertUtils.convertTo(vo, ProductInfo::new)));
        productInfoService.page(page, new QueryWrapper<ProductInfo>(vo));
        // page.getRecords()此时为po类型，转换为vo
        // page.setRecords(BeanConvertUtils.convertListTo(page.getRecords(), ProductInfo::new));
        return page;
    }

    @PostMapping("/add")
    public boolean add(ProductInfo vo) {
        return productInfoService.save(BeanConvertUtils.convertTo(vo, ProductInfo::new));
    }

    @PostMapping("/deleteById")
    public boolean deleteById(Integer id) {
        return productInfoService.removeById(id);
    }

    @PostMapping("/updateById")
    public boolean updateById(@Valid ProductInfo vo){
        return productInfoService.updateById(BeanConvertUtils.convertTo(vo, ProductInfo::new));
    }

    @PostMapping("/testLambda")
    public List testLambda(String productName){
        List<ProductInfo> list = productInfoService.lambdaQuery().like(ProductInfo::getProductName, productName)
                .eq(ProductInfo::getProductStatus, ProductStatusEnums.UP.getCode())
                .gt(ProductInfo::getProductPrice, 5)
                .orderByDesc(ProductInfo::getProductPrice)
                .list();
        return list;
    }
}
