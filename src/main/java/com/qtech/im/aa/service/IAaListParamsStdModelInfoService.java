package com.qtech.im.aa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtech.im.aa.domain.AaListParamsStdModelInfo;
import com.qtech.im.aa.vo.AaListParamsEqVo;
import com.qtech.im.aa.vo.AaListParamsStdModelInfoVo;
import com.qtech.im.common.util.QtechImVoUtil;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 14:00:09
 * desc   :
 */


public interface IAaListParamsStdModelInfoService extends IService<AaListParamsStdModelInfo> {

    public QtechImVoUtil.QtechImVos<AaListParamsStdModelInfoVo> selectStdModelInfoList(AaListParamsStdModelInfo aaListParamsStdModelInfo);

    public boolean updateStdModelInfo(AaListParamsStdModelInfo aaListParamsStdModelInfo);

    public boolean saveOrUpdateStdModelInfo(Object entity);

    public boolean deleteStdModelInfoById(Long id);
}
