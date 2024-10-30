package com.qtech.im.common.exception;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/03/26 11:48:57
 * desc   :
 */


public class AddFixtureSharedInfoException extends FixtureException {

    private static final long serialVersionUID = 1L;

    public AddFixtureSharedInfoException() {
        super("新增治具共用异常！");
    }

    public AddFixtureSharedInfoException(String message) {
        super(message);
    }
}
