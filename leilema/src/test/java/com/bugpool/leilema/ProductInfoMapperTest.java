package com.bugpool.leilema;


import com.bugpool.leilema.product.entity.ProductInfo;
import com.bugpool.leilema.product.mapper.ProductInfoMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoMapperTest {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Test
    public void testGetById() {
        ProductInfo productInfo = productInfoMapper.selectById(1);
        System.out.println(productInfo);
        Assert.assertNotNull(productInfo);
    }
}
