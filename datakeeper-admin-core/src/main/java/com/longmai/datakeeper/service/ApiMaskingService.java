package com.longmai.datakeeper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longmai.datakeeper.dao.entity.ApiMaskingEntity;
import com.longmai.datakeeper.dto.ApiMaskingDto;
import com.longmai.datakeeper.rest.dto.ApiMaskingDetailDto;

import java.util.List;

public interface ApiMaskingService {
    List<ApiMaskingDetailDto> listApiMaskingDetailPageable(Integer pageNum, Integer pageSize);

    ApiMaskingDetailDto getApiMaskingDetailByApiUrl(String apiUrl);

    Page<ApiMaskingDto> listPage(ApiMaskingEntity param, Integer pageNum, Integer pageSize);

    boolean create(ApiMaskingEntity entity);

    boolean deleteById(Integer id);
}
