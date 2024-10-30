package com.qtech.im.common.exception;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/12/05 09:25:03
 * desc   :
 */


public class FixtureUploadException extends FixtureException {

    private static final long serialVersionUID = 1L;

    public FixtureUploadException() {
        super("治具数据导入异常！");
    }

    public FixtureUploadException(String message) {
        super(message);
    }
}
