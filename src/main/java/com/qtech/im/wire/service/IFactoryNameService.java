package com.qtech.im.wire.service;


import com.qtech.im.wire.domain.FactoryNameList;

import java.util.List;

/**
 * @author : gaozhilin
 * @project : qtech
 * @email : gaoolin@gmail.com
 * @date : 2023/03/27 14:03:43
 * @description :
 */
public interface IFactoryNameService {

    public List<FactoryNameList> SelectFactoryNameList();

}
