package com.qtech.im.fixture.service;

import com.qtech.im.fixture.domain.FixtureMaterialCategoryProdType;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 11:02:40
 * desc   :
 */


public interface IFixtureMaterialCategoryProdTypeService {

    public FixtureMaterialCategoryProdType selectFixtureMaterialCategoryProdTypeOne(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType);

    public List<FixtureMaterialCategoryProdType> selectFixtureMaterialCategoryProdTypeList(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType);

    public Boolean isFixtureMaterialCategoryProdTypeExist(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType);

    public Integer addFixtureMaterialCategoryProdType(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType);

    public Integer editFixtureMaterialCategoryProdType(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType);

    public Integer removeFixtureMaterialCategoryProdType(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType);
}
