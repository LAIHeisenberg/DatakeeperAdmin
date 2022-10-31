package com.longmai.datakeeper.dao.po;

import lombok.Data;

@Data
public class UserMaskingColumnPo {

    private Integer id;
    private String dbUsername;
    private String dbName;
    private String tableName;
    private String columnName;
    private String dataType;
    private Integer maskingLevel;
    private String comment;
    private String maskingMethod;
    private String maskingMethodArgs;

}
