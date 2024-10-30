package com.qtech.im.wire.mapper;

import com.qtech.im.wire.domain.EqLevelWireUsage;
import com.qtech.im.wire.domain.WorkshopNameList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : gaozhilin
 * @project : qtech
 * @email : gaoolin@gmail.com
 * @date : 2023/03/27 15:59:44
 * @description :
 */

@Repository
public interface WorkshopNameListMapper {

    public List<WorkshopNameList> selectWorkshopNameList(EqLevelWireUsage eqLevelWireUsage);
}
