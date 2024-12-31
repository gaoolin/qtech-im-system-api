package com.qtech.im.aa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtech.im.aa.domain.template.AaListParamsStdTemplateInfo;
import com.qtech.im.aa.vo.AaListParamsStdModelInfoVo;
import com.qtech.im.common.util.QtechImVoUtil;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 14:00:09
 * desc   :
 */


public interface IAaListParamsStdTemplateInfoService extends IService<AaListParamsStdTemplateInfo> {

    public QtechImVoUtil.QtechImVos<AaListParamsStdModelInfoVo> selectStdModelInfoList(AaListParamsStdTemplateInfo aaListParamsStdTemplateInfo);

    public boolean updateStdModelInfo(AaListParamsStdTemplateInfo aaListParamsStdTemplateInfo);

    public boolean saveOrUpdateStdModelInfo(Object entity);

    public boolean deleteStdModelInfoById(Long id);
}
