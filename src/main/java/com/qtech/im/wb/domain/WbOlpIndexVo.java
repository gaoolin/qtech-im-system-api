package com.qtech.im.wb.domain;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/09/16 10:52:52
 * desc   :
 */


public class WbOlpIndexVo {

    private Long wbOlpStdModelsTtlCnt;

    private Long wbOlpStdModelAvgCnt;

    public Long getWbOlpStdModelsTtlCnt() {
        return wbOlpStdModelsTtlCnt;
    }

    public void setWbOlpStdModelsTtlCnt(Long wbOlpStdModelsTtlCnt) {
        this.wbOlpStdModelsTtlCnt = wbOlpStdModelsTtlCnt;
    }

    public Long getWbOlpStdModelAvgCnt() {
        return wbOlpStdModelAvgCnt;
    }

    public void setWbOlpStdModelAvgCnt(Long wbOlpStdModelAvgCnt) {
        this.wbOlpStdModelAvgCnt = wbOlpStdModelAvgCnt;
    }

    @Override
    public String toString() {
        return "WbOlpIndexVo{" +
                "wbOlpStdModelsTtlCnt=" + wbOlpStdModelsTtlCnt +
                ", wbOlpStdModelAvgCnt=" + wbOlpStdModelAvgCnt +
                '}';
    }
}
