package com.longmai.datakeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longmai.datakeeper.dao.entity.ApiFieldMaskingEntity;
import com.longmai.datakeeper.dao.entity.ApiMaskingEntity;
import com.longmai.datakeeper.dao.mapper.ApiFieldMaskingMapper;
import com.longmai.datakeeper.dao.mapper.ApiMaskingMapper;
import com.longmai.datakeeper.dto.ApiFieldMaskingDto;
import com.longmai.datakeeper.dto.ApiMaskingDetailDto;
import com.longmai.datakeeper.service.ApiMaskingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ApiMaskingServiceImpl implements ApiMaskingService {

    @Autowired
    private ApiMaskingMapper apiMaskingMapper;
    @Autowired
    private ApiFieldMaskingMapper apiFieldMaskingMapper;


    @Override
    public List<ApiMaskingDetailDto> listApiMaskingDetailPageable(Integer pageNum, Integer pageSize){

        Page<ApiMaskingEntity> apiMaskingEntityPage = apiMaskingMapper.selectPage(new Page<>(pageNum, pageNum), null);
        return apiMaskingEntityPage.getRecords().stream().map(new Function<ApiMaskingEntity, ApiMaskingDetailDto>() {

            @Override
            public ApiMaskingDetailDto apply(ApiMaskingEntity apiMaskingEntity) {
                ApiMaskingDetailDto detailDto = new ApiMaskingDetailDto();
                BeanUtils.copyProperties(apiMaskingEntity,detailDto);
                Integer id = apiMaskingEntity.getId();
                detailDto.setFields(getApiFieldMaskingDto(id));
                return detailDto;
            }
        }).collect(Collectors.toList());

    }

    @Override
    public ApiMaskingDetailDto getApiMaskingDetailByApiUrl(String apiUrl){

        ApiMaskingDetailDto apiMaskingDetailDto = null;
        QueryWrapper<ApiMaskingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("api_url", apiUrl);
        ApiMaskingEntity apiMaskingEntity = apiMaskingMapper.selectOne(queryWrapper);
        if (Objects.nonNull(apiMaskingEntity)){
            apiMaskingDetailDto = new ApiMaskingDetailDto();
            BeanUtils.copyProperties(apiMaskingEntity, apiMaskingDetailDto);

            Integer id = apiMaskingEntity.getId();
            apiMaskingDetailDto.setFields(getApiFieldMaskingDto(id));

        }
        return apiMaskingDetailDto;
    }


    public List<ApiFieldMaskingDto> getApiFieldMaskingDto(Integer apiId){

        if (apiId == null){
            return Collections.emptyList();
        }
        QueryWrapper<ApiFieldMaskingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("api_id", apiId);
        List<ApiFieldMaskingEntity> apiFieldMaskEntities = apiFieldMaskingMapper.selectList(queryWrapper);

        return apiFieldMaskEntities.stream().map(new Function<ApiFieldMaskingEntity, ApiFieldMaskingDto>() {
            @Override
            public ApiFieldMaskingDto apply(ApiFieldMaskingEntity apiFieldMaskingEntity) {
                ApiFieldMaskingDto apiFieldMaskingDto = new ApiFieldMaskingDto();
                BeanUtils.copyProperties(apiFieldMaskingEntity, apiFieldMaskingDto);
                return apiFieldMaskingDto;
            }
        }).collect(Collectors.toList());

    }


}
