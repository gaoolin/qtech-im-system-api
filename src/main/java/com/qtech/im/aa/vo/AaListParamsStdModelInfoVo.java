package com.qtech.im.aa.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.aa.domain.AaListParamsStdTemplateInfo;
import lombok.Data;

import java.text.SimpleDateFormat;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/05 16:45:16
 * desc   :
 */
@Data
public class AaListParamsStdModelInfoVo {
    private Long id;
    @Excel(name = "机型")
    private String prodType;
    @Excel(name = "list参数个数")
    private Integer listParams;
    @Excel(name = "item参数个数")
    private Integer itemParams;
    @Excel(name = "状态")
    private Integer status;
    @Excel(name = "提供人")
    private String provider;
    @Excel(name = "归属")
    private String belongTo;
    @Excel(name = "创建人")
    private String createBy;
    @Excel(name = "创建时间")
    private String createTime;
    @Excel(name = "更新人")
    private String updateBy;
    @Excel(name = "更新时间")
    private String updateTime;
    @Excel(name = "备注")
    private String remark;

    public AaListParamsStdModelInfoVo(AaListParamsStdTemplateInfo aaListParamsStdTemplateInfo) {
        this.id = aaListParamsStdTemplateInfo.getId();
        this.prodType = aaListParamsStdTemplateInfo.getProdType();
        this.listParams = aaListParamsStdTemplateInfo.getListParams();
        this.itemParams = aaListParamsStdTemplateInfo.getItemParams();
        this.status = aaListParamsStdTemplateInfo.getStatus();
        this.provider = aaListParamsStdTemplateInfo.getProvider();
        this.belongTo = aaListParamsStdTemplateInfo.getBelongTo();
        this.createBy = aaListParamsStdTemplateInfo.getCreateBy();
        this.createTime = aaListParamsStdTemplateInfo.getCreateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aaListParamsStdTemplateInfo.getCreateTime());
        this.updateBy = aaListParamsStdTemplateInfo.getUpdateBy();
        this.updateTime = aaListParamsStdTemplateInfo.getUpdateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aaListParamsStdTemplateInfo.getUpdateTime());
        this.remark = aaListParamsStdTemplateInfo.getRemark();
    }
}
