package com.qtech.im.common.exception;

/**
 * @author : gaozhilin
 * @project : qtech
 * @email : gaoolin@gmail.com
 * @date : 2023/03/30 08:59:41
 * @description : 金线标准用量业务异常类
 */


public class WireBizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected final String message;

    public WireBizException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
