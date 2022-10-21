package com.longmai.datakeeper.rest.provider.db;

import com.longmai.datakeeper.rest.dto.DBUserMaskingDto;
import com.longmai.datakeeper.rest.param.DBMaskingRequest;
import com.longmai.datakeeper.service.db.DBMaskingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/db")
public class DBMaskingController {

    @Autowired
    private DBMaskingService dbMaskingService;

    @GetMapping("/get-userMaskingDto")
    public DBUserMaskingDto getUserMaskingDto(DBMaskingRequest maskingRequest){
        return dbMaskingService.getUserMaskingColumnDto(maskingRequest);
    }

}
