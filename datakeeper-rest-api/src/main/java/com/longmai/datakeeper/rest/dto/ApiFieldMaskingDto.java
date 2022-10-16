package com.longmai.datakeeper.rest.dto;

import lombok.Data;

@Data
public class ApiFieldMaskingDto {

    private Integer id;
    private Integer apiId;
    private String fieldName;
    private String regex;
    private String maskingSymbol;
    private Integer maskingLevel;
    private Integer algorithmId;

}
