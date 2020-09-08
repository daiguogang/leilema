package com.bugpool.leilema.order.tool;

import com.bugpool.leilema.util.StatusCode;
import lombok.Getter;

@Getter
public enum  OrderStatusEnums implements StatusCode {

    PAY(0,"已支付"),
    NO_PAY(1,"未支付"),
    CANCEL(2,"已取消");

    private int code;
    private String msg;

    OrderStatusEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
