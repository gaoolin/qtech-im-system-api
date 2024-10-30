package com.qtech.im.common.exception;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/03/26 14:47:34
 * desc   :
 */


public class EditFixtureSharedInfoException extends FixtureException {

    private static final long serialVersionUID = 1L;

    public EditFixtureSharedInfoException() {
        super("修改治具共用信息异常！");
    }

    public EditFixtureSharedInfoException(String message) {
        super(message);
    }
}
