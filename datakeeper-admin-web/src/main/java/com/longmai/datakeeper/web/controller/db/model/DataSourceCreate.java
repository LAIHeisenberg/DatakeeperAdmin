package com.longmai.datakeeper.web.controller.db.model;

import lombok.Data;

@Data
public class DataSourceCreate {

    private String name;

    protected String ipHost;

    protected Integer port;

    protected String type;

    protected String dbName;

    private String userName;

    private String password;

    protected String remark;

}
