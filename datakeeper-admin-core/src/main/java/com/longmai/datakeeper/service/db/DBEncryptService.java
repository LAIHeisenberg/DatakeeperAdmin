package com.longmai.datakeeper.service.db;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longmai.datakeeper.dao.entity.EncryptFieldEntity;
import com.longmai.datakeeper.dao.entity.StructDataSourceEntity;
import com.longmai.datakeeper.dao.mapper.EncryptFieldMapper;
import com.longmai.datakeeper.dao.mapper.StructDataSourceMapper;
import com.longmai.datakeeper.rest.dto.DBEncryptDto;
import com.longmai.datakeeper.rest.param.DBEncryptRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DBEncryptService {

    @Autowired
    private EncryptFieldMapper encryptFieldMapper;
    @Autowired
    private StructDataSourceMapper structDataSourceMapper;

    public DBEncryptDto getDBEncryptDto(DBEncryptRequest encryptRequest){

        QueryWrapper<StructDataSourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ip_host", encryptRequest.getHost());
        queryWrapper.eq("port", encryptRequest.getPort());
        queryWrapper.eq("db_name", encryptRequest.getDbName());
        StructDataSourceEntity dataSourceEntity = structDataSourceMapper.selectOne(queryWrapper);
        if (Objects.nonNull(dataSourceEntity)){
            Integer id = dataSourceEntity.getId();
            QueryWrapper<EncryptFieldEntity> qw = new QueryWrapper<>();
            qw.eq("db_source_id", id);
            List<EncryptFieldEntity> encryptFieldList = encryptFieldMapper.selectList(qw);
            Map<String, List<EncryptFieldEntity>> collect = encryptFieldList.stream().collect(Collectors.groupingBy((Function<EncryptFieldEntity, String>) encryptFieldEntity -> encryptFieldEntity.getTableName()));
            DBEncryptDto dbEncryptDto = new DBEncryptDto(encryptRequest.getDbName());
            collect.forEach((tableName, encryptFieldEntities) -> encryptFieldEntities.forEach(encryptFieldEntity -> {
                DBEncryptDto.EncryptColumnDto encryptColumnDto = new DBEncryptDto.EncryptColumnDto();
                BeanUtils.copyProperties(encryptFieldEntity, encryptColumnDto);
                dbEncryptDto.addEncryptColumn(tableName,encryptColumnDto);
            }));

            return dbEncryptDto;
        }
        return null;
    }

}
