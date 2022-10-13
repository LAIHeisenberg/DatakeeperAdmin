package com.longmai.datakeeper.service;

import com.longmai.datakeeper.rest.dto.ApiMaskingDetailDto;

import java.util.List;

public interface ApiMaskingService {
    List<ApiMaskingDetailDto> listApiMaskingDetailPageable(Integer pageNum, Integer pageSize);

    ApiMaskingDetailDto getApiMaskingDetailByApiUrl(String apiUrl);
}
