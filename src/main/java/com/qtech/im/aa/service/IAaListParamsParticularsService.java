package com.qtech.im.aa.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qtech.im.aa.vo.statistics.AaListParamsParticularsVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/16 11:46:04
 * desc   :
 */


public interface IAaListParamsParticularsService extends IService<AaListParamsParticularsVo> {

    public List<AaListParamsParticularsVo> selectAaParamsParticalursList(AaListParamsParticularsVo aaListParamsParticularsVo);
}
