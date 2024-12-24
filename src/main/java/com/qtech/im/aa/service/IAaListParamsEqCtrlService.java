package com.qtech.im.aa.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qtech.im.aa.domain.AaListParamsEqCtrl;
import com.qtech.im.aa.vo.AaListParamsEqCtrlVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/19 10:05:47
 * desc   :
 */


public interface IAaListParamsEqCtrlService extends IService<AaListParamsEqCtrl> {
    public List<AaListParamsEqCtrlVo> list(AaListParamsEqCtrlVo aaListParamsEqCtrl);

    public Boolean isExist(AaListParamsEqCtrl aaListParamsEqCtrl);

    public AaListParamsEqCtrl getById(Long id);

    public Boolean edit(AaListParamsEqCtrl aaListParamsEqCtrl);

    public Boolean addOne(AaListParamsEqCtrl aaListParamsEqCtrl);
}
