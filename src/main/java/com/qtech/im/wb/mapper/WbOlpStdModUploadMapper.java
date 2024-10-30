package com.qtech.im.wb.mapper;

import com.qtech.im.wb.domain.WbOlpStdModDetail;
import com.qtech.im.wb.domain.WbOlpStdModUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/09/08 16:08:48
 * desc   :
 */

@Repository
public interface WbOlpStdModUploadMapper {
    List<WbOlpStdModUpload> selectWbOlpStdModUploadList(WbOlpStdModUpload wbOlpStdModUpload);

    List<WbOlpStdModDetail> selectWbOlpStdModUploadMockList(@Param("simId") String simId,
                                                            @Param("mcId") String mcId,
                                                            @Param("pId") String pId,
                                                            @Param("beginTime") String beginTime,
                                                            @Param("endTime") String endTime,
                                                            @Param("delLineNoList") List<Integer> delLineNoList);
}
