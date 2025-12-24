package com.huike.util;

import lombok.Data;

/**
 * @author jy
 * @version 1.0
 * @date 2022/2/10 20:24
 */
@Data
public class ReturnObject {
    private boolean success = true;
    private String msg;

    public ReturnObject() {
    }

    public ReturnObject(String msg) {
        this.msg = msg;
    }

    public ReturnObject mark(String msg){
        this.success = false;
        this.msg = msg;
        return this;
    }
}
