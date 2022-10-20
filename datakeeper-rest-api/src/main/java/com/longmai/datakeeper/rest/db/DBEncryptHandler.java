package com.longmai.datakeeper.rest.db;

import com.longmai.datakeeper.rest.dto.DBEncryptDto;
import com.longmai.datakeeper.rest.param.DBEncryptRequest;
import feign.Body;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Component
@FeignClient(
        value = "DBEncryptHandler",
        url = "http://192.168.1.128:8060/rest/db"
)
public interface DBEncryptHandler {

//    @RequestLine("GET /get-encryptDto")
//    DBEncryptDto getDBEncryptDto(@Param("encryptRequest") DBEncryptRequest encryptRequest);

    @GetMapping("/get-encryptDto")
    DBEncryptDto getDBEncryptDto(@RequestBody DBEncryptRequest encryptRequest);

//    @RequestLine("GET /test")
//    String test();
//
//    @RequestLine("GET /test2")
//    DBEncryptDto test2();
}
