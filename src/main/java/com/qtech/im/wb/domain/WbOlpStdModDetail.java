package com.qtech.im.wb.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 标准模版明细对象 qtech_wb_comparison_std_mod_detail
 *
 * @author gaozhilin
 * @date 2023-09-06
 */
public class WbOlpStdModDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 机型 外键 */
    @Excel(name = "机型")
    private String mcId;

    /** 线号 */
    @Excel(name = "线号")
    private Long lineNo;

    /** 坐标位置 */
    @Excel(name = "lead_X")
    private BigDecimal leadX;

    /** 坐标位置 */
    @Excel(name = "lead_Y")
    private BigDecimal leadY;

    /** 坐标位置 */
    @Excel(name = "pad_X")
    private BigDecimal padX;

    /** 坐标位置 */
    @Excel(name = "pad_Y")
    private BigDecimal padY;

    /** lead点间距 */
    @Excel(name = "lead点间距")
    private BigDecimal leadDiff;

    /** pad点间距 */
    @Excel(name = "pad点间距")
    private BigDecimal padDiff;

    /** lead点卡控 */
    @Excel(name = "lead点卡控")
    private float leadThreshold;

    /** pad点卡控 */
    @Excel(name = "pad点卡控")
    private float padThreshold;

    /** lead到pad点之间的线长 */
    @Excel(name = "lead到pad点之间的线长")
    private BigDecimal wireLen;

    /** 外键 */

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSource(String source)
    {
        this.source = source;
    }

    public String getSource()
    {
        return source;
    }
    public void setMcId(String mcId)
    {
        this.mcId = mcId;
    }

    public String getMcId()
    {
        return mcId;
    }
    public void setLineNo(Long lineNo)
    {
        this.lineNo = lineNo;
    }

    public Long getLineNo()
    {
        return lineNo;
    }
    public void setLeadX(BigDecimal leadX)
    {
        this.leadX = leadX;
    }

    public BigDecimal getLeadX()
    {
        return leadX;
    }
    public void setLeadY(BigDecimal leadY)
    {
        this.leadY = leadY;
    }

    public BigDecimal getLeadY()
    {
        return leadY;
    }
    public void setPadX(BigDecimal padX)
    {
        this.padX = padX;
    }

    public BigDecimal getPadX()
    {
        return padX;
    }
    public void setPadY(BigDecimal padY)
    {
        this.padY = padY;
    }

    public BigDecimal getPadY()
    {
        return padY;
    }
    public void setLeadDiff(BigDecimal leadDiff)
    {
        this.leadDiff = leadDiff;
    }

    public BigDecimal getLeadDiff()
    {
        return leadDiff;
    }
    public void setPadDiff(BigDecimal padDiff)
    {
        this.padDiff = padDiff;
    }

    public BigDecimal getPadDiff()
    {
        return padDiff;
    }
    public void setLeadThreshold(float leadThreshold)
    {
        this.leadThreshold = leadThreshold;
    }

    public float getLeadThreshold()
    {
        return leadThreshold;
    }
    public void setPadThreshold(float padThreshold)
    {
        this.padThreshold = padThreshold;
    }

    public float getPadThreshold()
    {
        return padThreshold;
    }
    public void setWireLen(BigDecimal wireLen)
    {
        this.wireLen = wireLen;
    }

    public BigDecimal getWireLen()
    {
        return wireLen;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("source", getSource())
            .append("mcId", getMcId())
            .append("lineNo.", getLineNo())
            .append("leadX", getLeadX())
            .append("leadY", getLeadY())
            .append("padX", getPadX())
            .append("padY", getPadY())
            .append("leadDiff", getLeadDiff())
            .append("padDiff", getPadDiff())
            .append("leadThreshold", getLeadThreshold())
            .append("padThreshold", getPadThreshold())
            .append("wireLen", getWireLen())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
