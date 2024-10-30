package com.qtech.im.fixture.domain;

import com.qtech.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 10:35:57
 * desc   :
 */

@Data
public class FixtureMaterialCategoryProdType extends BaseEntity {

    private Long id;

    private Long mId;

    private Long cId;

    private Long pId;

    private Long deptId;

    private Long userId;
}
