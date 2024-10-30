package com.qtech.im.fixture.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qtech.framework.web.domain.BaseEntity;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/20 17:12:33
 * desc   :  治具类型实体类
 */

public class FixtureCategoryInfo extends BaseEntity {

    @JsonProperty("cId")
    private Long cId;

    private String fixtureCategory;

    private Long deptId;

    private Long userId;

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
        return "FixtureCategoryInfo{" +
                "cId=" + cId +
                ", fixtureCategory='" + fixtureCategory + '\'' +
                ", deptId=" + deptId +
                ", userId=" + userId +
                '}';
    }
}




