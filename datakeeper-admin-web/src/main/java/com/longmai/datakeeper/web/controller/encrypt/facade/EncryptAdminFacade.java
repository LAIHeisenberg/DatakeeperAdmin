package com.longmai.datakeeper.web.controller.encrypt.facade;

import com.longmai.datakeeper.dao.entity.EncryptFieldEntity;
import com.longmai.datakeeper.dao.entity.StructDataSourceEntity;
import com.longmai.datakeeper.dto.UserLoginDto;
import com.longmai.datakeeper.service.db.EncryptFieldService;
import com.longmai.datakeeper.service.db.StructDataSourceService;
import com.longmai.datakeeper.web.controller.encrypt.model.EncryptColumnVo;
import com.longmai.datakeeper.web.controller.encrypt.model.EncryptTableCreate;
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
public class EncryptAdminFacade {

    @Autowired
    private StructDataSourceService dataSourceService;
    @Autowired
    private EncryptFieldService encryptFieldService;

    public void createEncryptColumn(EncryptTableCreate encryptTableCreate, UserLoginDto userLoginDto){

        List<EncryptTableCreate.EncryptColumn> encryptColumns = encryptTableCreate.getEncryptColumns();
        if (CollectionUtils.isEmpty(encryptColumns)){
            throw new RuntimeException("加密列为空");
        }
        encryptColumns = encryptColumns.stream().filter(new Predicate<EncryptTableCreate.EncryptColumn>() {
            @Override
            public boolean test(EncryptTableCreate.EncryptColumn encryptColumn) {
                return encryptColumn.isSelected();
            }
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(encryptColumns)){
            throw new RuntimeException("加密列为空");
        }

        Integer dataSourceId = encryptTableCreate.getDataSourceId();
        StructDataSourceEntity structDataSource = dataSourceService.getDataSourceEntityById(dataSourceId);
        if (Objects.isNull(structDataSource)){
            throw new RuntimeException("未找到数据源");
        }
        String dbName = structDataSource.getDbName();
        String tableName = encryptTableCreate.getTableName();

        List<EncryptFieldEntity> encryptFieldList = encryptColumns.stream().map(new Function<EncryptTableCreate.EncryptColumn, EncryptFieldEntity>() {
            @Override
            public EncryptFieldEntity apply(EncryptTableCreate.EncryptColumn encryptColumn) {
                EncryptFieldEntity entity = new EncryptFieldEntity();
                BeanUtils.copyProperties(encryptColumn, entity);
                if (Objects.isNull(entity.getAlgorithm())) {
                    entity.setAlgorithm("AES");
                }
                entity.setDbSourceId(dataSourceId);
                entity.setTableName(tableName);
                entity.setDbName(dbName);
                entity.setCreateById(userLoginDto.getId().intValue());
                entity.setCreateByName(userLoginDto.getUserName());
                return entity;
            }
        }).collect(Collectors.toList());

        encryptFieldService.createEncryptField(encryptFieldList);
    }

    public List<EncryptColumnVo> listEncryptColumn(){
        List<EncryptFieldEntity> encryptFieldList = encryptFieldService.listEncryptColumn();
        if (CollectionUtils.isEmpty(encryptFieldList)){
            return Collections.emptyList();
        }
        return encryptFieldList.stream().map(new Function<EncryptFieldEntity, EncryptColumnVo>() {
            @Override
            public EncryptColumnVo apply(EncryptFieldEntity encryptFieldEntity) {
                EncryptColumnVo encryptColumnVo = new EncryptColumnVo();
                BeanUtils.copyProperties(encryptFieldEntity, encryptColumnVo);
                return encryptColumnVo;
            }
        }).collect(Collectors.toList());
    }

    public boolean delete(Integer id){
        return encryptFieldService.deleteById(id);
    }


}
