package com.longmai.datakeeper.rest.provider.db;

import com.longmai.datakeeper.rest.dto.DBEncryptDto;
import com.longmai.datakeeper.rest.param.DBEncryptRequest;
import com.longmai.datakeeper.service.db.DBEncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/db")
public class DBEncryptController{

    @Autowired
    private DBEncryptService dbEncryptService;

    @GetMapping("/get-encryptDto")
    public DBEncryptDto getDBEncryptDto(DBEncryptRequest request) {
        return dbEncryptService.getDBEncryptDto(request);
    }

}
