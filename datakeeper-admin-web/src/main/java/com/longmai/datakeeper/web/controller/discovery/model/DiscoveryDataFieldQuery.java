package com.longmai.datakeeper.web.controller.discovery.model;

import lombok.Data;

@Data
public class DiscoveryDataFieldQuery {

    private Integer id;
    private Integer taskId;
    private Integer dataSourceId;
    private String dbName;
    private String tableName;
    private Integer level;

}