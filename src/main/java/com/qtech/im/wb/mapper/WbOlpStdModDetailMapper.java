package com.qtech.im.wb.mapper;

import com.qtech.im.wb.domain.WbOlpStdModDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 标准模版明细Mapper接口
 *
 * @author gaozhilin
 * @date 2023-09-06
 */
@Repository
public interface WbOlpStdModDetailMapper
{
    /**
     * 查询标准模版明细
     *
     * @param id 标准模版明细主键
     * @return 标准模版明细
     */
    public WbOlpStdModDetail selectWbOlpStdModDetailById(Long id);

    /**
     * 查询标准模版明细列表
     *
     * @param wbOlpStdModDetail 标准模版明细
     * @return 标准模版明细集合
     */
    public List<WbOlpStdModDetail> selectWbOlpStdModDetailList(WbOlpStdModDetail wbOlpStdModDetail);

    /**
     * 新增标准模版明细
     *
     * @param wbOlpStdModDetail 标准模版明细
     * @return 结果
     */
    public int insertWbOlpStdModDetail(WbOlpStdModDetail wbOlpStdModDetail);

    /**
     * 修改标准模版明细
     *
     * @param wbOlpStdModDetail 标准模版明细
     * @return 结果
     */
    public int updateWbOlpStdModDetail(WbOlpStdModDetail wbOlpStdModDetail);

    /**
     * 删除标准模版明细
     *
     * @param id 标准模版明细主键
     * @return 结果
     */
    public int deleteWbOlpStdModDetailById(Long id);

    /**
     * 批量删除标准模版明细
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWbOlpStdModDetailByIds(Long[] ids);

    int countWbOlpStdModDetailByMcId(@Param("mcId") String mcId);
}
