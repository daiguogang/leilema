package com.bugpool.leilema;

import com.bugpool.leilema.order.entity.OrderMaster;
import com.bugpool.leilema.order.mapper.OrderMasterMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderInfoMapperTest {

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Test
    public void testGetById() {
        OrderMaster orderMaster = orderMasterMapper.selectById(1);
        System.out.println(orderMaster);
        Assert.assertNotNull(orderMaster);
    }
}
