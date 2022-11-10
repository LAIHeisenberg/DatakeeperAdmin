package com.longmai.datakeeper.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DiscoveryDataFieldDto {

    private Integer id;
    private Integer taskId;
    private Integer dataSourceId;
    private String dbName;
    private Integer level;
    private String tableName;
    private String columnName;
    private String dataType;
    private String matchSensitiveRule;
    private String matchText;
    private Date createTime;

}
