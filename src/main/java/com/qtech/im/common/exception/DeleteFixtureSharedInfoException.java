package com.qtech.im.common.exception;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/03/26 14:49:54
 * desc   :
 */


public class DeleteFixtureSharedInfoException extends FixtureException {

    private static final long serialVersionUID = 1L;

    public DeleteFixtureSharedInfoException() {
        super("删除治具共用信息异常！");
    }

    public DeleteFixtureSharedInfoException(String message) {
        super(message);
    }
}
