package com.qtech.im.aa.utils;

import java.util.Arrays;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/04 09:25:15
 * desc   :
 */


public class Constants {

    public static final String REDIS_COMPARISON_MODEL_KEY_PREFIX = "qtech:im:aa:list:model:";
    public static final String REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX = "qtech:im:aa:list:model:info:";
    public static final String EQ_REVERSE_IGNORE_SIM_PREFIX = "qtech:im:chk:ignored:";

    // 需和比对程序同步
    public static final List<String> PROPERTIES_TO_COMPARE = Arrays.asList(
            "prodType",
            "aa1",
            "aa2",
            "aa3",
            "backToPosition",
            "blemish",
            "clampOnOff",
            "chartAlignment",
            "chartAlignment1",
            "chartAlignment2",
            "delay",
            "destroy",
            "destroyStart",
            "dispense",
            "epoxyInspection",
            "epoxyInspectionAuto",
            "grab",
            "gripperOpen",
            "init",
            "lpBlemish",
            "lpOc",
            "lpOnBlemish",
            "lpOcCheck",
            "lpOn",
            "lpOff",
            "moveToBlemishPos",
            "mtfCheck",
            "mtfCheck1",
            "mtfCheck2",
            "mtfCheck3",
            "openCheck",
            // "ocCheck",
            "recordPosition",
            "reInit",
            "saveOc",
            "saveMtf",
            "senserReset",
            "sid",
            "uvon",
            "uvoff",
            "vcmHall",
            "vcmHall2",
            "vcmMoveToZ",
            "vcmMoveToZPos",
            "vcmPowerOffCheck",
            // "vcmRun",
            "vcmInit",
            "vcmOisInit",
            "vcmPowerOff",
            "vcmPowerOn",
            "vcmTop",
            "vcmTopHall",
            "vcmZ",
            "vcmZHall",
            "yLevel"
            // ... 其他需要比较是否相等的属性名称
    );

    public static final List<String> PROPERTIES_TO_COMPUTE = Arrays.asList(
            "aa1RoiCc", "aa1RoiUl", "aa1RoiUr", "aa1RoiLl", "aa1RoiLr", "aa1Fc", "aa1F1", "aa1F2", "aa1F3", "aa1F4", "aa1MtfOffAxisCheck1", "aa1MtfOffAxisCheck2", "aa1MtfOffAxisCheck3",
            "aa2RoiCc", "aa2RoiUl", "aa2RoiUr", "aa2RoiLl", "aa2RoiLr", "aa2Fc", "aa2F1", "aa2F2", "aa2F3", "aa2F4", "aa2MtfOffAxisCheck1", "aa2MtfOffAxisCheck2", "aa2MtfOffAxisCheck3",
            "aa3RoiCc", "aa3RoiUl", "aa3RoiUr", "aa3RoiLl", "aa3RoiLr", "aa3Fc", "aa3F1", "aa3F2", "aa3F3", "aa3F4", "aa3MtfOffAxisCheck1", "aa3MtfOffAxisCheck2", "aa3MtfOffAxisCheck3",
            "mtfCheckFc", "mtfCheckF1", "mtfCheckF2", "mtfCheckF3", "mtfCheckF4",
            "mtfCheck1Fc", "mtfCheck1F1", "mtfCheck1F2", "mtfCheck1F3", "mtfCheck1F4",
            "mtfCheck2Fc", "mtfCheck2F1", "mtfCheck2F2", "mtfCheck2F3", "mtfCheck2F4",
            "mtfCheck3Fc", "mtfCheck3F1", "mtfCheck3F2", "mtfCheck3F3", "mtfCheck3F4",
            "chartAlignmentXResMin", "chartAlignmentXResMax", "chartAlignmentYResMin", "chartAlignmentYResMax",
            "chartAlignment1XResMin", "chartAlignment1XResMax", "chartAlignment1YResMin", "chartAlignment1YResMax",
            "chartAlignment2XResMin", "chartAlignment2XResMax", "chartAlignment2YResMin", "chartAlignment2YResMax",
            "epoxyInspectionInterval",
            "vcmCheckResultCheckMin", "vcmCheckResultCheckMax",
            "recordPositionUtXyzMove",
            "ocCheckXOffsetMin", "ocCheckXOffsetMax", "ocCheckYOffsetMin", "ocCheckYOffsetMax",
            "saveOcXOffsetMin", "saveOcXOffsetMax", "saveOcYOffsetMin", "saveOcYOffsetMax",
            "saveMtfCcMin", "saveMtfCcMax"
    );
}
