package com.longmai.datakeeper.dao.mapper;

import com.longmai.datakeeper.dao.po.UserPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPo> {

}
