package com.qtech.im.aa.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/27 14:44:42
 * desc   :
 */

@Getter
public class AaListParamsStdTemplateEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    private final String prodType;
    private final String operation; // 操作类型：INSERT, UPDATE, DELETE

    public AaListParamsStdTemplateEvent(Object source, String prodType, String operation) {
        super(source);
        this.prodType = prodType;
        this.operation = operation;
    }
}