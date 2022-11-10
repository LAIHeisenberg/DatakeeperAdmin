package com.longmai.datakeeper.dto;

import lombok.Data;

@Data
public class ApiMaskingDto {

    private Integer id;
    private String host;
    private String path;
    private String apiUrl;
    private String retType;
    private Integer templateId;

}