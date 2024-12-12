package com.qtech.im.aa.utils;

import lombok.Getter;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/11 11:06:54
 * desc   :
 */


// 枚举类型管理状态码
@Getter
public enum Status {
    ACTIVE(1),
    INACTIVE(0);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public static Status fromCode(int code) {
        for (Status status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }

}
