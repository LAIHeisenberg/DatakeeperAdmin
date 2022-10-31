package com.longmai.datakeeper.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longmai.datakeeper.dao.entity.DbUserMaskingColumnEntity;
import com.longmai.datakeeper.dao.po.UserMaskingColumnPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author longmai
 * @since 2022-10-21
 */
@Mapper
public interface DbUserMaskingColumnMapper extends BaseMapper<DbUserMaskingColumnEntity> {

    List<UserMaskingColumnPo> listUserMaskingColumn();

}
