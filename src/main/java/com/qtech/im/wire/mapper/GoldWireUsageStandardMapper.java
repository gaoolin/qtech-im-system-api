package com.qtech.im.wire.mapper;

import com.qtech.im.wire.domain.ImStandardGoldWireUsage;
import com.qtech.im.wire.vo.ImStandardGoldWireUsageVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 金线标准用量信息Mapper接口
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
@Repository
public interface GoldWireUsageStandardMapper
{
    /**
     * 查询金线标准用量信息
     *
     * @param prodTypes 金线标准用量信息主键
     * @return 金线标准用量信息
     */
    public List<ImStandardGoldWireUsage> listByProdTypes(List<String> prodTypes);

    /**
     * 查询金线标准用量信息列表
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 金线标准用量信息集合
     */
    public List<ImStandardGoldWireUsage> list(ImStandardGoldWireUsage imWireUsageStandard);

    /**
     * 新增金线标准用量信息
     *
     * @param imStandardGoldWireUsage 金线标准用量信息
     * @return 结果
     */
    public int addOne(ImStandardGoldWireUsage imStandardGoldWireUsage);

    public int addAll(List<ImStandardGoldWireUsage> imStandardGoldWireUsages);

    /**
     * 修改金线标准用量信息
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 结果
     */
    public int update(ImStandardGoldWireUsage imWireUsageStandard);

    /**
     * 删除金线标准用量信息
     *
     * @param prodType 金线标准用量信息主键
     * @return 结果
     */
    public int removeByProdType(String prodType);

    public ImStandardGoldWireUsage getByProdType(String prodType);
}
