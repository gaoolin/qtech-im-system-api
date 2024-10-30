package com.qtech.im.fixture.domain;


import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/03/07 15:17:29
 * desc   :
 */

public class FixtureParamsPoGoPin extends BaseEntity {

    private Long id;
    @Excel(name = "料号")
    private String materialNmb;
    @Excel(name = "品名")
    private String fixtureName;
    @Excel(name = "规格")
    private String fixtureSpec;
    @Excel(name = "连接器朝向", dictType = "fixture_buckle_status")
    private Integer buckle;
    @Excel(name = "治具版本")
    private String fixtureVersion;
    private String prodType;
    @Excel(name = "连接器型号")
    private String connectorModel;
    private Long cId;
    @Excel(name = "治具类别")
    private String fixtureCategory;
    @Excel(name = "连接器总宽")
    private Float connectorTtlWidth;
    @Excel(name = "连接器总长")
    private Float connectorTtlLength;
    @Excel(name = "连接器露铜部宽")
    private Float connectorExposedCopperWidth;
    @Excel(name = "引脚PIN数")
    private Integer pinPins;
    @Excel(name = "引脚PIN间距")
    private Float pinSpacing;
    @Excel(name = "是否有固定PIN", dictType = "fixture_pogopin_fixed_pin")
    private Integer isFixedPin;
    @Excel(name = "连接器钢片宽度")
    private Float connectorSheetWidth;
    @Excel(name = "连接器钢片长度")
    private Float connectorSheetLength;
    @Excel(name = "模组头部宽度")
    private Float moduleHeadWidth;
    @Excel(name = "模组头部长度")
    private Float moduleHeadLength;
    @Excel(name = "模组本体高度")
    private Float moduleBodyHeight;
    @Excel(name = "LENS中心到连接器中心高度")
    private Float heightOfLensCenterToConnectorCenter;
    @Excel(name = "连接器基板厚度")
    private Float connectorSubstrateThickness;
    @Excel(name = "LENS中心到连接器中心左右偏移量")
    private Float leftRightOffsetHeightLensCenterToConnectorCenter;
    @Excel(name = "FOV角度")
    private Float fovAngle;
    @Excel(name = "是否常规FPC", dictType = "fixture_pogopin_is_regular_fpc")
    private Integer isRegularFpc;
    @Excel(name = "FPC最大宽度")
    private Float fpcMaximumWidth;
    @Excel(name = "模组摆放方向", dictType = "fixture_pogopin_module_placement")
    private Integer modulePlacement;
    @Excel(name = "机台", dictType = "fixture_pogopin_mc_id")
    private Integer mcId;
    @Excel(name = "测试工装", dictType = "fixtrue_pogopin_test_fixture")
    private Integer testFixtures;
    @Excel(name = "产品类型", dictType = "fixture_prod_level")
    private Integer prodLevel;

    private Integer fixtureSharedStatus;

    private Long deptId = 209L;

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

    public Integer getBuckle() {
        return buckle;
    }

    public void setBuckle(Integer buckle) {
        this.buckle = buckle;
    }

    public String getFixtureVersion() {
        return fixtureVersion;
    }

