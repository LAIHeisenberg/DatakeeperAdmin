package com.longmai.datakeeper.rest.db;

import com.longmai.datakeeper.rest.dto.DBEncryptDto;
import com.longmai.datakeeper.rest.param.DBEncryptRequest;
import feign.QueryMap;
import feign.RequestLine;


public interface DBEncryptHandler {

    @RequestLine("GET /get-encryptDto")
    DBEncryptDto getDBEncryptDto(@QueryMap DBEncryptRequest encryptRequest);

}
