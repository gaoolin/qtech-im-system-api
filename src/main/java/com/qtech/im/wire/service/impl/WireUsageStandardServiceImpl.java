package com.qtech.im.wire.service.impl;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.exception.WireBizImportException;
import com.qtech.im.wire.domain.ImWireUsageStandard;
import com.qtech.im.wire.mapper.WireUsageStandardMapper;
import com.qtech.im.wire.service.IWireUsageStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.qtech.common.utils.SecurityUtils.getLoginUser;

/**
 * 金线标准用量信息Service业务层处理
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
@DataSource(value = DataSourceType.SECOND)
@Service
public class WireUsageStandardServiceImpl implements IWireUsageStandardService {
    @Autowired
    private WireUsageStandardMapper wireUsageStandardMapper;

    /**
     * 查询金线标准用量信息
     *
     * @param prodType 金线标准用量信息主键
     * @return 金线标准用量信息
     */
    @Override
    public ImWireUsageStandard selectWireUsageStandardByProdType(String prodType) {
        return wireUsageStandardMapper.selectWireUsageStandardByProdType(prodType);
    }

    /**
     * 查询金线标准用量信息列表
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 金线标准用量信息
     */
    @Override
    public List<ImWireUsageStandard> selectWireUsageStandardList(ImWireUsageStandard imWireUsageStandard) {
        return wireUsageStandardMapper.selectWireUsageStandardList(imWireUsageStandard);
    }

    /**
     * 新增金线标准用量信息
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 结果
     */
    @Override
    public int insertWireUsageStandard(ImWireUsageStandard imWireUsageStandard) {
        imWireUsageStandard.setCreateTime(DateUtils.getNowDate());
        return wireUsageStandardMapper.insertWireUsageStandard(imWireUsageStandard);
    }

    /**
     * 修改金线标准用量信息
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 结果
     */
    @Override
    public int updateWireUsageStandard(ImWireUsageStandard imWireUsageStandard) {
        imWireUsageStandard.setUpdateTime(DateUtils.getNowDate());
        imWireUsageStandard.setFlag("1");
        return wireUsageStandardMapper.updateWireUsageStandard(imWireUsageStandard);
    }

    /**
     * 删除金线标准用量信息信息
     *
     * @param prodType 金线标准用量信息主键
     * @return 结果
     */
    @Override
    public int deleteWireUsageStandardByProdType(String prodType) {
        return wireUsageStandardMapper.deleteWireUsageStandardByProdType(prodType);
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public String importWireUsageStandard(List<ImWireUsageStandard> imWireUsageStandards) {
        String nickName = getLoginUser().getUser().getNickName();

        int insert = 0;
        int exist = 0;

        if (StringUtils.isNull(imWireUsageStandards) || imWireUsageStandards.isEmpty()) {
            throw new WireBizImportException();
        }

        for (ImWireUsageStandard imWireUsageStandard : imWireUsageStandards) {
            ImWireUsageStandard p = new ImWireUsageStandard();
            p.setMcId(imWireUsageStandard.getMcId());
            List<ImWireUsageStandard> isExist = wireUsageStandardMapper.selectWireUsageStandardList(p);
            if (StringUtils.isNull(isExist) || isExist.isEmpty()) {
                imWireUsageStandard.setCreateBy(nickName);
                imWireUsageStandard.setCreateTime(DateUtils.getNowDate());
                int i = wireUsageStandardMapper.insertWireUsageStandard(imWireUsageStandard);
                insert = insert + 1;
            } else {
                exist = exist + 1;
            }
        }
        return String.valueOf(insert) + "-" + String.valueOf(exist);
    }
}
