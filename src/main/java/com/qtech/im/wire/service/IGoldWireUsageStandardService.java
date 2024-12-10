package com.qtech.im.wire.service;

import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wire.domain.ImStandardGoldWireUsage;
import com.qtech.im.wire.vo.ImStandardGoldWireUsageVo;

import java.util.List;

/**
 * 金线标准用量信息Service接口
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
public interface IGoldWireUsageStandardService {
    /**
     * 查询金线标准用量信息
     *
     * @param prodTypes 金线标准用量信息主键
     * @return 金线标准用量信息
     */
    public List<ImStandardGoldWireUsage> listByProdType(List<String> prodTypes);

    /**
     * 查询金线标准用量信息列表
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 金线标准用量信息集合
     */
    public QtechImVoUtil.QtechImVos<ImStandardGoldWireUsageVo> list(ImStandardGoldWireUsage imWireUsageStandard);

    /**
     * 新增金线标准用量信息
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 结果
     */
    int addOne(ImStandardGoldWireUsageVo imWireUsageStandard);

    /**
     * 修改金线标准用量信息
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 结果
     */
    public int update(ImStandardGoldWireUsage imWireUsageStandard);

    /**
     * 删除金线标准用量信息信息
     *
     * @param prodType 金线标准用量信息主键
     * @return 结果
     */
    public int remove(String prodType);

    /**
     * @param imWireUsageStandards
     * @return int
     * @description 批量导入金线标准用量数据
     */
    public String addAll(List<ImStandardGoldWireUsageVo> imWireUsageStandards);

    public ImStandardGoldWireUsage getByProdType(String prodType);
}
