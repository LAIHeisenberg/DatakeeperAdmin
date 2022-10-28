package com.longmai.datakeeper.service.db;

import com.longmai.datakeeper.dao.entity.EncryptFieldEntity;
import com.longmai.datakeeper.dao.mapper.EncryptFieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@Service
public class EncryptFieldService {

    @Autowired
    private EncryptFieldMapper encryptFieldMapper;

    public void createEncryptField(List<EncryptFieldEntity> entities){
        if (CollectionUtils.isEmpty(entities)){
            return;
        }
        entities.stream().forEach(new Consumer<EncryptFieldEntity>() {
            @Override
            public void accept(EncryptFieldEntity encryptFieldEntity) {
                encryptFieldEntity.setCreateTime(new Date());
                encryptFieldMapper.insert(encryptFieldEntity);
            }
        });
    }

    public List<EncryptFieldEntity> listEncryptColumn(){
        return encryptFieldMapper.selectList(null);
    }

    public boolean deleteById(Integer id){
        return encryptFieldMapper.deleteById(id) > 0;
    }

}
