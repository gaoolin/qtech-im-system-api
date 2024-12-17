package com.qtech.im.aa.service.impl.statistics;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.mapper.statistics.AaListParamsPercentageMapper;
import com.qtech.im.aa.service.statistics.IAaListParamsPercentageService;
import com.qtech.im.aa.vo.statistics.AaListParamsPercentageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/13 10:14:47
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class AaListParamsPercentageServiceImpl extends ServiceImpl<AaListParamsPercentageMapper, AaListParamsPercentageVo> implements IAaListParamsPercentageService {
    @Autowired
    private AaListParamsPercentageMapper aaListParamsPercentageMapper;

    @Override
    public boolean saveBatch(List<AaListParamsPercentageVo> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public boolean saveOrUpdateBatch(List<AaListParamsPercentageVo> entityList, int batchSize) {
        return super.saveOrUpdateBatch(entityList, batchSize);
    }

    @Override
    public List<AaListParamsPercentageVo> selectAaParamsPercentageList(AaListParamsPercentageVo aaListParamsPercentageVo) {
        try {
            return aaListParamsPercentageMapper.selectAaParamsPercentageList(aaListParamsPercentageVo);
        } catch (Exception e) {
            log.error("selectAaParamsPercentageList error", e);
            throw new RuntimeException("查询数据时发生异常，请联系系统管理员！");
        }
    }
}