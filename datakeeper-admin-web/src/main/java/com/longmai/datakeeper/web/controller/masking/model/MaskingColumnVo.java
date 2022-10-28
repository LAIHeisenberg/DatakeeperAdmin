package com.longmai.datakeeper.web.controller.masking.model;

import lombok.Data;

@Data
public class MaskingColumnVo {

    private Integer id;
    private String dbName;
    private String tableName;
    private String columnName;
    private Integer maskingLevel;
    private String maskingMethod;
    private String maskingMethodArgs;
    private String dataType;
    private String comment;

}
