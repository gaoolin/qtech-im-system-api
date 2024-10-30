package com.qtech.im.wb.domain;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/09/16 12:17:36
 * desc   :
 */


public class WbOlpTrendingVo {

    private String date;

    private Integer code;

    private Integer cnt;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "WbOlpTrendingVo{" +
                "date='" + date + '\'' +
                ", code=" + code +
                ", cnt=" + cnt +
                '}';
    }
}
