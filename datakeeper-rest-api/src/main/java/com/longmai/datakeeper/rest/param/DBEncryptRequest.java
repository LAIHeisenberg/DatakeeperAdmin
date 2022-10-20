package com.longmai.datakeeper.rest.param;

import lombok.Data;

@Data
public class DBEncryptRequest {

    private String host;
    private Integer port;
    private String dbName;


}
