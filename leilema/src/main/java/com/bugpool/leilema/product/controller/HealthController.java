package com.bugpool.leilema.product.controller;

import com.bugpool.leilema.util.NotControllerResponseAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** 不需要包装返回结果实例**/
@RestController
public class HealthController {

    @GetMapping("/health")
    @NotControllerResponseAdvice // 不需要包装返回结果
    public String health() {
        return "success。。";
    }
}
