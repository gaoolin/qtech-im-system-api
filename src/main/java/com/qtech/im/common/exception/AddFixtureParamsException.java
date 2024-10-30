package com.qtech.im.common.exception;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/04/03 13:21:38
 * desc   :
 */


public class AddFixtureParamsException extends FixtureException {

    private static final long serialVersionUID = 1L;

    public AddFixtureParamsException() {
        super("新增治具参数异常！");
    }

    public AddFixtureParamsException(String message) {
        super(message);
    }
}