    public void setFixtureVersion(String fixtureVersion) {
        this.fixtureVersion = fixtureVersion;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getConnectorModel() {
        return connectorModel;
    }

    public void setConnectorModel(String connectorModel) {
        this.connectorModel = connectorModel;
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

    public Float getConnectorTtlWidth() {
        return connectorTtlWidth;
    }

    public void setConnectorTtlWidth(Float connectorTtlWidth) {
        this.connectorTtlWidth = connectorTtlWidth;
    }

    public Float getConnectorTtlLength() {
        return connectorTtlLength;
    }

    public void setConnectorTtlLength(Float connectorTtlLength) {
        this.connectorTtlLength = connectorTtlLength;
    }

    public Float getConnectorExposedCopperWidth() {
        return connectorExposedCopperWidth;
    }

    public void setConnectorExposedCopperWidth(Float connectorExposedCopperWidth) {
        this.connectorExposedCopperWidth = connectorExposedCopperWidth;
    }

    public Integer getPinPins() {
        return pinPins;
    }

    public void setPinPins(Integer pinPins) {
        this.pinPins = pinPins;
    }

    public Float getPinSpacing() {
        return pinSpacing;
    }

    public void setPinSpacing(Float pinSpacing) {
        this.pinSpacing = pinSpacing;
    }

    public Integer getIsFixedPin() {
        return isFixedPin;
    }

    public void setIsFixedPin(Integer isFixedPin) {
        this.isFixedPin = isFixedPin;
    }

    public Float getConnectorSheetWidth() {
        return connectorSheetWidth;
    }

    public void setConnectorSheetWidth(Float connectorSheetWidth) {
        this.connectorSheetWidth = connectorSheetWidth;
    }

    public Float getConnectorSheetLength() {
        return connectorSheetLength;
    }

    public void setConnectorSheetLength(Float connectorSheetLength) {
        this.connectorSheetLength = connectorSheetLength;
    }

    public Float getModuleHeadWidth() {
        return moduleHeadWidth;
    }

    public void setModuleHeadWidth(Float moduleHeadWidth) {
        this.moduleHeadWidth = moduleHeadWidth;
    }

    public Float getModuleHeadLength() {
        return moduleHeadLength;
    }

    public void setModuleHeadLength(Float moduleHeadLength) {
        this.moduleHeadLength = moduleHeadLength;
    }

    public Float getModuleBodyHeight() {
        return moduleBodyHeight;
    }

    public void setModuleBodyHeight(Float moduleBodyHeight) {
        this.moduleBodyHeight = moduleBodyHeight;
    }

    public Float getHeightOfLensCenterToConnectorCenter() {
        return heightOfLensCenterToConnectorCenter;
    }

    public void setHeightOfLensCenterToConnectorCenter(Float heightOfLensCenterToConnectorCenter) {
        this.heightOfLensCenterToConnectorCenter = heightOfLensCenterToConnectorCenter;
    }

    public Float getConnectorSubstrateThickness() {
        return connectorSubstrateThickness;
    }

    public void setConnectorSubstrateThickness(Float connectorSubstrateThickness) {
        this.connectorSubstrateThickness = connectorSubstrateThickness;
    }

    public Float getLeftRightOffsetHeightLensCenterToConnectorCenter() {
        return leftRightOffsetHeightLensCenterToConnectorCenter;
    }

    public void setLeftRightOffsetHeightLensCenterToConnectorCenter(Float leftRightOffsetHeightLensCenterToConnectorCenter) {
        this.leftRightOffsetHeightLensCenterToConnectorCenter = leftRightOffsetHeightLensCenterToConnectorCenter;
    }

    public Float getFovAngle() {
        return fovAngle;
    }

    public void setFovAngle(Float fovAngle) {
        this.fovAngle = fovAngle;
    }

    public Integer getIsRegularFpc() {
        return isRegularFpc;
    }

    public void setIsRegularFpc(Integer isRegularFpc) {
        this.isRegularFpc = isRegularFpc;
    }

    public Float getFpcMaximumWidth() {
        return fpcMaximumWidth;
    }

    public void setFpcMaximumWidth(Float fpcMaximumWidth) {
        this.fpcMaximumWidth = fpcMaximumWidth;
    }

    public Integer getModulePlacement() {
        return modulePlacement;
    }

    public void setModulePlacement(Integer modulePlacement) {
        this.modulePlacement = modulePlacement;
    }

    public Integer getMcId() {
        return mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public Integer getTestFixtures() {
        return testFixtures;
    }

    public void setTestFixtures(Integer testFixtures) {
        this.testFixtures = testFixtures;
    }

    public Integer getProdLevel() {
        return prodLevel;
    }

    public void setProdLevel(Integer prodLevel) {
        this.prodLevel = prodLevel;
    }

    public Integer getFixtureSharedStatus() {
        return fixtureSharedStatus;
    }

    public void setFixtureSharedStatus(Integer fixtureSharedStatus) {
        this.fixtureSharedStatus = fixtureSharedStatus;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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
        return "FixtureParamsPoGoPin{" +
                "id=" + id +
                ", materialNmb='" + materialNmb + '\'' +
                ", fixtureName='" + fixtureName + '\'' +
                ", fixtureSpec='" + fixtureSpec + '\'' +
                ", buckle=" + buckle +
                ", fixtureVersion='" + fixtureVersion + '\'' +
                ", prodType='" + prodType + '\'' +
                ", connectorModel='" + connectorModel + '\'' +
                ", cId=" + cId +
                ", fixtureCategory='" + fixtureCategory + '\'' +
                ", connectorTtlWidth=" + connectorTtlWidth +
                ", connectorTtlLength=" + connectorTtlLength +
                ", connectorExposedCopperWidth=" + connectorExposedCopperWidth +
                ", pinPins=" + pinPins +
                ", pinSpacing=" + pinSpacing +
                ", isFixedPin=" + isFixedPin +
                ", connectorSheetWidth=" + connectorSheetWidth +
                ", connectorSheetLength=" + connectorSheetLength +
                ", moduleHeadWidth=" + moduleHeadWidth +
                ", moduleHeadLength=" + moduleHeadLength +
                ", moduleBodyHeight=" + moduleBodyHeight +
                ", heightOfLensCenterToConnectorCenter=" + heightOfLensCenterToConnectorCenter +
                ", connectorSubstrateThickness=" + connectorSubstrateThickness +
                ", leftRightOffsetHeightLensCenterToConnectorCenter=" + leftRightOffsetHeightLensCenterToConnectorCenter +
                ", fovAngle=" + fovAngle +
                ", isRegularFpc=" + isRegularFpc +
                ", fpcMaximumWidth=" + fpcMaximumWidth +
                ", modulePlacement=" + modulePlacement +
                ", mcId=" + mcId +
                ", testFixtures=" + testFixtures +
                ", prodLevel=" + prodLevel +
                ", fixtureSharedStatus=" + fixtureSharedStatus +
                ", deptId=" + deptId +
                ", deviation=" + deviation +
                ", remark='" + remark + '\'' +
                '}';
    }
}
