package com.longmai.datakeeper.service.db;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longmai.datakeeper.dao.entity.StructDataSourceEntity;
import com.longmai.datakeeper.dao.mapper.StructDataSourceMapper;
import com.longmai.datakeeper.utils.TimeZoneUtil;
import com.longmai.datakeeper.vo.StructDataSourceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StructDataSourceService {

    @Autowired
    private StructDataSourceMapper sourceMapper;

    public List<StructDataSourceVo> listAll(){
        QueryWrapper<StructDataSourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        List<StructDataSourceEntity> list = sourceMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)){
            return list.stream().map(new Function<StructDataSourceEntity, StructDataSourceVo>() {
                @Override
                public StructDataSourceVo apply(StructDataSourceEntity structDataSourceEntity) {
                    StructDataSourceVo structDataSourceVo = new StructDataSourceVo();
                    BeanUtils.copyProperties(structDataSourceEntity, structDataSourceVo);
//                    structDataSourceVo.setCreateTimeStr(TimeZoneUtil.localDate2ChineseDateStr(structDataSourceEntity.getCreateTime()));
                    return structDataSourceVo;
                }
            }).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    public boolean create(StructDataSourceEntity dataSourceEntity){
        if (Objects.isNull(dataSourceEntity)){
            return false;
        }
        dataSourceEntity.setStatus(1);
        dataSourceEntity.setCreateTime(new Date(Instant.now().toEpochMilli()));
        return sourceMapper.insert(dataSourceEntity) > 0;
    }

    public boolean update(StructDataSourceEntity dataSourceEntity){
        dataSourceEntity.setUpdateTime(new Date(Instant.now().toEpochMilli()));
        return sourceMapper.updateById(dataSourceEntity) > 0;
    }

    public StructDataSourceEntity getDataSourceEntityById(Integer id){
        return sourceMapper.selectById(id);
    }

}
