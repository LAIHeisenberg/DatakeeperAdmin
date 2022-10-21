package com.longmai.datakeeper.rest.db;

import com.longmai.datakeeper.rest.dto.DBUserMaskingDto;
import com.longmai.datakeeper.rest.param.DBMaskingRequest;
import feign.QueryMap;
import feign.RequestLine;

public interface DBUserMaskingHandler {

    @RequestLine("GET /get-userMaskingDto")
    DBUserMaskingDto getUserMaskingDto(@QueryMap DBMaskingRequest maskingRequest);

}
