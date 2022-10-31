package com.longmai.datakeeper.service.db;

import com.longmai.datakeeper.dao.mapper.DbUserMaskingColumnMapper;
import com.longmai.datakeeper.dao.po.UserMaskingColumnPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBUserMaskingColumnService {

    @Autowired
    private DbUserMaskingColumnMapper userMaskingColumnMapper;

    public List<UserMaskingColumnPo> listUserMaskingColumn(){
        return userMaskingColumnMapper.listUserMaskingColumn();
    }

    public boolean deleteById(Integer id){
        return userMaskingColumnMapper.deleteById(id) > 0;
    }

}
