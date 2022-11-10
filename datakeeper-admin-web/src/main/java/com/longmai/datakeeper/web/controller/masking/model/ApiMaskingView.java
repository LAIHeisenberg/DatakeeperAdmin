package com.longmai.datakeeper.web.controller.masking.model;

import lombok.Data;

@Data
public class ApiMaskingView {

    private Integer id;
    private String host;
    private String path;
    private String apiUrl;
    private String retType;
    private Integer templateId;

}