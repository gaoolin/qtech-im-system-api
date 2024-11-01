package com.qtech.im.aa.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaStdProgramManagementVo;
import com.qtech.im.aa.mapper.AaStdProgramManagementMapper;
import com.qtech.im.aa.service.IAaStdProgramManagementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/10 09:08:08
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.FOURTH)
@Service
public class AaStdProgramManagementServiceImpl implements IAaStdProgramManagementService {
    private final AaStdProgramManagementMapper aaStdProgramManagementMapper;

    @Autowired
    public AaStdProgramManagementServiceImpl(AaStdProgramManagementMapper aaStdProgramManagementMapper) {
        this.aaStdProgramManagementMapper = aaStdProgramManagementMapper;
    }

    @Override
    public AaStdProgramManagementVo selectAaStdProgramManagementById(Long id) {
        return aaStdProgramManagementMapper.selectAaStdProgramManagementById(id);
    }

    @Override
    public List<AaStdProgramManagementVo> selectAaStdProgramManagementList(AaStdProgramManagementVo aaStdProgramManagementVo) {
        try {
            return aaStdProgramManagementMapper.selectAaStdProgramManagementList(aaStdProgramManagementVo);
        } catch (Exception e) {
            log.error("查询数据库发生异常，请联系系统负责人。", e);
            throw new RuntimeException("查询数据库发生异常，请联系系统负责人。");
        }
    }

    @Override
    public AaStdProgramManagementVo selectOneAaStdProgramManagement(AaStdProgramManagementVo aaStdProgramManagementVo) {
        List<AaStdProgramManagementVo> list = aaStdProgramManagementMapper.selectAaStdProgramManagementList(aaStdProgramManagementVo);
        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            if (size > 1) {
                throw new TooManyResultsException(String.format("Expected one result (or null) to be returned by selectOne(), but found: %s", size));
            }
            return list.get(0);
        }
        return null;
    }

    @Override
    public int insertAaStdProgramManagement(AaStdProgramManagementVo aaStdProgramManagementVo) {
        try {
            return aaStdProgramManagementMapper.insertAaStdProgramManagement(aaStdProgramManagementVo);
        } catch (Exception e) {
            log.error("插入数据库发生异常，请联系系统负责人。", e);
            throw new RuntimeException("插入数据库发生异常，请联系系统负责人。");
        }
    }

    @Override
    public int updateAaStdProgramManagement(AaStdProgramManagementVo aaStdProgramManagementVo) {
        return aaStdProgramManagementMapper.updateAaStdProgramManagement(aaStdProgramManagementVo);
    }

    @Override
    public int deleteAaStdProgramManagementById(Long id) {
        try {
            return aaStdProgramManagementMapper.deleteAaStdProgramManagementById(id);
        } catch (Exception e) {
            throw new RuntimeException("删除数据发生异常，请联系系统负责人。");
        }
    }

    @Override
    public int deleteAaStdProgramManagementByIds(Long[] ids) {
        return 0;
    }
}
