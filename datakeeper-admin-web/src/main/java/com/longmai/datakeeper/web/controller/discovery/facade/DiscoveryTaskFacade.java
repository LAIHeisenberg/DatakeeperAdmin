package com.longmai.datakeeper.web.controller.discovery.facade;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longmai.datakeeper.dao.entity.DiscoveryTask;
import com.longmai.datakeeper.dao.entity.StructDataSourceEntity;
import com.longmai.datakeeper.dto.DiscoveryTaskDto;
import com.longmai.datakeeper.dto.UserLoginDto;
import com.longmai.datakeeper.service.db.DiscoveryTaskService;
import com.longmai.datakeeper.service.db.StructDataSourceService;
import com.longmai.datakeeper.utils.PageUtil;
import com.longmai.datakeeper.web.controller.discovery.model.DiscoveryTaskCreate;
import com.longmai.datakeeper.web.controller.discovery.model.DiscoveryTaskQueryParam;
import com.longmai.datakeeper.web.controller.discovery.model.DiscoveryTaskView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DiscoveryTaskFacade {

    @Autowired
    private DiscoveryTaskService serviceImpl;
    @Autowired
    private StructDataSourceService dataSourceService;

    public Map<String,Object> listPage(DiscoveryTaskQueryParam queryParam, Integer pageNum, Integer pageSize){
        DiscoveryTask queryEntity = new DiscoveryTask();
        BeanUtils.copyProperties(queryParam, queryEntity);
        Page<DiscoveryTaskDto> page = serviceImpl.listPage(queryEntity, pageNum, pageSize);
        List<DiscoveryTaskView> view = convertView(page.getRecords());
        return PageUtil.toPage(view, page.getTotal());
    }

    public boolean create(DiscoveryTaskCreate createBean, UserLoginDto userLoginDto){
        StructDataSourceEntity dataSource = dataSourceService.getDataSourceEntityById(createBean.getDataSourceId());
        if (Objects.isNull(dataSource)){
            throw new IllegalArgumentException("未找到数据源");
        }
        DiscoveryTask newEntity = new DiscoveryTask();
        BeanUtils.copyProperties(createBean, newEntity);
        newEntity.setDataSourceId(dataSource.getId());
        newEntity.setDbName(dataSource.getDbName());
        newEntity.setBeginScanLine(0);
        newEntity.setEndScanLine(createBean.getScanLine());
        newEntity.setCreateById(userLoginDto.getId().intValue());
        newEntity.setCreateByName(userLoginDto.getUserName());
        newEntity.setStatus(1);
        return serviceImpl.create(newEntity);
    }

    public boolean delete(Integer id){
        return serviceImpl.deleteById(id);
    }

    private List<DiscoveryTaskView> convertView(List<DiscoveryTaskDto> dtoList){
        if (CollectionUtils.isEmpty(dtoList)){
            return Collections.emptyList();
        }
        return dtoList.stream().map(dto -> {
            DiscoveryTaskView view = new DiscoveryTaskView();
            BeanUtils.copyProperties(dto, view);
            view.setStatus(DiscoveryTask.Status.getDesc(dto.getStatus()));
            view.setPeriod(DiscoveryTask.Period.getDesc(dto.getPeriod()));
            return view;
        }).collect(Collectors.toList());
    }

}