package com.qtech.im.wb.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wb.domain.WbOlpStdModDetail;
import com.qtech.im.wb.domain.WbOlpStdModUpload;
import com.qtech.im.wb.mapper.WbOlpStdModUploadMapper;
import com.qtech.im.wb.mapper.WbOlpStdModelDetailMapper;
import com.qtech.im.wb.service.IWbOlpStdModUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/09/08 16:18:20
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class WbOlpStdModUploadServiceImpl implements IWbOlpStdModUploadService {

    @Autowired
    WbOlpStdModUploadMapper wbOlpStdModUploadMapper;

    @Autowired
    private WbOlpStdModelDetailMapper wbOlpStdModelDetailMapper;

    @Override
    public List<WbOlpStdModUpload> selectWbOlpStdModUploadList(WbOlpStdModUpload wbOlpStdModUpload) {
        try {
            return wbOlpStdModUploadMapper.selectWbOlpStdModUploadList(wbOlpStdModUpload);
        } catch (Exception e) {
            log.error("查询标准模组上传数据异常", e);
            throw new RuntimeException("查询标准模组上传数据异常，请联系系统负责人!");
        }
    }

    @Override
    public List<WbOlpStdModDetail> selectWbOlpStdModUploadMockList(String simId, String mcId, String pId, String beginTime, String endTime, String delLineNo) {
        List<Integer> delLineNoList = new ArrayList<>();
        if (!"".equals(delLineNo)) {
            String[] split = delLineNo.split(",");
            for (String s : split) {
                delLineNoList.add(Integer.parseInt(s));
            }
        }
        try {
            return wbOlpStdModUploadMapper.selectWbOlpStdModUploadMockList(simId, mcId, pId, beginTime, endTime, delLineNoList);
        } catch (Exception e) {
            log.error("查询标准模组上传数据异常", e);
            throw new RuntimeException("查询标准模组上传数据异常，请联系系统负责人!");
        }
    }

    @Override
    public Integer addWbOlpStdModUploadMock(List<WbOlpStdModDetail> wbOlpStdModDetails, String leadThreshold, String padThreshold) {
        for (WbOlpStdModDetail wbOlpStdModDetail : wbOlpStdModDetails) {
            wbOlpStdModDetail.setSource("数采模版");
            wbOlpStdModDetail.setLeadThreshold(Float.parseFloat(leadThreshold));
            wbOlpStdModDetail.setPadThreshold(Float.parseFloat(padThreshold));
        }

        Integer cnt = 0;
        for (WbOlpStdModDetail wbOlpStdModDetail : wbOlpStdModDetails) {
            Integer i = null;
            try {
                i = wbOlpStdModelDetailMapper.insertWbOlpStdModDetail(wbOlpStdModDetail);
            } catch (Exception e) {
                log.error("插入标准模组上传数据异常", e);
                throw new RuntimeException("插入标准模组上传数据异常，请联系系统负责人!");
            }
            cnt = cnt + i;
        }
        return cnt;
    }
}
