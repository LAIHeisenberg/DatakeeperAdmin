package com.longmai.datakeeper.web.controller.discovery.model;

import lombok.Data;

@Data
public class DiscoveryTaskQueryParam {

    private String taskName;
    private String rule;
    private Integer mode;
    private Integer period;
    private Integer riskLevel;
    private Integer status;

}