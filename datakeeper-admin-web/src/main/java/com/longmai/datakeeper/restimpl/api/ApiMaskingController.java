package com.longmai.datakeeper.restimpl.api;

import com.longmai.datakeeper.rest.api.ApiMaskingHandler;
import com.longmai.datakeeper.rest.dto.ApiMaskingDetailDto;
import com.longmai.datakeeper.service.ApiMaskingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restimpl/api")
public class ApiMaskingController {

    @Autowired
    private ApiMaskingHandler apiMaskingHandler;

    @GetMapping("/masking")
    public List<ApiMaskingDetailDto> listAll(){
        return apiMaskingHandler.listAll();
    }

}
