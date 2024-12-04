package com.qtech.im.aa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtech.im.aa.domain.AaListParamsStdModelInfoVo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 14:00:09
 * desc   :
 */


public interface IAaListParamsStdModelInfoService extends IService<AaListParamsStdModelInfoVo> {

    public List<AaListParamsStdModelInfoVo> selectStdModelInfoList(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo);

    public boolean updateStdModelInfo(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo);

    public boolean saveOrUpdateStdModelInfo(Object entity);

    public boolean deleteStdModelInfoById(Long id);
}
