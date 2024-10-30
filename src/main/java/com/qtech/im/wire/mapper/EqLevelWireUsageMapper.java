package com.qtech.im.wire.mapper;

import com.qtech.im.wire.domain.EqLevelWireUsage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 金线消耗量Mapper接口
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
@Repository
public interface EqLevelWireUsageMapper {
    /**
     * 查询金线消耗量列表
     *
     * @param eqLevelWireUsage 金线消耗量
     * @return 金线消耗量集合
     */
    public List<EqLevelWireUsage> selectEqLevelWireUsageList(EqLevelWireUsage eqLevelWireUsage);

}
