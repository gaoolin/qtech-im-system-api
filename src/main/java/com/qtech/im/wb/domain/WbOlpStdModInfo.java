package com.qtech.im.wb.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 智慧打线图对象 qtech_wb_comparison_std_mod_info
 *
 * @author gaozhilin
 * @date 2023-09-05
 */
public class WbOlpStdModInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long sid;

    /** 机型 */
    @Excel(name = "机型")
    private String mcId;

    /** 线数 */
    @Excel(name = "线数")
    private Integer lineCount;

    /** 状态（1为启用，0为弃用） */
    @Excel(name = "状态", readConverterExp = "1=为启用，0为弃用")
    private Integer status;

    /** 模板数据提供人 */
    @Excel(name = "模板数据提供人")
    private String provider;

    /** 模板所属厂区 */
    @Excel(name = "模板所属厂区")
    private String factory;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    public void setSid(Long sid)
    {
        this.sid = sid;
    }

    public Long getSid()
    {
        return sid;
    }
    public void setMcId(String mcId)
    {
        this.mcId = mcId;
    }

    public String getMcId()
    {
        return mcId;
    }
    public void setLineCount(Integer lineCount)
    {
        this.lineCount = lineCount;
    }

    public Integer getLineCount()
    {
        return lineCount;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setProvider(String provider)
    {
        this.provider = provider;
    }

    public String getProvider()
    {
        return provider;
    }
    public void setFactory(String factory)
    {
        this.factory = factory;
    }

    public String getFactory()
    {
        return factory;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRemark()
    {
        return remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("mcId", getMcId())
            .append("lineCount", getLineCount())
            .append("status", getStatus())
            .append("provider", getProvider())
            .append("factory", getFactory())
            .append("remark", getRemark())
            .toString();
    }
}
