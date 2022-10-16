package com.longmai.datakeeper.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("dk_api_field_masking")
@Data
public class ApiFieldMaskingEntity extends BaseEntity {

    private Integer id;
    private Integer apiId;
    private String fieldName;
    private String regex;
    private String maskingSymbol;
    private Integer maskingLevel;
    private Integer algorithmId;

}
