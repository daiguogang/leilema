package com.bugpool.leilema.product.tool;

import com.bugpool.leilema.util.StatusCode;
import lombok.Getter;

@Getter
public enum ProductStatusEnums implements StatusCode {
    UP(0, "上架"),
    DOWN(1, "下架");

    private int code;
    private String msg;

    ProductStatusEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
