package com.qtech.im.wire.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;
import com.qtech.im.wire.domain.ImStandardGoldWireUsage;
import lombok.*;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/10 11:08:00
 * desc   :  金线标准用量
 */

@Data
@AllArgsConstructor
@NoArgsConstructor  // 导入Excel时，需要无参构造函数
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ImStandardGoldWireUsageVo extends BaseEntity {
    @Excel(name = "机型")
    private String prodType;
    @Excel(name = "线径")
    private String wireWidth;
    @Excel(name = "标准用量")
    private Double standardWireUsage;

    public ImStandardGoldWireUsageVo(ImStandardGoldWireUsage imStandardGoldWireUsage) {
        this.prodType = imStandardGoldWireUsage.getProdType();
        this.wireWidth = imStandardGoldWireUsage.getWireWidth();
        this.standardWireUsage = imStandardGoldWireUsage.getStandardWireUsage();
        super.setCreateBy(imStandardGoldWireUsage.getCreateBy());
        super.setCreateTime(imStandardGoldWireUsage.getCreateTime());
        super.setUpdateBy(imStandardGoldWireUsage.getUpdateBy());
        super.setUpdateTime(imStandardGoldWireUsage.getUpdateTime());
        super.setRemark(imStandardGoldWireUsage.getRemark());
    }
}
