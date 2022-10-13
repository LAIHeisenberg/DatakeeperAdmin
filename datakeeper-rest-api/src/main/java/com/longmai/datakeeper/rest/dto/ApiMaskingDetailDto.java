package com.longmai.datakeeper.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiMaskingDetailDto {

    private Integer id;
    private String host;
    private String path;
    private String apiUrl;
    private String retType;
    private Integer templateId;

    private List<ApiFieldMaskingDto> fields;

}
