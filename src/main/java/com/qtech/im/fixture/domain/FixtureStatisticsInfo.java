package com.qtech.im.fixture.domain;

import com.alibaba.fastjson2.annotation.JSONField;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/12/17 15:21:25
 * desc   :
 */


public class FixtureStatisticsInfo {

    @JSONField(name="DEPT_ID")
    private int deptId;

    private int sharedFixtureCnt;

    private int unSharedFixtureCnt;

    private int ttlProdTypeCnt;

    @JSONField(name="WEEK_NUM")
    private String weekNum;

    @JSONField(name="SHARED_RATIO")
    private float sharedRatio;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getSharedFixtureCnt() {
        return sharedFixtureCnt;
    }

    public void setSharedFixtureCnt(int sharedFixtureCnt) {
        this.sharedFixtureCnt = sharedFixtureCnt;
    }

    public int getUnSharedFixtureCnt() {
        return unSharedFixtureCnt;
    }

    public void setUnSharedFixtureCnt(int unSharedFixtureCnt) {
        this.unSharedFixtureCnt = unSharedFixtureCnt;
    }

    public int getTtlProdTypeCnt() {
        return ttlProdTypeCnt;
    }

    public void setTtlProdTypeCnt(int ttlProdTypeCnt) {
        this.ttlProdTypeCnt = ttlProdTypeCnt;
    }

    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public float getSharedRatio() {
        return sharedRatio;
    }

    public void setSharedRatio(float sharedRatio) {
        this.sharedRatio = sharedRatio;
    }

    @Override
    public String toString() {
        return "FixtureStatisticsInfo{" +
                "deptId=" + deptId +
                ", sharedFixtureCnt=" + sharedFixtureCnt +
                ", unSharedFixtureCnt=" + unSharedFixtureCnt +
                ", ttlProdTypeCnt=" + ttlProdTypeCnt +
                ", weekNum='" + weekNum + '\'' +
                ", sharedRatio=" + sharedRatio +
                '}';
    }
}