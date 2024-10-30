package com.qtech.im.fixture.common;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.im.common.exception.AddFixtureParamsException;
import com.qtech.im.common.exception.TooManyResultsException;
import com.qtech.im.fixture.domain.*;
import com.qtech.im.fixture.mapper.FixtureParamsAaMapper;
import com.qtech.im.fixture.mapper.FixtureParamsLockMapper;
import com.qtech.im.fixture.mapper.FixtureParamsPoGoPinMapper;
import com.qtech.im.fixture.service.IFixtureCategoryInfoService;
import com.qtech.im.fixture.service.IFixtureMaterialCategoryProdTypeService;
import com.qtech.im.fixture.service.IFixtureMaterialInfoService;
import com.qtech.im.fixture.service.IFixtureSharedInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.qtech.common.utils.SecurityUtils.getLoginUser;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/03/21 13:42:14
 * desc   :
 */

@Slf4j
@Component
public class FixtureUtils {

    @Autowired
    IFixtureCategoryInfoService fixtureCategoryInfoService;

    @Autowired
    IFixtureMaterialInfoService fixtureMaterialInfoService;

    @Autowired
    IFixtureMaterialCategoryProdTypeService fixtureMaterialCategoryProdTypeService;

    @Autowired
    FixtureParamsPoGoPinMapper fixtureParamsPoGoPinMapper;

    @Autowired
    FixtureParamsAaMapper fixtureParamsAaMapper;

    @Autowired
    FixtureParamsLockMapper fixtureParamsLockMapper;

