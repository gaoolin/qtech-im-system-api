package com.qtech.im.aa.service.impl.statistics;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.mapper.statistics.AaListParamsParticularsMapper;
import com.qtech.im.aa.service.IAaListParamsParticularsService;
import com.qtech.im.aa.vo.statistics.AaListParamsParticularsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/16 11:47:37
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class AaListParamsParticularsServiceImpl extends ServiceImpl<AaListParamsParticularsMapper, AaListParamsParticularsVo> implements IAaListParamsParticularsService {
    @Autowired
    private AaListParamsParticularsMapper aaListParamsParticularsMapper;

    /**
     * @param aaListParamsParticularsVo
     * @return
     */
    @Override
    public List<AaListParamsParticularsVo> selectAaParamsParticalursList(AaListParamsParticularsVo aaListParamsParticularsVo) {
        try {
            return aaListParamsParticularsMapper.getAll(aaListParamsParticularsVo);
        } catch (Exception e) {
            log.error("selectAaParamsParticularsList error", e); // 记录完整的异常堆栈跟踪
            throw new RuntimeException("查询数据时发生异常，请联系管理员！", e); // 保留原始异常
        }
    }
}