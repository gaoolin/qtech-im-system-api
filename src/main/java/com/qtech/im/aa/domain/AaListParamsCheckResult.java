package com.qtech.im.aa.domain;

import com.qtech.im.common.domain.ImReportBaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/03 10:45:07
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AaListParamsCheckResult extends ImReportBaseInfo {
    private Integer flag;
}
