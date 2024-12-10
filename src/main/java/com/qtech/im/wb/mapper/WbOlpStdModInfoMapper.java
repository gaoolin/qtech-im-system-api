package com.qtech.im.wb.mapper;

import com.qtech.im.wb.domain.WbOlpStdModInfo;
import com.qtech.im.wb.domain.WbOlpTrending;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 智慧打线图Mapper接口
 *
 * @author gaozhilin
 * @date 2023-09-05
 */
@Repository
public interface WbOlpStdModInfoMapper
{
    /**
     * 查询智慧打线图
     *
     * @param sid 智慧打线图主键
     * @return 智慧打线图
     */
    public WbOlpStdModInfo selectWbOlpStdModInfoBySid(Long sid);

    /**
     * 查询智慧打线图列表
     *
     * @param wbOlpStdModInfo 智慧打线图
     * @return 智慧打线图集合
     */
    public List<WbOlpStdModInfo> selectWbOlpStdModInfoList(WbOlpStdModInfo wbOlpStdModInfo);

    /**
     * 新增智慧打线图
     *
     * @param wbOlpStdModInfo 智慧打线图
     * @return 结果
     */
    public int insertWbOlpStdModInfo(WbOlpStdModInfo wbOlpStdModInfo);

    /**
     * 修改智慧打线图
     *
     * @param wbOlpStdModInfo 智慧打线图
     * @return 结果
     */
    public int updateWbOlpStdModInfo(WbOlpStdModInfo wbOlpStdModInfo);

    /**
     * 删除智慧打线图
     *
     * @param sid 智慧打线图主键
     * @return 结果
     */
    public int deleteWbOlpStdModInfoBySid(Long sid);

    /**
     * 批量删除智慧打线图
     *
     * @param sids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWbOlpStdModInfoBySids(Long[] sids);

    Integer getModelsTtlCnt();

    Integer getModelAvgCnt();

    List<WbOlpTrending> getWbOlpTrending();
}
