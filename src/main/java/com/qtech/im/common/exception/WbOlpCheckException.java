package com.qtech.im.common.exception;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/09/06 16:32:44
 * desc   :  打线图导入异常类
 */


public class WbOlpCheckException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected final String message;

    public WbOlpCheckException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
