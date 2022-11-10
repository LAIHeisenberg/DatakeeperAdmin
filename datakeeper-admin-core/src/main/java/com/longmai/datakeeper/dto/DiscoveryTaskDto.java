package com.longmai.datakeeper.dto;

import lombok.Data;
import java.util.Date;

@Data
public class DiscoveryTaskDto {

    private Integer id;
    private String taskName;
    private Integer dataSourceId;
    private String dbName;
    private String rule;
    private Integer mode;
    private Integer period;
    private Integer riskLevel;
    private Integer beginScanLine;
    private Integer endScanLine;
    private Integer status;
    private String createByName;
    private Date createTime;

}