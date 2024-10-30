package com.qtech.im.common.mapper;

import com.qtech.im.common.domain.ImReportBaseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 16:03:42
 * desc   :
 */

@Mapper
public interface QtechImFactoryNamesMapper {
    public List<ImReportBaseInfo> getHistoryFactoryNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getLatestFactoryNames(ImReportBaseInfo imReportBaseInfo);
}
