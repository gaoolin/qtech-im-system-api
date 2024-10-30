package com.qtech.im.fixture.domain;


import com.qtech.framework.web.domain.BaseEntity;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/20 13:48:47
 * desc   :  料号实体类
 */


public class FixtureMaterialInfo extends BaseEntity {

    private Long mId;

    private String materialNmb;

    private String fixtureName;

    private String fixtureSpec;

    private String fixtureVersion;

    private Integer buckle;

    private Long deptId;

    private Long userId;

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
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

    public Integer getBuckle() {
        return buckle;
    }

    public void setBuckle(Integer buckle) {
        this.buckle = buckle;
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
        return "FixtureMaterialInfo{" +
                "mId=" + mId +
                ", materialNmb='" + materialNmb + '\'' +
                ", fixtureName='" + fixtureName + '\'' +
                ", fixtureSpec='" + fixtureSpec + '\'' +
                ", fixtureVersion='" + fixtureVersion + '\'' +
                ", buckle=" + buckle +
                ", deptId=" + deptId +
                ", userId=" + userId +
                '}';
    }
}
