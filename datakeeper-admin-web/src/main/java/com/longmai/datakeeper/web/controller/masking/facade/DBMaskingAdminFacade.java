package com.longmai.datakeeper.web.controller.masking.facade;

import com.longmai.datakeeper.dao.entity.DbMaskingColumnEntity;
import com.longmai.datakeeper.dao.entity.EncryptFieldEntity;
import com.longmai.datakeeper.dao.entity.StructDataSourceEntity;
import com.longmai.datakeeper.dto.UserLoginDto;
import com.longmai.datakeeper.service.db.DBMaskingColumnService;
import com.longmai.datakeeper.service.db.StructDataSourceService;
import com.longmai.datakeeper.web.controller.encrypt.model.EncryptTableCreate;
import com.longmai.datakeeper.web.controller.masking.model.MaskingColumnVo;
import com.longmai.datakeeper.web.controller.masking.model.MaskingTableCreate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DBMaskingAdminFacade {

    @Autowired
    private StructDataSourceService dataSourceService;
    @Autowired
    private DBMaskingColumnService maskingColumnService;

    public List<MaskingColumnVo> listAll(){
        List<DbMaskingColumnEntity> maskingColumnList = maskingColumnService.listAll();
        return convertVO(maskingColumnList);
    }

    public List<MaskingColumnVo> listBy(Integer dataSourceId, String tableName){
        List<DbMaskingColumnEntity> entityList = maskingColumnService.listBy(dataSourceId, tableName);
        return convertVO(entityList);
    }

    public void createEncryptColumn(MaskingTableCreate maskingTableCreate, UserLoginDto userLoginDto){

        List<MaskingTableCreate.MaskingColumn> maskColumns = maskingTableCreate.getMaskingColumns();
        if (CollectionUtils.isEmpty(maskColumns)){
            throw new RuntimeException("脱敏列为空");
        }
        maskColumns = maskColumns.stream().filter(new Predicate<MaskingTableCreate.MaskingColumn>() {
            @Override
            public boolean test(MaskingTableCreate.MaskingColumn maskingColum) {
                return maskingColum.isSelected();
            }
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(maskColumns)){
            throw new RuntimeException("脱敏列为空");
        }

        Integer dataSourceId = maskingTableCreate.getDataSourceId();
        StructDataSourceEntity structDataSource = dataSourceService.getDataSourceEntityById(dataSourceId);
        if (Objects.isNull(structDataSource)){
            throw new RuntimeException("未找到数据源");
        }
        String dbName = structDataSource.getDbName();
        String tableName = maskingTableCreate.getTableName();

        List<DbMaskingColumnEntity> maskColumnList = maskColumns.stream().map(new Function<MaskingTableCreate.MaskingColumn, DbMaskingColumnEntity>() {
            @Override
            public DbMaskingColumnEntity apply(MaskingTableCreate.MaskingColumn maskColumn) {
                DbMaskingColumnEntity entity = new DbMaskingColumnEntity();
                BeanUtils.copyProperties(maskColumn, entity);
                if (Objects.isNull(entity.getMaskingMethod())) {
                    entity.setMaskingMethod("shieldMasking");
                }
                entity.setAlgorithmId(1);
                entity.setDbSourceId(dataSourceId);
                entity.setTableName(tableName);
                entity.setDbName(dbName);
                entity.setCreateById(userLoginDto.getId().intValue());
                entity.setCreateByName(userLoginDto.getUserName());
                return entity;
            }
        }).collect(Collectors.toList());

        maskingColumnService.createMaskingColumn(maskColumnList);
    }

    public boolean delete(Integer id){
        return maskingColumnService.deleteById(id);
    }

    private List<MaskingColumnVo> convertVO(List<DbMaskingColumnEntity> entityList){
        if (CollectionUtils.isEmpty(entityList)){
            return Collections.emptyList();
        }
        return entityList.stream().map(new Function<DbMaskingColumnEntity, MaskingColumnVo>() {
            @Override
            public MaskingColumnVo apply(DbMaskingColumnEntity maskingColumnEntity) {
                MaskingColumnVo maskingColumnVo = new MaskingColumnVo();
                BeanUtils.copyProperties(maskingColumnEntity, maskingColumnVo);
                return maskingColumnVo;
            }
        }).collect(Collectors.toList());
    }

}
