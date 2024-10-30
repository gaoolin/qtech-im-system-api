package com.qtech.im.wire.mapper;

import com.qtech.im.wire.domain.WireUsageStandard;
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
     * @param mcId 金线标准用量信息主键
     * @return 金线标准用量信息
     */
    public WireUsageStandard selectWireUsageStandardByMcId(String mcId);

    /**
     * 查询金线标准用量信息列表
     *
     * @param wireUsageStandard 金线标准用量信息
     * @return 金线标准用量信息集合
     */
    public List<WireUsageStandard> selectWireUsageStandardList(WireUsageStandard wireUsageStandard);

    /**
     * 新增金线标准用量信息
     *
     * @param wireUsageStandard 金线标准用量信息
     * @return 结果
     */
    public int insertWireUsageStandard(WireUsageStandard wireUsageStandard);

    /**
     * 修改金线标准用量信息
     *
     * @param wireUsageStandard 金线标准用量信息
     * @return 结果
     */
    public int updateWireUsageStandard(WireUsageStandard wireUsageStandard);

    /**
     * 删除金线标准用量信息
     *
     * @param mcId 金线标准用量信息主键
     * @return 结果
     */
    public int deleteWireUsageStandardByMcId(String mcId);

}
