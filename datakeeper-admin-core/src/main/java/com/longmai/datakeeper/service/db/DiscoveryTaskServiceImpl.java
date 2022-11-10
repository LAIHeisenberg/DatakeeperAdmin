package com.longmai.datakeeper.service.db;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longmai.datakeeper.dao.entity.DiscoveryTask;
import com.longmai.datakeeper.dao.mapper.DiscoveryTaskMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longmai.datakeeper.dto.DiscoveryTaskDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author longmai
 * @since 2022-11-11
 */
@Service
public class DiscoveryTaskServiceImpl extends ServiceImpl<DiscoveryTaskMapper, DiscoveryTask> implements DiscoveryTaskService {

    @Override
    public Page<DiscoveryTaskDto> listPage(DiscoveryTask param, Integer pageNum, Integer pageSize){

        QueryWrapper<DiscoveryTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(param);
        Page<DiscoveryTask> page = this.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<DiscoveryTaskDto> records = page.getRecords().stream().map(new Function<DiscoveryTask, DiscoveryTaskDto>() {
            @Override
            public DiscoveryTaskDto apply(DiscoveryTask entity) {
                DiscoveryTaskDto dto = new DiscoveryTaskDto();
                BeanUtils.copyProperties(entity,dto);
                return dto;
            }
        }).collect(Collectors.toList());

        Page<DiscoveryTaskDto> resultPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        resultPage.setRecords(records);

        return resultPage;
    }

    @Override
    public DiscoveryTaskDto getById(Integer id){
        DiscoveryTask task = super.getById(id);
        if (Objects.isNull(task)){
            return null;
        }
        DiscoveryTaskDto dto = new DiscoveryTaskDto();
        BeanUtils.copyProperties(task, dto);
        return dto;
    }

    @Override
    public boolean updateStatus(Integer id, DiscoveryTask.Status status){
        DiscoveryTask updatePo = new DiscoveryTask();
        updatePo.setId(id);
        updatePo.setStatus(status.getValue());
        updatePo.setUpdateTime(new Date());
        return super.updateById(updatePo);
    }

    @Override
    public boolean create(DiscoveryTask entity){
        if (Objects.isNull(entity)){
            return false;
        }
        entity.setCreateTime(new Date());
        return this.save(entity);
    }

    @Override
    public boolean deleteById(Integer id){
        if (Objects.isNull(id)){
            return false;
        }
        return this.removeById(id);
    }
}
