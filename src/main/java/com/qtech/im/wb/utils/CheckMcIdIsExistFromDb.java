package com.qtech.im.wb.utils;

import com.qtech.im.wb.mapper.WbOlpStdModDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/09/06 17:17:10
 * desc   :  工具类
 */

@Component
public class CheckMcIdIsExistFromDb {

    @Autowired
    private static WbOlpStdModDetailMapper wbOlpStdModDetailMapper;

    public static Boolean isExist(String mcId) {
        int count = wbOlpStdModDetailMapper.countWbOlpStdModDetailByMcId(mcId);
        return count != 0;
    }
}
