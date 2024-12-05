package com.qtech.im.aa.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.aa.domain.AaListParamsStdModelInfo;
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

    public AaListParamsStdModelInfoVo(AaListParamsStdModelInfo aaListParamsStdModelInfo) {
        this.id = aaListParamsStdModelInfo.getId();
        this.prodType = aaListParamsStdModelInfo.getProdType();
        this.listParams = aaListParamsStdModelInfo.getListParams();
        this.itemParams = aaListParamsStdModelInfo.getItemParams();
        this.status = aaListParamsStdModelInfo.getStatus();
        this.provider = aaListParamsStdModelInfo.getProvider();
        this.belongTo = aaListParamsStdModelInfo.getBelongTo();
        this.createBy = aaListParamsStdModelInfo.getCreateBy();
        this.createTime = aaListParamsStdModelInfo.getCreateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aaListParamsStdModelInfo.getCreateTime());
        this.updateBy = aaListParamsStdModelInfo.getUpdateBy();
        this.updateTime = aaListParamsStdModelInfo.getUpdateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aaListParamsStdModelInfo.getUpdateTime());
        this.remark = aaListParamsStdModelInfo.getRemark();
    }
}
