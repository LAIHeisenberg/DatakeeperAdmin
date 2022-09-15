package com.longmai.datakeeper.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("dk_api_masking")
public class ApiMaskingEntity extends BaseEntity {

    private Integer id;
    private String host;
    private String path;
    private String apiUrl;
    private String retType;
    private Integer templateId;

}
