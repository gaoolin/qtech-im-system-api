package com.qtech.im.common.exception;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/09/06 16:31:24
 * desc   :
 */


public class WbOlpCheckUploadException extends WbOlpCheckException {

    private static final long serialVersionUID = 1L;

    public WbOlpCheckUploadException() {
        super("打线图标准模版导入异常！");
    }

    public WbOlpCheckUploadException(String message) {
        super(message);
    }
}
