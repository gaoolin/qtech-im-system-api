package com.qtech.im.wb.service;

import com.qtech.im.wb.vo.WbOlpChkVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/01 12:13:41
 * desc   :
 */


public interface IWbOlpIndexService {

    public List<WbOlpChkVo> selectFactoryNameList();

    public List<WbOlpChkVo> selectWorkShopNameList(WbOlpChkVo wbOlpChkVo);
}
