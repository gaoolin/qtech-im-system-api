package com.qtech.im.wire.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 金线标准用量信息对象 data_clean_db.ads_standard_wirebond_usage
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
public class WireUsageStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 机型 */
    @Excel(name = "机型")
    private String mcId;

    /** 线径 */
    @Excel(name = "线径")
    private String wireWidth;

    /** 标准用量 */
    @Excel(name = "标准用量")
    private String standardWireUsage;

    /** 标签 */
    private String flag;

    /** 备用 */
    private String standby;

    public void setMcId(String mcId)
    {
        this.mcId = mcId;
    }

    public String getMcId()
    {
        return mcId;
    }
    public void setWireWidth(String wireWidth)
    {
        this.wireWidth = wireWidth;
    }

    public String getWireWidth()
    {
        return wireWidth;
    }
    public void setStandardWireUsage(String standardWireUsage)
    {
        this.standardWireUsage = standardWireUsage;
    }

    public String getStandardWireUsage()
    {
        return standardWireUsage;
    }
    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getFlag()
    {
        return flag;
    }
    public void setStandby(String standby)
    {
        this.standby = standby;
    }

    public String getStandby()
    {
        return standby;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mcId", getMcId())
            .append("wireWidth", getWireWidth())
            .append("standardWireUsage", getStandardWireUsage())
            .append("flag", getFlag())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("standby", getStandby())
            .toString();
    }
}
