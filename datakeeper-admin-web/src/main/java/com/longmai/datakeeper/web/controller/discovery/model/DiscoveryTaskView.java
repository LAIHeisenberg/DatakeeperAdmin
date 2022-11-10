package com.longmai.datakeeper.web.controller.discovery.model;

import lombok.Data;
import java.util.Date;

@Data
public class DiscoveryTaskView {

    private Integer id;
    private String taskName;
    private String dbName;
    private String rule;
    private String period;
    private Integer riskLevel;
    private String status;
    private String createByName;
    private Date createTime;

}