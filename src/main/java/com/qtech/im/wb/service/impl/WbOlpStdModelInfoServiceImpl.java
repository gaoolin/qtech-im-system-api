package com.qtech.im.wb.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wb.domain.WbOlpStdModInfo;
import com.qtech.im.wb.domain.WbOlpTrendingVo;
import com.qtech.im.wb.mapper.WbOlpStdModelInfoMapper;
import com.qtech.im.wb.service.IWbOlpStdModelInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/05 15:31:20
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class WbOlpStdModelInfoServiceImpl implements IWbOlpStdModelInfoService {
    @Autowired
    private WbOlpStdModelInfoMapper wbOlpStdModelInfoMapper;

    @Override
    public WbOlpStdModInfo selectWbOlpStdModInfoBySid(Long sid) {
        try {
            return wbOlpStdModelInfoMapper.selectWbOlpStdModInfoBySid(sid);
        } catch (Exception e) {
            log.error("selectWbOlpStdModInfoBySid error", e);
            throw new RuntimeException("查询数据库发生异常，请联系系统负责人。");
        }
    }

    @Override
    public List<WbOlpStdModInfo> selectWbOlpStdModInfoList(WbOlpStdModInfo wbOlpStdModInfo) {
        try {
            return wbOlpStdModelInfoMapper.selectWbOlpStdModInfoList(wbOlpStdModInfo);
        } catch (Exception e) {
            log.error("selectWbOlpStdModInfoList error", e);
            throw new RuntimeException("查询数据库发生异常，请联系系统负责人。");
        }
    }

    @Override
    public int insertWbOlpStdModInfo(WbOlpStdModInfo wbOlpStdModInfo) {

        try {
            return wbOlpStdModelInfoMapper.insertWbOlpStdModInfo(wbOlpStdModInfo);
        } catch (Exception e) {
            log.error("insertWbOlpStdModInfo error", e);
            throw new RuntimeException("新增智慧打线图失败，请联系系统负责人!");
        }
    }

    @Override
    public int updateWbOlpStdModInfo(WbOlpStdModInfo wbOlpStdModInfo) {
        try {
            return wbOlpStdModelInfoMapper.updateWbOlpStdModInfo(wbOlpStdModInfo);
        } catch (Exception e) {
            log.error("updateWbOlpStdModInfo error", e);
            throw new RuntimeException("修改智慧打线图失败，请联系系统负责人!");
        }
    }

    @Override
    public int deleteWbOlpStdModInfoBySid(Long sid) {
        try {
            return wbOlpStdModelInfoMapper.deleteWbOlpStdModInfoBySid(sid);
        } catch (Exception e) {
            log.error("deleteWbOlpStdModInfoBySid error", e);
            throw new RuntimeException("删除智慧打线图信息失败，请联系系统负责人!");
        }
    }

    @Override
    public int deleteWbOlpStdModInfoBySids(Long[] sids) {
        try {
            return wbOlpStdModelInfoMapper.deleteWbOlpStdModInfoBySids(sids);
        } catch (Exception e) {
            log.error("deleteWbOlpStdModInfoBySids error", e);
            throw new RuntimeException("批量删除智慧打线图失败，请联系系统负责人!");
        }
    }

    @Override
    public long getModelAvgCnt() {
        try {
            return wbOlpStdModelInfoMapper.getModelAvgCnt();
        } catch (Exception e) {
            log.error("getModelAvgCnt error", e);
            throw new RuntimeException("查询智慧打线图平均线数，请联系系统负责人!");
        }
    }

    @Override
    public long getModelsTtlCnt() {
        try {
            return wbOlpStdModelInfoMapper.getModelsTtlCnt();
        } catch (Exception e) {
            log.error("getModelsTtlCnt error", e);
            throw new RuntimeException("查询智慧打线图比对总数，请联系系统负责人!");
        }
    }

    @DataSource(DataSourceType.SECOND)
    @Override
    public List<WbOlpTrendingVo> getWbOlpTrending() {
        try {
            return wbOlpStdModelInfoMapper.getWbOlpTrending();
        } catch (Exception e) {
            log.error("getWbOlpTrending error", e);
            throw new RuntimeException("查询智慧打线图趋势图，请联系系统负责人!");
        }
    }
}
