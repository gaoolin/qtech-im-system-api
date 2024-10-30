package com.qtech.im.wb.service.impl;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.exception.WbOlpCheckUploadException;
import com.qtech.im.wb.domain.WbOlpStdModDetail;
import com.qtech.im.wb.domain.WbOlpStdModInfo;
import com.qtech.im.wb.mapper.WbOlpStdModelDetailMapper;
import com.qtech.im.wb.mapper.WbOlpStdModelInfoMapper;
import com.qtech.im.wb.service.IWbOlpStdModelDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/05 16:23:19
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class WbOlpStdModelDetailServiceImpl implements IWbOlpStdModelDetailService {
    @Autowired
    private WbOlpStdModelDetailMapper wbOlpStdModelDetailMapper;

    @Autowired
    private WbOlpStdModelInfoMapper wbOlpStdModelInfoMapper;

    @Override
    public WbOlpStdModDetail selectWbOlpStdModDetailById(Long id) {
        try {
            return wbOlpStdModelDetailMapper.selectWbOlpStdModDetailById(id);
        } catch (Exception e) {
            log.error("查询标准模组明细异常" , e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<WbOlpStdModDetail> selectWbOlpStdModDetailList(WbOlpStdModDetail wbOlpStdModDetail) {
        try {
            return wbOlpStdModelDetailMapper.selectWbOlpStdModDetailList(wbOlpStdModDetail);
        } catch (Exception e) {
            log.error("查询标准模组明细异常" , e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public int insertWbOlpStdModDetail(WbOlpStdModDetail wbOlpStdModDetail) {
        wbOlpStdModDetail.setCreateTime(DateUtils.getNowDate());
        try {
            return wbOlpStdModelDetailMapper.insertWbOlpStdModDetail(wbOlpStdModDetail);
        } catch (Exception e) {
            log.error("插入标准模组明细异常" , e);
            throw new RuntimeException("保存数据失败，请联系系统负责人!");
        }
    }

    @Override
    public int updateWbOlpStdModDetail(WbOlpStdModDetail wbOlpStdModDetail) {
        wbOlpStdModDetail.setUpdateTime(DateUtils.getNowDate());
        try {
            return wbOlpStdModelDetailMapper.updateWbOlpStdModDetail(wbOlpStdModDetail);
        } catch (Exception e) {
            log.error("修改标准模版明细异常" , e);
            throw new RuntimeException("修改数据失败，请联系系统负责人!");
        }
    }

    @Override
    public int deleteWbOlpStdModDetailById(Long id) {
        try {
            return wbOlpStdModelDetailMapper.deleteWbOlpStdModDetailById(id);
        } catch (Exception e) {
            log.error("删除标准模版明细信息异常" , e);
            throw new RuntimeException("删除数据失败，请联系系统负责人!");
        }
    }

    @Override
    public int deleteWbOlpStdModDetailByIds(Long[] ids) {
        try {
            return wbOlpStdModelDetailMapper.deleteWbOlpStdModDetailByIds(ids);
        } catch (Exception e) {
            log.error("批量删除标准模版明细异常" , e);
            throw new RuntimeException("批量删除数据失败，请联系系统负责人!");
        }
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Map<String, String> uploadWbOlpStdModDetail(List<WbOlpStdModDetail> wbOlpStdModDetails, WbOlpStdModInfo wbOlpStdModInfo) {

        Map<String, Integer> modStat = new HashMap<>();
        Map<String, String> resultMap = new HashMap<>();
        ArrayList<WbOlpStdModDetail> oneMcIdList = new ArrayList<>();
        ArrayList<String> existMcIdList = new ArrayList<>();

        int insert = 0;
        int exist = 0;

        if (StringUtils.isNull(wbOlpStdModDetails) || wbOlpStdModDetails.isEmpty()) {
            throw new WbOlpCheckUploadException();
        }

        for (WbOlpStdModDetail wbOlpStdModDetail : wbOlpStdModDetails) {
            modStat.merge(wbOlpStdModDetail.getMcId(), 1, Integer::sum);
        }

        for (String key : modStat.keySet()) {

            for (WbOlpStdModDetail wbOlpStdModDetail : wbOlpStdModDetails) {
                if (key.equals(wbOlpStdModDetail.getMcId())) {
                    oneMcIdList.add(wbOlpStdModDetail);
                }
            }

            Boolean isExist = isExistMcId(key);
            if (isExist) {
                existMcIdList.add(key);
                oneMcIdList.clear();
                exist = exist + 1;
                break;
            } else {
                wbOlpStdModInfo.setMcId(key);
                wbOlpStdModInfo.setLineCount(modStat.get(key));

                wbOlpStdModelInfoMapper.insertWbOlpStdModInfo(wbOlpStdModInfo);

                for (WbOlpStdModDetail oneMcId : oneMcIdList) {
                    try {
                        wbOlpStdModelDetailMapper.insertWbOlpStdModDetail(oneMcId);
                    } catch (Exception e) {
                        log.error("插入标准模版明细异常" , e);
                        throw new RuntimeException("插入标准模版明细异常，请联系系统负责人!");
                    }
                }

                oneMcIdList.clear();
            }
            insert = insert + 1;
        }

        if (existMcIdList.isEmpty()) {
            resultMap.put("flag" , "1");
            resultMap.put("result" , "共 " + (insert + exist) + " 个机型，已导入 " + insert + " 个机型。");
        } else {
            resultMap.put("flag" , "0");
            resultMap.put("result" , "共 " + (insert + exist) + " 个机型，已导入 " + insert + " 个机型，未导入 " + exist +
                    " 个机型。以下机型模板已存在，请检查！\n" + existMcIdList.toString());
        }

        return resultMap;
    }

    @Override
    public Boolean isExistMcId(String mcId) {
        int count = 0;
        try {
            count = wbOlpStdModelDetailMapper.countWbOlpStdModDetailByMcId(mcId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count != 0;
    }
}
