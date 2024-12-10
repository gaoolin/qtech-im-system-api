package com.qtech.im.wb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qtech.im.common.domain.ImReportBaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/09/16 12:17:36
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WbOlpTrending extends ImReportBaseInfo {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dt;
}
