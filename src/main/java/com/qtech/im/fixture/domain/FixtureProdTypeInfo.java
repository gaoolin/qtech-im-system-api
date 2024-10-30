package com.qtech.im.fixture.domain;


import com.qtech.framework.web.domain.BaseEntity;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/20 13:49:32
 * desc   :  共享机型实体类
 */


public class FixtureProdTypeInfo extends BaseEntity {

    private Long pId;

    private String prodType;

    private Long deptId;

    private Long userId;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FixtureProdTypeInfo{" +
                "pId=" + pId +
                ", prodType='" + prodType + '\'' +
                ", deptId=" + deptId +
                ", userId=" + userId +
                '}';
    }
}
