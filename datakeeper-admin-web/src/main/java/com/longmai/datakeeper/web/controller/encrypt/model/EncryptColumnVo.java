package com.longmai.datakeeper.web.controller.encrypt.model;

import lombok.Data;

@Data
public class EncryptColumnVo {

    private Integer id;

    private String columnName;

    private String tableName;

    private String dbName;

    private String algorithm;

    private String secretKey;

    private String dataType;

    private String comment;

}
