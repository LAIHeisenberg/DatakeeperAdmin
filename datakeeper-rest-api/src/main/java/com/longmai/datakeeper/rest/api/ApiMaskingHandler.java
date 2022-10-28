package com.longmai.datakeeper.rest.api;

import com.longmai.datakeeper.rest.dto.ApiMaskingDetailDto;
import feign.Param;
import feign.RequestLine;

public interface ApiMaskingHandler {

    @RequestLine("GET /getMasking?apiUrl={apiUrl}")
    ApiMaskingDetailDto getApiMaskingDetail(@Param("apiUrl") String apiUrl);

}
