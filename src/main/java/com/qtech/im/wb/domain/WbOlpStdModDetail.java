package com.qtech.im.wb.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 标准模版明细对象 qtech_wb_comparison_std_mod_detail
 *
 * @author gaozhilin
 * @date 2023-09-06
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class WbOlpStdModDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 来源
     */
    @Excel(name = "来源")
    private String source;

    /**
     * 机型 外键
     */
    @Excel(name = "机型")
    private String mcId;

    /**
     * 线号
     */
    @Excel(name = "线号")
    private Long lineNo;

    /**
     * 坐标位置
     */
    @Excel(name = "lead_X")
    private BigDecimal leadX;

    /**
     * 坐标位置
     */
    @Excel(name = "lead_Y")
    private BigDecimal leadY;

    /**
     * 坐标位置
     */
    @Excel(name = "pad_X")
    private BigDecimal padX;

    /**
     * 坐标位置
     */
    @Excel(name = "pad_Y")
    private BigDecimal padY;

    /**
     * lead点间距
     */
    @Excel(name = "lead点间距")
    private BigDecimal leadDiff;

    /**
     * pad点间距
     */
    @Excel(name = "pad点间距")
    private BigDecimal padDiff;

    /**
     * lead点卡控
     */
    @Excel(name = "lead点卡控")
    private Float leadThreshold;

    /**
     * pad点卡控
     */
    @Excel(name = "pad点卡控")
    private Float padThreshold;

    /**
     * lead到pad点之间的线长
     */
    @Excel(name = "lead到pad点之间的线长")
    private BigDecimal wireLen;

    /** 外键 */
}
