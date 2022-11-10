package com.longmai.datakeeper.web.controller.masking.facade;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longmai.datakeeper.dao.entity.ApiMaskingEntity;
import com.longmai.datakeeper.dto.ApiMaskingDto;
import com.longmai.datakeeper.dto.UserLoginDto;
import com.longmai.datakeeper.service.impl.ApiMaskingServiceImpl;
import com.longmai.datakeeper.utils.PageUtil;
import com.longmai.datakeeper.web.controller.masking.model.ApiMaskingCreate;
import com.longmai.datakeeper.web.controller.masking.model.ApiMaskingQueryParam;
import com.longmai.datakeeper.web.controller.masking.model.ApiMaskingView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiMaskingFacade {

    @Autowired
    private ApiMaskingServiceImpl serviceImpl;

    public Map<String,Object> listPage(ApiMaskingQueryParam queryParam, Integer pageNum, Integer pageSize){
        ApiMaskingEntity queryEntity = new ApiMaskingEntity();
        BeanUtils.copyProperties(queryParam, queryEntity);
        Page<ApiMaskingDto> page = serviceImpl.listPage(queryEntity, pageNum, pageSize);
        List<ApiMaskingView> view = convertView(page.getRecords());
        return PageUtil.toPage(view, page.getTotal());
    }

    public boolean create(ApiMaskingCreate createBean, UserLoginDto userLoginDto){
        ApiMaskingEntity newEntity = new ApiMaskingEntity();
        BeanUtils.copyProperties(createBean, newEntity);
        newEntity.setCreateById(userLoginDto.getId().intValue());
        newEntity.setCreateByName(userLoginDto.getUserName());
        return serviceImpl.create(newEntity);
    }

    public boolean delete(Integer id){
        return serviceImpl.deleteById(id);
    }

    private List<ApiMaskingView> convertView(List<ApiMaskingDto> dtoList){
        if (CollectionUtils.isEmpty(dtoList)){
            return Collections.emptyList();
        }
        return dtoList.stream().map(dto -> {
            ApiMaskingView view = new ApiMaskingView();
            BeanUtils.copyProperties(dto, view);
            return view;
        }).collect(Collectors.toList());
    }

}