package com.longmai.datakeeper.rest.provider;

import com.longmai.datakeeper.rest.dto.ApiMaskingDetailDto;
import com.longmai.datakeeper.service.ApiMaskingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/rest/api")
public class ApiMaskingController {

    @Autowired
    private ApiMaskingService apiMaskingService;

    @GetMapping("/masking")
    public List<ApiMaskingDetailDto> listAll(){
        return apiMaskingService.listApiMaskingDetailPageable(1,10);
    }

    @GetMapping("/getMasking")
    public ApiMaskingDetailDto getApiMaskingDetail(@RequestParam("apiUrl")String apiUrl){
        try {
            apiUrl = URLDecoder.decode(apiUrl, "utf-8");
            return apiMaskingService.getApiMaskingDetailByApiUrl(apiUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
