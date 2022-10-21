package com.longmai.datakeeper.rest.api;

import com.longmai.datakeeper.rest.dto.ApiMaskingDetailDto;

import java.util.List;

//@Component
//@FeignClient(
//        value = "ApiMaskingHandler",
//        url = "http://192.168.1.128:8060/rest/api"
//)
public interface ApiMaskingHandler {

//    @GetMapping("/masking")
    List<ApiMaskingDetailDto> listAll();

//    @GetMapping("/getMasking")
//    ApiMaskingDetailDto getApiMaskingDetail(@RequestParam("apiUrl")String apiUrl);
    ApiMaskingDetailDto getApiMaskingDetail(String apiUrl);

}
