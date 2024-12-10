package com.qtech.im.common.util;

import com.github.pagehelper.PageInfo;
import com.qtech.common.constant.HttpStatus;
import com.qtech.framework.web.page.PageDomain;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.framework.web.page.TableSupport;

import java.util.List;

/**
 * 作者: gaozhilin
 * 邮箱: gaoolin@gmail.com
 * 日期: 2024/12/09 08:52:29
 * 描述: 解决PageHelper只能用第一次查询数据来分页，用于处理Vo转换时的分页
 */
public class QtechImVoUtil {

    /**
     * 根据页码和每页大小对列表进行分页
     *
     * @param list     列表数据
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 分页后的列表
     */
    public static List<?> qtechImVoStartPage(List<?> list, Integer pageNum, Integer pageSize) {
        if (list == null || pageNum == null || pageSize == null || pageNum < 1 || pageSize < 1) {
            return null;
        }
        if (list.isEmpty()) {
            return null;
        }
        int count = list.size();
        int fromIndex = (pageNum - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, count);

        if (fromIndex >= count) {
            return null;
        }

        return list.subList(fromIndex, toIndex);
    }

    /**
     * 获取分页数据的响应
     *
     * @param list 列表数据
     * @return 分页数据的响应
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected static TableDataInfo getDataTable(List<?> list) {
        try {
            PageDomain pageDomain = TableSupport.buildPageRequest();
            Integer pageNum = pageDomain.getPageNum();
            Integer pageSize = pageDomain.getPageSize();
            return buildTableDataInfo(list, pageNum, pageSize);
        } catch (Exception e) {
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(HttpStatus.ERROR);
            rspData.setMsg("查询失败: " + e.getMessage());
            return rspData;
        }
    }

    /**
     * 构建分页数据的响应
     *
     * @param list     列表数据
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 分页数据的响应
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static TableDataInfo buildTableDataInfo(List<?> list, Integer pageNum, Integer pageSize) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(qtechImVoStartPage(list, pageNum, pageSize));
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 获取 Vo 数据的分页响应
     *
     * @param qtechImVoResponse Vo 数据
     * @return 分页数据的响应
     */
    public static TableDataInfo getVoDataTable(QtechImVos<?> qtechImVoResponse) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(qtechImVoResponse.getData());
        rspData.setTotal(qtechImVoResponse.getTotal());
        return rspData;
    }

    /**
     * Vo 数据的包装类
     */
    public static class QtechImVos<T> {
        private List<T> data;
        private long total;

        /**
         * 构造函数
         *
         * @param data  数据列表
         * @param total 总数
         */
        public QtechImVos(List<T> data, long total) {
            this.data = data;
            this.total = total;
        }

        /**
         * 获取数据列表
         *
         * @return 数据列表
         */
        public List<T> getData() {
            return data;
        }

        /**
         * 设置数据列表
         *
         * @param data 数据列表
         */
        public void setData(List<T> data) {
            this.data = data;
        }

        /**
         * 获取总数
         *
         * @return 总数
         */
        public long getTotal() {
            return total;
        }

        /**
         * 设置总数
         *
         * @param total 总数
         */
        public void setTotal(long total) {
            this.total = total;
        }
    }
}
