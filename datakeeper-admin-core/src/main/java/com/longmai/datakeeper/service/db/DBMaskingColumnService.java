package com.longmai.datakeeper.service.db;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longmai.datakeeper.dao.entity.DbMaskingColumnEntity;
import com.longmai.datakeeper.dao.mapper.DbMaskingColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@Service
public class DBMaskingColumnService {

    @Autowired
    private DbMaskingColumnMapper dbMaskingColumnMapper;

    public List<DbMaskingColumnEntity> listAll(){
        return dbMaskingColumnMapper.selectList(null);
    }

    public List<DbMaskingColumnEntity> listBy(Integer dataSourceId, String tableName){
        QueryWrapper<DbMaskingColumnEntity> queryWrapper = new QueryWrapper();
        DbMaskingColumnEntity queryEntity = new DbMaskingColumnEntity();
        queryEntity.setDbSourceId(dataSourceId);
        queryEntity.setTableName(tableName);
        queryWrapper.setEntity(queryEntity);

        return dbMaskingColumnMapper.selectList(queryWrapper);
    }

    public boolean createMaskingColumn(List<DbMaskingColumnEntity> entityList){
        if (CollectionUtils.isEmpty(entityList)){
            return false;
        }
        entityList.forEach(new Consumer<DbMaskingColumnEntity>() {
            @Override
            public void accept(DbMaskingColumnEntity dbMaskingColumnEntity) {
                dbMaskingColumnEntity.setCreateTime(new Date());
                dbMaskingColumnMapper.insert(dbMaskingColumnEntity);
            }
        });
        return true;
    }

    public boolean deleteById(Integer id){
        return dbMaskingColumnMapper.deleteById(id)>0;
    }


}
