package com.longmai.datakeeper.service.db;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longmai.datakeeper.dao.entity.DiscoveryDataField;
import com.longmai.datakeeper.dao.mapper.DiscoveryDataFieldMapper;
import com.longmai.datakeeper.dto.DiscoveryDataFieldDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DiscoveryDataFieldServiceImpl extends ServiceImpl<DiscoveryDataFieldMapper, DiscoveryDataField> implements DiscoveryDataFieldService {

    @Override
    public Page<DiscoveryDataFieldDto> listPage(DiscoveryDataField param, Integer pageNum, Integer pageSize){

        QueryWrapper<DiscoveryDataField> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(param);
        Page<DiscoveryDataField> page = this.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<DiscoveryDataFieldDto> records = page.getRecords().stream().map(new Function<DiscoveryDataField, DiscoveryDataFieldDto>() {
            @Override
            public DiscoveryDataFieldDto apply(DiscoveryDataField entity) {
                DiscoveryDataFieldDto dto = new DiscoveryDataFieldDto();
                BeanUtils.copyProperties(entity,dto);
                return dto;
            }
        }).collect(Collectors.toList());

        Page<DiscoveryDataFieldDto> resultPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        resultPage.setRecords(records);

        return resultPage;
    }

    @Override
    public DiscoveryDataFieldDto getById(Integer id){
        DiscoveryDataField entity = super.getById(id);
        if (Objects.isNull(entity)){
            return null;
        }
        DiscoveryDataFieldDto dto = new DiscoveryDataFieldDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public boolean create(DiscoveryDataField entity){
        if (Objects.isNull(entity)){
            return false;
        }
        entity.setCreateTime(new Date());
        return this.save(entity);
    }

    @Override
    public boolean batchSave(List<DiscoveryDataField> entityList, Integer batchSize){
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public boolean deleteById(Integer id){
        if (Objects.isNull(id)){
            return false;
        }
        return this.removeById(id);
    }

}