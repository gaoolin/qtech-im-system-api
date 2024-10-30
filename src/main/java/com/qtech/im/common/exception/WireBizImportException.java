package com.qtech.im.common.exception;

/**
 * @author : gaozhilin
 * @project : qtech
 * @email : gaoolin@gmail.com
 * @date : 2023/03/30 09:05:00
 * @description : 上传失败报错信息
 */


public class WireBizImportException extends WireBizException {

    private static final long serialVersionUID = 1L;

    public WireBizImportException() {
        super("金线用量标准上传失败！");
    }

    public WireBizImportException(String message) {
        super(message);
    }
}
