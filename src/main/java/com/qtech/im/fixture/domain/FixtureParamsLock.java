package com.qtech.im.fixture.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/04/16 13:44:40
 * desc   :
 */

public class FixtureParamsLock extends BaseEntity {
    private Long id;
    @Excel(name = "料号")
    private String materialNmb;
    @Excel(name = "品名")
    private String fixtureName;
    @Excel(name = "规格")
    private String fixtureSpec;
    @Excel(name = "版本")
    private String fixtureVersion;
    @JsonProperty("cId")
    private Long cId;
    @Excel(name = "治具类别")
    private String fixtureCategory;
    @Excel(name = "设备型号", dictType = "fixture_lock_mc_id")
    private Integer mcId;
    @Excel(name = "产品类型", dictType = "fixture_lock_prod_level")
    private Integer prodLevel;
    @JsonProperty("vHType")
    @Excel(name = "V/H型号")
    private String vhType;
    @JsonProperty("vHLengthWidth")
    @Excel(name = "V/H长宽")
    private String vhLengthWidth;
    @Excel(name = "VCM Pin类别", dictType = "fixture_lock_vcm_pin_category")
    private Integer vcmPinCategory;
    @JsonProperty("vHInnerDiameter")
    @Excel(name = "V/H内径")
    private Float vhInnerDiameter;
    @Excel(name = "VCM驱动IC")
    private String vcmDriverIc;
    @Excel(name = "Lens型号")
    private String lensModel;
    @Excel(name = "Lens螺牙", dictType = "fixture_lock_lens_thread")
    private Integer LensThread;
    @Excel(name = "Lens外径")
    private Float lensOuterDiameter;
    @Excel(name = "Lens底面外径")
    private Float lensBottomOuterDiameter;
    @Excel(name = "Lens端面直径")
    private Float lensEndDiameter;
    @Excel(name = "Lens花瓣数量", dictType = "fixture_lock_lens_number_of_petals")
    private Integer LensNumberOfPetals;
    private Long deptId = 211L;

    private Integer fixtureSharedStatus;
    private Float deviation;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialNmb() {
        return materialNmb;
    }

    public void setMaterialNmb(String materialNmb) {
        this.materialNmb = materialNmb;
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

    public Long getCId() {
        return cId;
    }

    public void setCId(Long cId) {
        this.cId = cId;
    }

    public String getFixtureCategory() {
        return fixtureCategory;
    }

    public void setFixtureCategory(String fixtureCategory) {
        this.fixtureCategory = fixtureCategory;
    }

    public Integer getMcId() {
        return mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public Integer getProdLevel() {
        return prodLevel;
    }

    public void setProdLevel(Integer prodLevel) {
        this.prodLevel = prodLevel;
    }

    public String getVhType() {
        return vhType;
    }

    public void setVhType(String vhType) {
        this.vhType = vhType;
    }

    public String getVhLengthWidth() {
        return vhLengthWidth;
    }

    public void setVhLengthWidth(String vhLengthWidth) {
        this.vhLengthWidth = vhLengthWidth;
    }

    public Integer getVcmPinCategory() {
        return vcmPinCategory;
    }

    public void setVcmPinCategory(Integer vcmPinCategory) {
        this.vcmPinCategory = vcmPinCategory;
    }

    public Float getVhInnerDiameter() {
        return vhInnerDiameter;
    }

    public void setVhInnerDiameter(Float vhInnerDiameter) {
        this.vhInnerDiameter = vhInnerDiameter;
    }

    public String getVcmDriverIc() {
        return vcmDriverIc;
    }

    public void setVcmDriverIc(String vcmDriverIc) {
        this.vcmDriverIc = vcmDriverIc;
    }

    public String getLensModel() {
        return lensModel;
    }

    public void setLensModel(String lensModel) {
        this.lensModel = lensModel;
    }

    public Integer getLensThread() {
        return LensThread;
    }

    public void setLensThread(Integer lensThread) {
        LensThread = lensThread;
    }

    public Float getLensOuterDiameter() {
        return lensOuterDiameter;
    }

    public void setLensOuterDiameter(Float lensOuterDiameter) {
        this.lensOuterDiameter = lensOuterDiameter;
    }

    public Float getLensBottomOuterDiameter() {
        return lensBottomOuterDiameter;
    }

    public void setLensBottomOuterDiameter(Float lensBottomOuterDiameter) {
        this.lensBottomOuterDiameter = lensBottomOuterDiameter;
    }

    public Float getLensEndDiameter() {
        return lensEndDiameter;
    }

    public void setLensEndDiameter(Float lensEndDiameter) {
        this.lensEndDiameter = lensEndDiameter;
    }

    public Integer getLensNumberOfPetals() {
        return LensNumberOfPetals;
    }

    public void setLensNumberOfPetals(Integer lensNumberOfPetals) {
        LensNumberOfPetals = lensNumberOfPetals;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Integer getFixtureSharedStatus() {
        return fixtureSharedStatus;
    }

    public void setFixtureSharedStatus(Integer fixtureSharedStatus) {
        this.fixtureSharedStatus = fixtureSharedStatus;
    }

    public Float getDeviation() {
        return deviation;
    }

    public void setDeviation(Float deviation) {
        this.deviation = deviation;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FixtureParamsLock{" +
                "id=" + id +
                ", materialNmb='" + materialNmb + '\'' +
                ", fixtureName='" + fixtureName + '\'' +
                ", fixtureSpec='" + fixtureSpec + '\'' +
                ", fixtureVersion='" + fixtureVersion + '\'' +
                ", cId=" + cId +
                ", fixtureCategory='" + fixtureCategory + '\'' +
                ", mcId=" + mcId +
                ", prodLevel=" + prodLevel +
                ", vhType='" + vhType + '\'' +
                ", vhLengthWidth='" + vhLengthWidth + '\'' +
                ", vcmPinCategory=" + vcmPinCategory +
                ", vhInnerDiameter=" + vhInnerDiameter +
                ", vcmDriverIc='" + vcmDriverIc + '\'' +
                ", lensModel='" + lensModel + '\'' +
                ", LensThread=" + LensThread +
                ", lensOuterDiameter=" + lensOuterDiameter +
                ", lensBottomOuterDiameter=" + lensBottomOuterDiameter +
                ", lensEndDiameter=" + lensEndDiameter +
                ", LensNumberOfPetals=" + LensNumberOfPetals +
                ", deptId=" + deptId +
                ", fixtureSharedStatus=" + fixtureSharedStatus +
                ", deviation=" + deviation +
                ", remark='" + remark + '\'' +
                '}';
    }
}
