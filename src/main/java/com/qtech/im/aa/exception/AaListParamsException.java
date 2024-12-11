package com.qtech.im.aa.exception;

import com.qtech.im.aa.utils.ErrorCode;
import lombok.Getter;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/11 11:09:12
 * desc   :
 */


// 自定义异常类
@Getter
public class AaListParamsException extends RuntimeException {
    private final ErrorCode errorCode;

    public AaListParamsException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
