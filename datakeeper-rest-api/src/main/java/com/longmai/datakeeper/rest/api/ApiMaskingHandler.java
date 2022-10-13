package com.longmai.datakeeper.rest.api;

import com.longmai.datakeeper.rest.dto.ApiMaskingDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Component
@FeignClient(
        value = "ApiMaskingHandler",
        url = "http://192.168.1.128:8060/rest/api"
)
public interface ApiMaskingHandler {

    @GetMapping("/masking")
    List<ApiMaskingDetailDto> listAll();

    @GetMapping("/getMasking")
    ApiMaskingDetailDto getApiMaskingDetail(@RequestParam("apiUrl")String apiUrl);

}
