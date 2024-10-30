package com.qtech.im.common.exception;

/**
 * @author : gaozhilin
 * @project : qtech
 * @email : gaoolin@gmail.com
 * @date : 2023/03/30 09:08:06
 * @description : 检查金线标准用量数据是否存在
 */


public class WireBizIsExistException extends WireBizException {

    public static final long serialVersionUID = 1L;

    public WireBizIsExistException() {
        super("金线标准用量数据已存在！");
    }

    public WireBizIsExistException(String message) {
        super(message);
    }
}
