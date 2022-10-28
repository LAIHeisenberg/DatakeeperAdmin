package com.longmai.datakeeper.vo;

import lombok.Data;

@Data
public class StructDataSourceVo {

    private Integer id;

    private String name;

    private String ipHost;

    private Integer port;

    private String type;

    private String dbName;

    private String userName;

    private String remark;

    private String createTimeStr;

}
