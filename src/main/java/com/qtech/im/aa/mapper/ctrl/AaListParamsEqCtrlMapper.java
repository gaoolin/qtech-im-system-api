package com.qtech.im.aa.mapper.ctrl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qtech.im.aa.domain.ctrl.AaListParamsEqCtrl;
import com.qtech.im.aa.vo.AaListParamsEqCtrlVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/19 10:12:41
 * desc   :
 */

@Mapper
public interface AaListParamsEqCtrlMapper extends BaseMapper<AaListParamsEqCtrl> {
    public List<AaListParamsEqCtrlVo> listFullInfo(AaListParamsEqCtrlVo aaListParamsEqCtrl);
    public int updateSimIdAndSource(AaListParamsEqCtrl aaListParamsEqCtrl);
}