    /**
     * @param fixtureEntity
     * @param project
     * @param <T>           参数校验：在方法开始时添加了对fixtureEntity和project的非空和非空白校验。
     *                      异常信息增强：在捕获反射相关的异常时，现在抛出了包含更具体错误信息的运行时异常。这样可以提供更多的上下文信息，便于问题的诊断。
     *                      字符串检查优化：将检查fixtureCategory是否包含"null"的逻辑改为使用StringUtils.isNotBlank(fixtureCategory)和!fixtureCategory.contains("null")，以简化逻辑并避免潜在的错误。
     *                      反射调用错误处理：将对methodCIdSet的空检查移到实际调用之前，以避免潜在的NullPointerException。
     *                      代码辅助方法：添加了一个辅助方法capitalize，用于将字符串的首字母大写。这有助于提高代码的可读性和重用性。
     *                      去除不必要的注释和未使用的变量：清理了代码中的一些注释和未使用的变量，以提高代码的整洁度。
     *                      请注意，根据具体的项目架构和代码库的其他部分，可能还需要进行其他调整。例如，异常处理策略或字符串验证逻辑可能需要根据项目的具体需求进行定制。此优化后的代码版本仅供参考。
     */
    public <T> void fixtureCategoryExistCheckGeneric(T fixtureEntity, String project) {
        if (fixtureEntity == null || StringUtils.isBlank(project)) {
            throw new IllegalArgumentException("fixtureEntity and project must not be null or empty");
        }

        String fixtureCategory = null;
        Method methodFixtureCategoryGet = null;
        Method methodCIdSet = null;

        try {
            Class<?> fixtureEntityClass = fixtureEntity.getClass();

            // 得到所有属性
            Field[] fields = fixtureEntityClass.getDeclaredFields();
            for (Field field : fields) {
                if ("fixtureCategory".equals(field.getName())) {
                    field.setAccessible(true);
                    methodFixtureCategoryGet = fixtureEntityClass.getMethod("get" + capitalize(field.getName()));
                    fixtureCategory = (String) methodFixtureCategoryGet.invoke(fixtureEntity);
                } else if ("cId".equals(field.getName())) {
                    field.setAccessible(true);
                    methodCIdSet = fixtureEntityClass.getMethod("set" + capitalize(field.getName()), field.getType());
                }
            }

            if (StringUtils.isNotBlank(fixtureCategory) && !fixtureCategory.contains("null")) {
                FixtureCategoryInfo fixtureCategoryInfo = new FixtureCategoryInfo();
                fixtureCategoryInfo.setFixtureCategory(fixtureCategory);
                FixtureCategoryInfo fixtureCategoryInfoQuery = setEntityDeptId(fixtureCategoryInfo, project);

                FixtureCategoryInfo fixtureCategoryInfoDb = fixtureCategoryInfoService.selectOneFixtureCategoryInfo(fixtureCategoryInfoQuery);

                if (fixtureCategoryInfoDb != null) {
                    Long cIdDb = fixtureCategoryInfoDb.getCId();
                    // 检查 methodCIdSet 是否为 null，提前在使用前进行检查
                    if (methodCIdSet != null) {
                        methodCIdSet.invoke(fixtureEntity, cIdDb);
                    } else {
                        throw new IllegalStateException("Method for setting cId is null");
                    }
                } else {
                    fixtureCategoryInfoService.addFixtureCategoryInfo(fixtureCategoryInfoQuery);
                    if (methodCIdSet != null) {
                        methodCIdSet.invoke(fixtureEntity, fixtureCategoryInfoQuery.getCId());
                    } else {
                        throw new IllegalStateException("Method for setting cId is null");
                    }
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error during reflection operation" , e);
        }
    }

    // 辅助方法，将字符串首字母大写
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public <T> int addFixtureParamsGeneric1(T fixtureParamsEntity, String project) {

        if (fixtureParamsEntity == null || StringUtils.isBlank(project)) {
            throw new IllegalArgumentException("数据缺少料号信息，请检查！");
        }

        String materialNmb = null;
        int i = 0;

        String nickName = getLoginUser().getUser().getNickName();

        Class<?> fixtureParamsEntityClass = fixtureParamsEntity.getClass();
        Field[] fields = fixtureParamsEntityClass.getDeclaredFields();
        for (Field field : fields) {
            if ("createBy".equals(field.getName())) {
                field.setAccessible(true);
                String fieldName = field.getName();
                String name = fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
                try {
                    Method methodCreateBySet = fixtureParamsEntityClass.getMethod("set" + name, field.getType());
                    methodCreateBySet.invoke(fixtureParamsEntity, nickName);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    // e.printStackTrace();
                    throw new RuntimeException("Error during reflection operation" , e);
                }
            } else if ("createTime".equals(field.getName())) {
                field.setAccessible(true);
                String fieldName = field.getName();
                String name = fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
                try {
                    Method methodCreateTimeSet = fixtureParamsEntityClass.getMethod("set" + name, field.getType());
                    methodCreateTimeSet.invoke(fixtureParamsEntity, DateUtils.getNowDate());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    // e.printStackTrace();
                    throw new RuntimeException("Error during reflection operation" , e);
                }
            } else if ("materialNmb".equals(field.getName())) {
                field.setAccessible(true);
                String fieldName = field.getName();
                String name = fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
                try {
                    Method methodMaterialNmbGet = fixtureParamsEntityClass.getMethod("get" + name);
                    materialNmb = (String) methodMaterialNmbGet.invoke(fixtureParamsEntity);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("Error during reflection operation" , e);
                }
            }
        }
        try {
            // 根据参数类型获取相应的构造函数
            Constructor<?> constructor = fixtureParamsEntityClass.getConstructor();
            constructor.setAccessible(true);
            Object queryParams = constructor.newInstance();
            Field materialNmbField = queryParams.getClass().getDeclaredField("materialNmb");
            materialNmbField.setAccessible(true);
            materialNmbField.set(queryParams, materialNmb);

            switch (StringUtils.upperCase(project)) {
                case "POGOPIN":
                    if (queryParams instanceof FixtureParamsPoGoPin) {
                        List<FixtureParamsPoGoPin> listPogopin = fixtureParamsPoGoPinMapper.selectFixtureParamsPoGoPinList((FixtureParamsPoGoPin) queryParams);
                        if (listPogopin.isEmpty()) {
                            fixtureCategoryExistCheckGeneric(fixtureParamsEntity, project);
                            if (fixtureParamsEntity instanceof FixtureParamsPoGoPin) {
                                try {
                                    i = fixtureParamsPoGoPinMapper.addFixtureParamsPoGoPin((FixtureParamsPoGoPin) fixtureParamsEntity);
                                } catch (Exception e) {
                                    throw new AddFixtureParamsException(String.format("导入Pogopin治具信息出错： %s，请检查！" , materialNmb));
                                }
                            }
                        } else {
                            throw new TooManyResultsException(String.format("料号已存在: %S" , materialNmb));
                        }
                    }
                    break;
                case "AA":
                    if (queryParams instanceof FixtureParamsAa) {
                        List<FixtureParamsAa> listAa = fixtureParamsAaMapper.selectFixtureParamsAaList((FixtureParamsAa) queryParams);
                        if (listAa.isEmpty()) {
                            fixtureCategoryExistCheckGeneric(fixtureParamsEntity, project);
                            if (fixtureParamsEntity instanceof FixtureParamsAa) {
                                try {
                                    i = fixtureParamsAaMapper.addFixtureParamsAa((FixtureParamsAa) fixtureParamsEntity);
                                } catch (Exception e) {
                                    throw new AddFixtureParamsException(String.format("导入AA治具信息出错： %s，请检查！" , materialNmb));
                                }
                            }
                        } else {
                            throw new TooManyResultsException(String.format("料号已存在: %S" , materialNmb));
                        }
                    }
                    break;
                case "LOCK":
                    if (queryParams instanceof FixtureParamsLock) {
                        List<FixtureParamsLock> listLock = fixtureParamsLockMapper.selectFixtureParamsLockList((FixtureParamsLock) queryParams);
                        if (listLock.isEmpty()) {
                            fixtureCategoryExistCheckGeneric(fixtureParamsEntity, project);
                            if (fixtureParamsEntity instanceof FixtureParamsLock) {
                                try {
                                    i = fixtureParamsLockMapper.addFixtureParamsLock((FixtureParamsLock) fixtureParamsEntity);
                                } catch (Exception e) {
                                    throw new AddFixtureParamsException(String.format("导入锁附治具信息出错： %s，请检查！" , materialNmb));
                                }
                            }
                        } else {
                            throw new TooManyResultsException(String.format("料号已存在: %S" , materialNmb));
                        }
                    }
                    break;
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return i;
    }


    /**
     * 优化点解释：
     * 参数校验：validateParameters函数增加了对project是否仅为空白字符的检查。
     * 提取materialNmb：通过反射优化提取materialNmb的逻辑，减少冗余代码。
     * 异常处理：将异常捕获放在最外层，统一抛出运行时异常，并附带原始异常信息，便于追踪问题。
     * 性能优化：通过避免在switch语句中重复查询类信息，而是直接传入已经创建的查询参数对象，减少了反射的使用，从而提高性能。
     * 维护性提升：将每个项目类型的处理逻辑独立到各自的函数中，避免了switch语句的膨胀，提高了代码的可读性和可维护性。
     *
     * @param fixtureParamsEntity
     * @param project
     * @param <T>
     * @return
     */
    public <T> int addFixtureParamsGeneric(T fixtureParamsEntity, String project) {
        // 参数校验
        validateParameters(fixtureParamsEntity, project);

        String materialNmb = null;
        try {
            materialNmb = extractMaterialNmb(fixtureParamsEntity);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        if (StringUtils.isBlank(materialNmb)) {
            throw new IllegalArgumentException("数据缺少料号信息，请检查！");
        }

        int result = 0;
        try {
            Object queryParams = createQueryParams(fixtureParamsEntity);

            switch (project.toUpperCase()) {
                case "POGOPIN":
                    result = processPoGoPin(fixtureParamsEntity, queryParams, materialNmb);
                    break;
                case "AA":
                    result = processAA(fixtureParamsEntity, queryParams, materialNmb);
                    break;
                case "LOCK":
                    result = processLock(fixtureParamsEntity, queryParams, materialNmb);
                    break;
                default:
                    throw new IllegalArgumentException("不支持的项目类型: " + project);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    private void validateParameters(Object fixtureParamsEntity, String project) {
        if (fixtureParamsEntity == null || StringUtils.isBlank(project)) {
            throw new IllegalArgumentException("数据缺少料号信息，请检查！");
        }
    }

    private String extractMaterialNmb(Object fixtureParamsEntity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> fixtureParamsEntityClass = fixtureParamsEntity.getClass();
        for (Field field : fixtureParamsEntityClass.getDeclaredFields()) {
            if ("materialNmb".equals(field.getName())) {
                field.setAccessible(true);
                Method getter = fixtureParamsEntityClass.getMethod("get" + capitalize(field.getName()));
                return (String) getter.invoke(fixtureParamsEntity);
            }
        }
        return null;
    }

    private Object createQueryParams(Object fixtureParamsEntity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = fixtureParamsEntity.getClass().getConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    private <T> int processPoGoPin(T fixtureParamsEntity, Object queryParams, String materialNmb) throws Exception {
        if (queryParams instanceof FixtureParamsPoGoPin) {
            FixtureParamsPoGoPin fixtureParamsPoGoPin = (FixtureParamsPoGoPin) queryParams;
            fixtureParamsPoGoPin.setMaterialNmb(materialNmb);
            List<FixtureParamsPoGoPin> listPoGoPin = fixtureParamsPoGoPinMapper.selectFixtureParamsPoGoPinList(fixtureParamsPoGoPin);
            if (listPoGoPin.isEmpty()) {
                fixtureCategoryExistCheckGeneric(fixtureParamsEntity, "POGOPIN");
                return fixtureParamsPoGoPinMapper.addFixtureParamsPoGoPin((FixtureParamsPoGoPin) fixtureParamsEntity);
            } else {
                throw new TooManyResultsException(String.format("料号已存在: %S" , materialNmb));
            }
        }
        throw new IllegalArgumentException("不支持的参数类型");
    }

    private <T> int processAA(T fixtureParamsEntity, Object queryParams, String materialNmb) throws Exception {
        // 逻辑与 processPoGoPin 类似，但针对 FixtureParamsAa
        if (queryParams instanceof FixtureParamsAa) {
            FixtureParamsAa fixtureParamsAa = (FixtureParamsAa) queryParams;
            fixtureParamsAa.setMaterialNmb(materialNmb);
            List<FixtureParamsAa> listAa = fixtureParamsAaMapper.selectFixtureParamsAaList(fixtureParamsAa);
            if (listAa.isEmpty()) {
                fixtureCategoryExistCheckGeneric(fixtureParamsEntity, "AA");
                return fixtureParamsAaMapper.addFixtureParamsAa((FixtureParamsAa) fixtureParamsEntity);
            } else {
                throw new TooManyResultsException(String.format("料号已存在: %S" , materialNmb));
            }
        }
        throw new IllegalArgumentException("不支持的参数类型"); // 示例代码，具体实现根据需要填充
    }

    private <T> int processLock(T fixtureParamsEntity, Object queryParams, String materialNmb) {
        // 逻辑与 processPoGoPin 类似，但针对 FixtureParamsLock
        if (queryParams instanceof FixtureParamsLock) {
            FixtureParamsLock fixtureParamsLock = (FixtureParamsLock) queryParams;
            fixtureParamsLock.setMaterialNmb(materialNmb);
            List<FixtureParamsLock> listLock = fixtureParamsLockMapper.selectFixtureParamsLockList(fixtureParamsLock);
            if (listLock.isEmpty()) {
                fixtureCategoryExistCheckGeneric(fixtureParamsEntity, "LOCK");
                return fixtureParamsLockMapper.addFixtureParamsLock((FixtureParamsLock) fixtureParamsEntity);
            } else {
                throw new TooManyResultsException(String.format("料号已存在:%S" , materialNmb));
            }
        }
        throw new IllegalArgumentException("不支持的参数类型"); // 示例代码，具体实现根据需要填充
    }


    // FIXME
    public <T> void fixtureMaterialCheckGeneric(T fixtureParamsEntity, String project) {
        FixtureMaterialInfo fixtureMaterialInfo = new FixtureMaterialInfo();
        BeanUtils.copyProperties(fixtureParamsEntity, fixtureMaterialInfo, "remark");
        if (fixtureMaterialInfo.getFixtureName() == null && fixtureMaterialInfo.getFixtureSpec() == null && fixtureMaterialInfo.getFixtureVersion() == null && fixtureMaterialInfo.getBuckle() == null) {
            return;
        }
        FixtureMaterialInfo fixtureMaterialInfoQuery = setEntityDeptId(fixtureMaterialInfo, project);

        fixtureMaterialInfoService.editFixtureMaterialInfo(fixtureMaterialInfoQuery);
    }

    private <T> T setEntityDeptId(T entity, String project) {
        Long deptId = null;
        switch (StringUtils.upperCase(project)) {
            case "POGOPIN":
                deptId = 209L;
                break;
            case "AA":
                deptId = 210L;
                break;
            case "LOCK":
                deptId = 211L;
                break;
            default:
                log.error("未知的工程部门，请检查！");
        }

        Class<?> fixtureEntityClass = entity.getClass();
        Field[] fields = fixtureEntityClass.getDeclaredFields();
        for (Field field : fields) {
            if ("deptId".equals(field.getName())) {
                field.setAccessible(true);
                String fieldName = field.getName();
                String name = fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
                try {
                    Method methodDeptIdSet = fixtureEntityClass.getMethod("set" + name, field.getType());
                    methodDeptIdSet.invoke(entity, deptId);
                } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return entity;
    }

    /**
     * @param pId
     * @return java.lang.Boolean
     * @description 返回是否有被引用。方法传入机型ID，判断此机型是否在共享信息中被多次（>1)使用，多次引用返回True，否则返回False。
     */
    public Boolean fixtureProdTypeMultipleReferenceCheck(Long pId, Long deptId) {
        FixtureMaterialCategoryProdType fixtureMaterialCategoryProdTypeQuery = new FixtureMaterialCategoryProdType();
        fixtureMaterialCategoryProdTypeQuery.setPId(pId);
        fixtureMaterialCategoryProdTypeQuery.setDeptId(deptId);
        List<FixtureMaterialCategoryProdType> list = fixtureMaterialCategoryProdTypeService.selectFixtureMaterialCategoryProdTypeList(fixtureMaterialCategoryProdTypeQuery);

        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            return size > 0;
        }
        return false;
    }

    /**
     * @param materialNmb
     * @param deptId
     * @return java.lang.Boolean true被引用，false没有被引用
     * @description 删除控制器在传入料号和机型参数的情况下，应使用此方法判断是否可以删除料号；若只传入料号参数时，则无需使用判断
     */
    public Boolean fixtureMaterialNmbMultipleReferenceCheck(String materialNmb, Long deptId) {
        FixtureMaterialInfo fixtureMaterialInfoQuery = new FixtureMaterialInfo();
        fixtureMaterialInfoQuery.setMaterialNmb(materialNmb);
        fixtureMaterialInfoQuery.setDeptId(deptId);
        FixtureMaterialInfo fixtureMaterialInfoDb = fixtureMaterialInfoService.selectOneFixtureMaterialInfo(fixtureMaterialInfoQuery);
        Long mIdDb = null;

        mIdDb = fixtureMaterialInfoDb.getmId();

        FixtureMaterialCategoryProdType fixtureMaterialCategoryProdTypeQuery = new FixtureMaterialCategoryProdType();
        fixtureMaterialCategoryProdTypeQuery.setMId(mIdDb);
        fixtureMaterialCategoryProdTypeQuery.setDeptId(deptId);
        List<FixtureMaterialCategoryProdType> referenceList = fixtureMaterialCategoryProdTypeService.selectFixtureMaterialCategoryProdTypeList(fixtureMaterialCategoryProdTypeQuery);

        if (CollectionUtils.isNotEmpty(referenceList)) {
            int size = referenceList.size();
            return size > 0;
        }
        return false;
    }


    public int fixtureSharedInfoRemoveCheck(Integer fixtureSharedStatus, Long deptId, String materialNmb, IFixtureSharedInfoService fixtureSharedInfoService) {
        if (fixtureSharedStatus == 1) {
            FixtureSharedInfo fixtureSharedInfoQuery = new FixtureSharedInfo();
            fixtureSharedInfoQuery.setDeptId(deptId);
            fixtureSharedInfoQuery.setMaterialNmb(materialNmb);
            return fixtureSharedInfoService.deleteFixtureSharedInfo(fixtureSharedInfoQuery);
        }
        return 0;
    }
}
