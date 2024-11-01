package com.qtech.im.wire.mapper;

import com.qtech.im.wire.domain.ImWireUsageStandard;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 金线标准用量信息Mapper接口
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
@Repository
public interface WireUsageStandardMapper
{
    /**
     * 查询金线标准用量信息
     *
     * @param prodType 金线标准用量信息主键
     * @return 金线标准用量信息
     */
    public ImWireUsageStandard selectWireUsageStandardByProdType(String prodType);

    /**
     * 查询金线标准用量信息列表
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 金线标准用量信息集合
     */
    public List<ImWireUsageStandard> selectWireUsageStandardList(ImWireUsageStandard imWireUsageStandard);

    /**
     * 新增金线标准用量信息
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 结果
     */
    public int insertWireUsageStandard(ImWireUsageStandard imWireUsageStandard);

    /**
     * 修改金线标准用量信息
     *
     * @param imWireUsageStandard 金线标准用量信息
     * @return 结果
     */
    public int updateWireUsageStandard(ImWireUsageStandard imWireUsageStandard);

    /**
     * 删除金线标准用量信息
     *
     * @param prodType 金线标准用量信息主键
     * @return 结果
     */
    public int deleteWireUsageStandardByProdType(String prodType);

}
