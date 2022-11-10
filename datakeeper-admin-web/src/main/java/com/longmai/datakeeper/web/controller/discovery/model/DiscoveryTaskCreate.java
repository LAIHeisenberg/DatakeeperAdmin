package com.longmai.datakeeper.web.controller.discovery.model;

import lombok.Data;

@Data
public class DiscoveryTaskCreate {

    private String taskName;
    private String rule;
    private Integer dataSourceId;
    private Integer mode;
    private Integer period;
    private Integer scanLine;

}