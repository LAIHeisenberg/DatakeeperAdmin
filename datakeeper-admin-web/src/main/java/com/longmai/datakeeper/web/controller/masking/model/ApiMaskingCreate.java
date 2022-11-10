package com.longmai.datakeeper.web.controller.masking.model;

import lombok.Data;

@Data
public class ApiMaskingCreate {

    private String host;
    private String path;
    private String apiUrl;
    private String retType;

}