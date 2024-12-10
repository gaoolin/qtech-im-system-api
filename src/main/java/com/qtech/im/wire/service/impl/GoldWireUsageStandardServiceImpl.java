package com.qtech.im.wire.service.impl;

import com.github.pagehelper.PageInfo;
import com.qtech.common.utils.DateUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.framework.security.LoginUser;
import com.qtech.im.common.exception.WireBizImportException;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wire.domain.ImStandardGoldWireUsage;
import com.qtech.im.wire.mapper.GoldWireUsageStandardMapper;
import com.qtech.im.wire.service.IGoldWireUsageStandardService;
import com.qtech.im.wire.vo.ImStandardGoldWireUsageVo;
import com.qtech.project.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.qtech.common.utils.SecurityUtils.getLoginUser;

/**
 * 金线标准用量信息Service业务层处理
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
@Slf4j
@DataSource(value = DataSourceType.SECOND)
@Service
public class GoldWireUsageStandardServiceImpl implements IGoldWireUsageStandardService {
    @Autowired
    private GoldWireUsageStandardMapper goldWireUsageStandardMapper;

    /**
     * @param prodType
     * @return
     */
    @Override
    public ImStandardGoldWireUsage getByProdType(String prodType) {
        return goldWireUsageStandardMapper.getByProdType(prodType);
    }

    /**
     * 查询金线标准用量信息
     *
     * @param prodTypes 金线标准用量信息主键
     * @return 金线标准用量信息
     */
    @Override
    public List<ImStandardGoldWireUsage> listByProdType(List<String> prodTypes) {
        return goldWireUsageStandardMapper.listByProdTypes(prodTypes);
    }

    /**
     * 查询金线标准用量信息列表
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 金线标准用量信息
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public QtechImVoUtil.QtechImVos<ImStandardGoldWireUsageVo> list(ImStandardGoldWireUsage imWireUsageStandard) {
        try {
            ArrayList<ImStandardGoldWireUsageVo> vos = new ArrayList<>();
            List<ImStandardGoldWireUsage> list = goldWireUsageStandardMapper.list(imWireUsageStandard);
            long total = new PageInfo(list).getTotal();
            if (!list.isEmpty()) {
                list.stream().map(ImStandardGoldWireUsageVo::new).collect(Collectors.toList()).forEach(vos::add);
            }
            return new QtechImVoUtil.QtechImVos<>(vos, total);
        } catch (Exception e) {
            log.error("查询金线标准用量信息列表失败", e);
            throw new RuntimeException("查询数据时发生异常，请联系系统负责人！");
        }
    }

    /**
     * 新增金线标准用量信息
     *
     * @param imStandardGoldWireUsageVo 金线标准用量信息
     * @return 结果
     */
    @Override
    public int addOne(ImStandardGoldWireUsageVo imStandardGoldWireUsageVo) {
        ImStandardGoldWireUsage standardGoldWireUsage = new ImStandardGoldWireUsage();
        BeanUtils.copyProperties(imStandardGoldWireUsageVo, standardGoldWireUsage);
        return goldWireUsageStandardMapper.addOne(standardGoldWireUsage);
    }

    /**
     * 修改金线标准用量信息
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 结果
     */
    @Override
    public int update(ImStandardGoldWireUsage imWireUsageStandard) {
        imWireUsageStandard.setUpdateTime(DateUtils.getNowDate());
        imWireUsageStandard.setFlag("1");
        return goldWireUsageStandardMapper.update(imWireUsageStandard);
    }

    /**
     * 删除金线标准用量信息信息
     *
     * @param prodType 金线标准用量信息主键
     * @return 结果
     */
    @Override
    public int remove(String prodType) {
        return goldWireUsageStandardMapper.removeByProdType(prodType);
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public String addAll(List<ImStandardGoldWireUsageVo> imStandardGoldWireUsageVos) {
        // 获取当前登录用户的昵称
        String nickName = Optional.ofNullable(getLoginUser())
                .map(LoginUser::getUser)
                .map(SysUser::getNickName)
                .orElseThrow(() -> new WireBizImportException("获取当前登录用户失败"));

        int insertCount = 0;
        int existCount = 0;

        // 验证输入是否为空
        if (CollectionUtils.isEmpty(imStandardGoldWireUsageVos)) {
            throw new WireBizImportException("输入列表为空");
        }

        // 一次性查询所有已存在的记录
        Set<String> existingProdTypes = new HashSet<>();
        try {
            List<String> prodTypes = imStandardGoldWireUsageVos.stream()
                    .map(ImStandardGoldWireUsageVo::getProdType)
                    .collect(Collectors.toList());

            List<ImStandardGoldWireUsage> existingRecords = goldWireUsageStandardMapper.listByProdTypes(prodTypes);
            existingRecords.forEach(record -> existingProdTypes.add(record.getProdType()));
        } catch (Exception e) {
            throw new RuntimeException("查询已存在记录时发生错误: " + e.getMessage(), e);
        }

        // 批处理插入
        List<ImStandardGoldWireUsage> toInsert = new ArrayList<>();
        for (ImStandardGoldWireUsageVo vo : imStandardGoldWireUsageVos) {
            if (!existingProdTypes.contains(vo.getProdType())) {
                ImStandardGoldWireUsage one = new ImStandardGoldWireUsage();
                BeanUtils.copyProperties(vo, one);
                one.setCreateBy(nickName);
                one.setCreateTime(DateUtils.getNowDate());
                toInsert.add(one);
            } else {
                existCount++;
            }
        }

        if (!toInsert.isEmpty()) {
            try {
                int inserted = goldWireUsageStandardMapper.addAll(toInsert);
                insertCount += inserted;
            } catch (Exception e) {
                throw new RuntimeException("插入记录时发生错误: " + e.getMessage(), e);
            }
        }

        return insertCount + "-" + existCount;
    }

}
