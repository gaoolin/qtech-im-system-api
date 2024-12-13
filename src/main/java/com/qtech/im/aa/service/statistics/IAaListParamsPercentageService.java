package com.qtech.im.aa.service.statistics;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtech.im.aa.vo.statistics.AaListParamsPercentageVo;
import com.qtech.im.common.util.QtechImVoUtil;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/13 10:13:58
 * desc   :
 */


public interface IAaListParamsPercentageService extends IService<AaListParamsPercentageVo> {

    boolean saveBatch(List<AaListParamsPercentageVo> entityList, int batchSize);

    boolean saveOrUpdateBatch(List<AaListParamsPercentageVo> entityList, int batchSize);

    List<AaListParamsPercentageVo> selectAaParamsPercentageList(AaListParamsPercentageVo aaListParamsPercentage);
}