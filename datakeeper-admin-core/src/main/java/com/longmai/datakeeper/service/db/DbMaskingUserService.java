package com.longmai.datakeeper.service.db;

import com.longmai.datakeeper.dao.entity.DbMaskingUserEntity;
import com.longmai.datakeeper.dao.mapper.DbMaskingUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DbMaskingUserService {

    @Autowired
    private DbMaskingUserMapper maskingUserMapper;

    public List<DbMaskingUserEntity> listAll(){
        return maskingUserMapper.selectList(null);
    }

    public boolean create(DbMaskingUserEntity dbMaskingUserEntity){
        if (Objects.isNull(dbMaskingUserEntity)){
            return false;
        }
        dbMaskingUserEntity.setCreateTime(new Date());
        return maskingUserMapper.insert(dbMaskingUserEntity) > 0;
    }

}
