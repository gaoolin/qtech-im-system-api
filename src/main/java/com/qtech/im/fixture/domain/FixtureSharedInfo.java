package com.qtech.im.fixture.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * pogopin治具对象 fixture_pogopin_shared_info_prod
 *
 * @author gaozhilin
 * @date 2023-06-27
 */
public class FixtureSharedInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用于修改映射表数据的主键
     */
    private Long id;

    /**
     * 用于插入历史数据的主键
     */
    private Long hid;

    /**
     * 料号主键
     */
    private Long mId;

    /**
     * 治具类型主键
     */
    private Long cId;

    /**
     * 共享机型主键
     */
    private Long pId;

    /**
     * 料号
     */
    @Excel(name = "料号")
    private String materialNmb;

    /**
     * 品名
     */
    @Excel(name = "品名")
    private String fixtureName;

    /**
     * 治具规格
     */
    @Excel(name = "规格")
    private String fixtureSpec;

    /**
     * 治具类别
     */
    @Excel(name = "治具类型")
    private String fixtureCategory;

    /**
     * 机种
     */
    @Excel(name = "机种")
    private String prodType;

    /**
     * 治具版本
     */
    @Excel(name = "治具版本")
    private String fixtureVersion;

    /**
     * 治具类别
     */
    @Excel(name = "连接器朝向" , dictType = "fixture_buckle_status")
    private Integer buckle;

    ///** 备注 */
    //@Excel(name = "备注")
    // private String remark;

    /**
     * 部门id 权限
     */
    @Excel(name = "治具类别(pogopin/AA/锁附)" , readConverterExp = "209=pogopin,210=AA,211=锁附")
    private Long deptId;

    /**
     * 用户id 权限
     */
    private Long userId;

    /**
     * 操作系统时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date opsTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public Long getCId() {
        return cId;
    }

    public void setCId(Long cId) {
        this.cId = cId;
    }

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

    public String getFixtureCategory() {
        return fixtureCategory;
    }

    public void setFixtureCategory(String fixtureCategory) {
        this.fixtureCategory = fixtureCategory;
    }

    public Integer getBuckle() {
        return buckle;
    }

    public void setBuckle(Integer buckle) {
        this.buckle = buckle;
    }

    public String getFixtureName() {
        return fixtureName;
    }

    public void setFixtureName(String fixtureName) {
        this.fixtureName = fixtureName;
    }

    public String getFixtureSpec() {
        return fixtureSpec;
    }

    public void setFixtureSpec(String fixtureSpec) {
        this.fixtureSpec = fixtureSpec;
    }

    public String getFixtureVersion() {
        return fixtureVersion;
    }

    public void setFixtureVersion(String fixtureVersion) {
        this.fixtureVersion = fixtureVersion;
    }

    public String getMaterialNmb() {
        return materialNmb;
    }

    public void setMaterialNmb(String materialNmb) {
        this.materialNmb = materialNmb;
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

    public Date getOpsTime() {
        return opsTime;
    }

    public void setOpsTime(Date opsTime) {
        this.opsTime = opsTime;
    }

    @Override
    public String toString() {
        return "FixtureSharedInfo{" +
                "id=" + id +
                ", hid=" + hid +
                ", mId=" + mId +
                ", cId=" + cId +
                ", pId=" + pId +
                ", prodType='" + prodType + '\'' +
                ", fixtureCategory='" + fixtureCategory + '\'' +
                ", buckle=" + buckle +
                ", fixtureName='" + fixtureName + '\'' +
                ", fixtureSpec='" + fixtureSpec + '\'' +
                ", fixtureVersion='" + fixtureVersion + '\'' +
                ", materialNmb='" + materialNmb + '\'' +
                ", deptId=" + deptId +
                ", userId=" + userId +
                ", opsTime=" + opsTime +
                '}';
    }
}
