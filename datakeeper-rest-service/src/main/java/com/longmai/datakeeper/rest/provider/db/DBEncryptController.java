package com.longmai.datakeeper.rest.provider.db;

import com.longmai.datakeeper.rest.db.DBEncryptHandler;
import com.longmai.datakeeper.rest.dto.DBEncryptDto;
import com.longmai.datakeeper.rest.param.DBEncryptRequest;
import com.longmai.datakeeper.service.db.DBEncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/db")
public class DBEncryptController implements DBEncryptHandler {

    @Autowired
    private DBEncryptService dbEncryptService;

    @Override
    @GetMapping("/get-encryptDto")
    public DBEncryptDto getDBEncryptDto(@RequestBody DBEncryptRequest encryptRequest) {
        System.out.println("调用成功。。。。");
        return dbEncryptService.getDBEncryptDto(encryptRequest);
    }

    @GetMapping("/test")
    public String test(){
        return "sucess";
    }

    @GetMapping("/test2")
    public DBEncryptDto test2(){


        DBEncryptRequest request = new DBEncryptRequest();
        request.setHost("192.168.1.128");
        request.setPort(3306);
        request.setDbName("eladmin");
        DBEncryptDto dbEncryptDto = dbEncryptService.getDBEncryptDto(request);
        return dbEncryptDto;
    }


}