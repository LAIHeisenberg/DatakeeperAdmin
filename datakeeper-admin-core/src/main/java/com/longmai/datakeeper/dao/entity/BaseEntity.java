package com.longmai.datakeeper.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseEntity {

    protected Integer createById;
    protected String createByName;
    protected Integer updateById;
    protected String updateByName;
    protected Date createTime;
    protected Date updateTime;
}
