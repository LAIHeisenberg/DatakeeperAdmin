package com.longmai.datakeeper.rest.dto;

import lombok.Data;

@Data
public class ApiFieldMaskingDto {

    private Integer id;
    private Integer apiId;
    private String fieldName;
    private Integer maskingLevel;
    private Integer algorithmId;

}
