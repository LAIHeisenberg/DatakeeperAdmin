package com.longmai.datakeeper.rest.param;

import lombok.Data;

@Data
public class DBMaskingRequest {

    private String user;
    private String host;
    private Integer port;
    private String dbName;

}
