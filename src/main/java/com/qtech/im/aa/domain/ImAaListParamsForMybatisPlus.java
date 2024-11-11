package com.qtech.im.aa.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/04 14:28:29
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ImAaListParamsForMybatisPlus extends BaseEntityForMybatisPlus {
    @Excel(name = "AA1" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String aa1;
    @Excel(name = "AA2" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String aa2;
    @Excel(name = "AA3" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String aa3;
    @Excel(name = "backToPosition" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String backToPosition;
    @Excel(name = "blemish" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String blemish;
    @Excel(name = "clampOnOff" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String clampOnOff;
    @Excel(name = "chartAlignment" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String chartAlignment;
    @Excel(name = "chartAlignment1" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String chartAlignment1;
    @Excel(name = "chartAlignment2" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String chartAlignment2;
    @Excel(name = "delay" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String delay;
    @Excel(name = "destroy" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String destroy;
    @Excel(name = "destroyStart" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String destroyStart;
    @Excel(name = "dispense" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String dispense;
    @Excel(name = "epoxyInspection" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String epoxyInspection;
    @Excel(name = "epoxyInspectionAuto" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String epoxyInspectionAuto;
    @Excel(name = "grab" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String grab;
    @Excel(name = "gripperOpen" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String gripperOpen;
    @Excel(name = "init" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String init;
    @Excel(name = "lpBlemish" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String lpBlemish;
    @Excel(name = "lpOc" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String lpOc;
    @Excel(name = "lpOcCheck" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String lpOcCheck;
    @Excel(name = "lpOn" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String lpOn;
    @Excel(name = "lpOnBlemish" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String lpOnBlemish;
    @Excel(name = "lpOff" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String lpOff;
    @Excel(name = "moveToBlemishPos" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String moveToBlemishPos;
    @Excel(name = "mtfCheck" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String mtfCheck;
    @Excel(name = "mtfCheck1" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String mtfCheck1;
    @Excel(name = "mtfCheck2" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String mtfCheck2;
    @Excel(name = "mtfCheck3" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String mtfCheck3;
    @Excel(name = "openCheck" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String openCheck;
    // @Excel(name = "ocCheck" , cellType = Excel.ColumnType.STRING, prompt = "list")
    // private String ocCheck;
    @Excel(name = "recordPosition" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String recordPosition;
    @Excel(name = "reInit" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String reInit;
    @Excel(name = "saveOc" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String saveOc;
    @Excel(name = "saveMtf" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String saveMtf;
    @Excel(name = "senserReset" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String senserReset;
    @Excel(name = "sid" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String sid;
    @Excel(name = "uvon" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String uvon;
    @Excel(name = "uvoff" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String uvoff;
    @Excel(name = "vcmHall" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmHall;
    @Excel(name = "vcmHall2" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmHall2;
    @Excel(name = "vcmMoveToZ" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmMoveToZ;
    @Excel(name = "vcmMoveToZPos" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmMoveToZPos;
    @Excel(name = "vcmPowerOffCheck" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmPowerOffCheck;
    // @Excel(name = "vcmRun" , cellType = Excel.ColumnType.STRING, prompt = "list")
    // private String vcmRun;
    @Excel(name = "vcmInit" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmInit;
    @Excel(name = "vcmOisInit" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmOisInit;
    @Excel(name = "vcmPowerOff" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmPowerOff;
    @Excel(name = "vcmPowerOn" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmPowerOn;
    @Excel(name = "vcmTop" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmTop;
    @Excel(name = "vcmTopHall" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmTopHall;
    @Excel(name = "vcmZ" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmZ;
    @Excel(name = "vcmZHall" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String vcmZHall;
    @Excel(name = "yLevel" , cellType = Excel.ColumnType.STRING, prompt = "list")
    private String yLevel;

    // AA Item 指标
    @Excel(name = "aa1RoiCc" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1RoiCc;
    @Excel(name = "aa1RoiUl" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1RoiUl;
    @Excel(name = "aa1RoiUr" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1RoiUr;
    @Excel(name = "aa1RoiLl" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1RoiLl;
    @Excel(name = "aa1RoiLr" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1RoiLr;
    @Excel(name = "aa1Fc" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1Fc;
    @Excel(name = "aa1F1" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa1F1;
    @Excel(name = "aa1F2" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa1F2;
    @Excel(name = "aa1F3" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa1F3;
    @Excel(name = "aa1F4" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa1F4;
    @Excel(name = "aa1MtfOffAxisCheck1" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1MtfOffAxisCheck1;
    @Excel(name = "aa1MtfOffAxisCheck2" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1MtfOffAxisCheck2;
    @Excel(name = "aa1MtfOffAxisCheck3" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1MtfOffAxisCheck3;
    @Excel(name = "aa1Target" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1Target;
    @Excel(name = "aa1CcToCornerLimit" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1CcToCornerLimit;
    @Excel(name = "aa1CcToCornerLimitMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1CcToCornerLimitMin;
    @Excel(name = "aa1CornerScoreDifferenceRejectValue" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1CornerScoreDifferenceRejectValue;
    @Excel(name = "aa1ZRef" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1ZRef;
    @Excel(name = "aa1SrchStep" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1SrchStep;
    @Excel(name = "aa1GoldenGlueThicknessMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1GoldenGlueThicknessMin;
    @Excel(name = "aa1GoldenGlueThicknessMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa1GoldenGlueThicknessMax;

    @Excel(name = "aa2RoiCc" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2RoiCc;
    @Excel(name = "aa2RoiUl" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2RoiUl;
    @Excel(name = "aa2RoiUr" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2RoiUr;
    @Excel(name = "aa2RoiLl" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2RoiLl;
    @Excel(name = "aa2RoiLr" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2RoiLr;
    @Excel(name = "aa2Fc" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa2Fc;
    @Excel(name = "aa2F1" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa2F1;
    @Excel(name = "aa2F2" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa2F2;
    @Excel(name = "aa2F3" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa2F3;
    @Excel(name = "aa2F4" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa2F4;
    @Excel(name = "aa2MtfOffAxisCheck1" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2MtfOffAxisCheck1;
    @Excel(name = "aa2MtfOffAxisCheck2" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2MtfOffAxisCheck2;
    @Excel(name = "aa2MtfOffAxisCheck3" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2MtfOffAxisCheck3;
    @Excel(name = "aa2Target" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2Target;
    @Excel(name = "aa2CcToCornerLimit" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2CcToCornerLimit;
    @Excel(name = "aa2CcToCornerLimitMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2CcToCornerLimitMin;
    @Excel(name = "aa2CornerScoreDifferenceRejectValue" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2CornerScoreDifferenceRejectValue;
    @Excel(name = "aa2ZRef" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2ZRef;
    @Excel(name = "aa2SrchStep" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2SrchStep;
    @Excel(name = "aa2GoldenGlueThicknessMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2GoldenGlueThicknessMin;
    @Excel(name = "aa2GoldenGlueThicknessMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa2GoldenGlueThicknessMax;

    @Excel(name = "aa3RoiCc" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3RoiCc;
    @Excel(name = "aa3RoiUl" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3RoiUl;
    @Excel(name = "aa3RoiUr" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3RoiUr;
    @Excel(name = "aa3RoiLl" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3RoiLl;
    @Excel(name = "aa3RoiLr" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3RoiLr;
    @Excel(name = "aa3Fc" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa3Fc;
    @Excel(name = "aa3F1" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa3F1;
    @Excel(name = "aa3F2" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa3F2;
    @Excel(name = "aa3F3" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa3F3;
    @Excel(name = "aa3F4" , cellType = Excel.ColumnType.STRING, prompt = "AA中挂mtfCheck命令")
    private String aa3F4;
    @Excel(name = "aa3MtfOffAxisCheck1" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3MtfOffAxisCheck1;
    @Excel(name = "aa3MtfOffAxisCheck2" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3MtfOffAxisCheck2;
    @Excel(name = "aa3MtfOffAxisCheck3" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3MtfOffAxisCheck3;
    @Excel(name = "aa3Target" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3Target;
    @Excel(name = "aa3CcToCornerLimit" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3CcToCornerLimit;
    @Excel(name = "aa3CcToCornerLimitMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3CcToCornerLimitMin;
    @Excel(name = "aa3CornerScoreDifferenceRejectValue" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3CornerScoreDifferenceRejectValue;
    @Excel(name = "aa3ZRef" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3ZRef;
    @Excel(name = "aa3SrchStep" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3SrchStep;
    @Excel(name = "aa3GoldenGlueThicknessMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3GoldenGlueThicknessMin;
    @Excel(name = "aa3GoldenGlueThicknessMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String aa3GoldenGlueThicknessMax;

    // mtfCheck Item 指标
    @Excel(name = "mtfCheckFc" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheckFc;
    @Excel(name = "mtfCheckF1" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheckF1;
    @Excel(name = "mtfCheckF2" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheckF2;
    @Excel(name = "mtfCheckF3" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheckF3;
    @Excel(name = "mtfCheckF4" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheckF4;

    @Excel(name = "mtfCheck1Fc" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck1Fc;
    @Excel(name = "mtfCheck1F1" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck1F1;
    @Excel(name = "mtfCheck1F2" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck1F2;
    @Excel(name = "mtfCheck1F3" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck1F3;
    @Excel(name = "mtfCheck1F4" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck1F4;

    @Excel(name = "mtfCheck2Fc" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck2Fc;
    @Excel(name = "mtfCheck2F1" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck2F1;
    @Excel(name = "mtfCheck2F2" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck2F2;
    @Excel(name = "mtfCheck2F3" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck2F3;
    @Excel(name = "mtfCheck2F4" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck2F4;

    @Excel(name = "mtfCheck3Fc" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck3Fc;
    @Excel(name = "mtfCheck3F1" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck3F1;
    @Excel(name = "mtfCheck3F2" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck3F2;
    @Excel(name = "mtfCheck3F3" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck3F3;
    @Excel(name = "mtfCheck3F4" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String mtfCheck3F4;

    // chartAlignment Item 指标
    @Excel(name = "chartAlignmentXResMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignmentXResMin;
    @Excel(name = "chartAlignmentXResMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignmentXResMax;
    @Excel(name = "chartAlignmentYResMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignmentYResMin;
    @Excel(name = "chartAlignmentYResMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignmentYResMax;

    @Excel(name = "chartAlignment1XResMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignment1XResMin;
    @Excel(name = "chartAlignment1XResMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignment1XResMax;
    @Excel(name = "chartAlignment1YResMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignment1YResMin;
    @Excel(name = "chartAlignment1YResMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignment1YResMax;

    @Excel(name = "chartAlignment2XResMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignment2XResMin;
    @Excel(name = "chartAlignment2XResMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignment2XResMax;
    @Excel(name = "chartAlignment2YResMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignment2YResMin;
    @Excel(name = "chartAlignment2YResMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String chartAlignment2YResMax;

    // EpoxyInspection Auto Item 指标
    @Excel(name = "epoxyInspectionInterval" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String epoxyInspectionInterval;

    // vcmCheck 指标
    @Excel(name = "vcmCheckResultCheckMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String vcmCheckResultCheckMin;
    @Excel(name = "vcmCheckResultCheckMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String vcmCheckResultCheckMax;

    // RecordPosition 指标
    @Excel(name = "recordPositionUtXyzMove" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String recordPositionUtXyzMove;

    // OcCheck 指标 Save Oc
    @Excel(name = "ocCheckXOffsetMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String ocCheckXOffsetMin;
    @Excel(name = "ocCheckXOffsetMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String ocCheckXOffsetMax;
    @Excel(name = "ocCheckYOffsetMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String ocCheckYOffsetMin;
    @Excel(name = "ocCheckYOffsetMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String ocCheckYOffsetMax;

    @Excel(name = "saveOcXOffsetMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String saveOcXOffsetMin;
    @Excel(name = "saveOcXOffsetMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String saveOcXOffsetMax;
    @Excel(name = "saveOcYOffsetMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String saveOcYOffsetMin;
    @Excel(name = "saveOcYOffsetMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String saveOcYOffsetMax;

    // SaveMtf 指标
    @Excel(name = "saveMtfCcMin" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String saveMtfCcMin;
    @Excel(name = "saveMtfCcMax" , cellType = Excel.ColumnType.STRING, prompt = "item")
    private String saveMtfCcMax;

    // Vcm Run 指标
}
