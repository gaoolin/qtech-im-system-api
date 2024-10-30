package com.qtech.im.fixture.domain;


import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/04/02 13:11:45
 * desc   :  泛型中用此类的反射，不能用注解生成getter、setter、toString，会报没有这个方法的错误。
 */

public class FixtureParamsAa extends BaseEntity {

    private Long id;
    @Excel(name = "料号")
    private String materialNmb;
    @Excel(name = "品名")
    private String fixtureName;
    @Excel(name = "规格")
    private String fixtureSpec;
    @Excel(name = "连接器朝向", dictType = "fixture_aa_buckle_status")
    private Integer buckle;
    @Excel(name = "治具版本")
    private String fixtureVersion;
    private String prodType;
    @Excel(name = "连接器型号")
    private String connectorModel;
    private Long cId;
    @Excel(name = "治具类别")
    private String fixtureCategory;
    private Integer fixtureSharedStatus;
    @Excel(name = "工艺要求", dictType = "fixture_aa_process_requirements")
    private Integer processRequirements;
    @Excel(name = "马达型号")
    private String motorModel;
    @Excel(name = "马达pin朝向", dictType = "fixture_aa_motor_pin_direction")
    private Integer motorPinDirection;
    @Excel(name = "AA夹取方向", dictType = "fixture_aa_clamping_direction")
    private Integer aaClampingDirection;
    @Excel(name = "马达通电pin朝向", dictType = "fixture_aa_motor_pin_direction")
    private Integer motorPowerPinDirection;
    @Excel(name = "Lens型号")
    private String lensModel;
    @Excel(name = "D-FOV角度")
    private Float dFovAngle;
    @Excel(name = "AA距离", dictType = "fixture_aa_distance")
    private Integer aaDistance;
    @Excel(name = "Sensor型号")
    private String sensorModel;
    @Excel(name = "有效像素")
    private String effectivePixels;
    @Excel(name = "模组头部宽度")
    private Float moduleHeadWidth;
    @Excel(name = "模组头部长度")
    private Float moduleHeadLength;
    @Excel(name = "补强板宽度")
    private Float reinforcementBoardWidth;
    @Excel(name = "补强板长度")
    private Float reinforcementPlateLength;
    @Excel(name = "FPC宽度")
    private Float fpcWidth;
    @Excel(name = "FPC长度")
    private Float fpcLength;
    @Excel(name = "保护膜厚度")
    private Float protectiveFilmThickness;
    @Excel(name = "产品摆放头部方向", dictType = "fixture_aa_module_placement")
    private Integer modulePlacement;
    @Excel(name = "设备型号", dictType = "fixture_aa_mc_id")
    private Integer mcId;
    @Excel(name = "产品类型", dictType = "fixture_prod_level")
    private Integer prodLevel;

    private Long deptId = 210L;
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

    public Integer getFixtureSharedStatus() {
        return fixtureSharedStatus;
    }

    public void setFixtureSharedStatus(Integer fixtureSharedStatus) {
        this.fixtureSharedStatus = fixtureSharedStatus;
    }

    public Integer getProcessRequirements() {
        return processRequirements;
    }

    public void setProcessRequirements(Integer processRequirements) {
        this.processRequirements = processRequirements;
    }

    public String getMotorModel() {
        return motorModel;
    }

    public void setMotorModel(String motorModel) {
        this.motorModel = motorModel;
    }

    public Integer getMotorPinDirection() {
        return motorPinDirection;
    }

    public void setMotorPinDirection(Integer motorPinDirection) {
        this.motorPinDirection = motorPinDirection;
    }

    public Integer getAaClampingDirection() {
        return aaClampingDirection;
    }

    public void setAaClampingDirection(Integer aaClampingDirection) {
        this.aaClampingDirection = aaClampingDirection;
    }

    public Integer getMotorPowerPinDirection() {
        return motorPowerPinDirection;
    }

    public void setMotorPowerPinDirection(Integer motorPowerPinDirection) {
        this.motorPowerPinDirection = motorPowerPinDirection;
    }

    public String getLensModel() {
        return lensModel;
    }

    public void setLensModel(String lensModel) {
        this.lensModel = lensModel;
    }

    public Float getDFovAngle() {
        return dFovAngle;
    }

    public void setDFovAngle(Float dFovAngle) {
        this.dFovAngle = dFovAngle;
    }

    public Integer getAaDistance() {
        return aaDistance;
    }

    public void setAaDistance(Integer aaDistance) {
        this.aaDistance = aaDistance;
    }

    public String getSensorModel() {
        return sensorModel;
    }

    public void setSensorModel(String sensorModel) {
        this.sensorModel = sensorModel;
    }

    public String getEffectivePixels() {
        return effectivePixels;
    }

    public void setEffectivePixels(String effectivePixels) {
        this.effectivePixels = effectivePixels;
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

    public Float getReinforcementBoardWidth() {
        return reinforcementBoardWidth;
    }

    public void setReinforcementBoardWidth(Float reinforcementBoardWidth) {
        this.reinforcementBoardWidth = reinforcementBoardWidth;
    }

    public Float getReinforcementPlateLength() {
        return reinforcementPlateLength;
    }

    public void setReinforcementPlateLength(Float reinforcementPlateLength) {
        this.reinforcementPlateLength = reinforcementPlateLength;
    }

    public Float getFpcWidth() {
        return fpcWidth;
    }

    public void setFpcWidth(Float fpcWidth) {
        this.fpcWidth = fpcWidth;
    }

    public Float getFpcLength() {
        return fpcLength;
    }

    public void setFpcLength(Float fpcLength) {
        this.fpcLength = fpcLength;
    }

    public Float getProtectiveFilmThickness() {
        return protectiveFilmThickness;
    }

    public void setProtectiveFilmThickness(Float protectiveFilmThickness) {
        this.protectiveFilmThickness = protectiveFilmThickness;
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

    public Integer getProdLevel() {
        return prodLevel;
    }

    public void setProdLevel(Integer prodLevel) {
        this.prodLevel = prodLevel;
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
        return "FixtureParamsAa{" +
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
                ", fixtureSharedStatus=" + fixtureSharedStatus +
                ", processRequirements=" + processRequirements +
                ", motorModel='" + motorModel + '\'' +
                ", motorPinDirection=" + motorPinDirection +
                ", aaClampingDirection=" + aaClampingDirection +
                ", motorPowerPinDirection=" + motorPowerPinDirection +
                ", lensModel='" + lensModel + '\'' +
                ", dFovAngle=" + dFovAngle +
                ", aaDistance=" + aaDistance +
                ", sensorModel='" + sensorModel + '\'' +
                ", effectivePixels='" + effectivePixels + '\'' +
                ", moduleHeadWidth=" + moduleHeadWidth +
                ", moduleHeadLength=" + moduleHeadLength +
                ", reinforcementBoardWidth=" + reinforcementBoardWidth +
                ", reinforcementPlateLength=" + reinforcementPlateLength +
                ", fpcWidth=" + fpcWidth +
                ", fpcLength=" + fpcLength +
                ", protectiveFilmThickness=" + protectiveFilmThickness +
                ", modulePlacement=" + modulePlacement +
                ", mcId=" + mcId +
                ", prodLevel=" + prodLevel +
                ", deptId=" + deptId +
                ", deviation=" + deviation +
                ", remark='" + remark + '\'' +
                '}';
    }
}
