package com.longmai.datakeeper.web.controller.discovery.model;

import lombok.Data;

import java.util.Date;

@Data
public class DiscoveryDataFieldView {

    private Integer id;
    private Integer taskId;
    private Integer dataSourceId;
    private String dbName;
    private String tableName;
    private String columnName;
    private String dataType;
    private Integer level;
    private String matchSensitiveRule;
    private String matchText;
    private Date createTime;

}