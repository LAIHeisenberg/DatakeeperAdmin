package com.longmai.datakeeper.web.controller.masking.model;

import lombok.Data;

@Data
public class MaskingUserCreate {
    private String realName;

    private Integer dbSourceId;

    private String dbUsername;

    private Integer permLevel;

    private String remark;
}
