package com.bugpool.leilema;

import com.bugpool.leilema.product.entity.ProductInfo;
import com.bugpool.leilema.util.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    RedisService redisService;

    @Test
    public void get() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("推拿")
                .setProductId(1)
                .setProductPrice(new BigDecimal(100));

        redisService.set("testRedisGet", productInfo, 100);
        ProductInfo productInfo1 = (ProductInfo)redisService.get("testRedisGet");
        Assert.assertTrue(productInfo1.getProductName().equals(productInfo.getProductName()));
    }

}
