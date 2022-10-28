package com.longmai.datakeeper.web.controller.encrypt.model;

import lombok.Data;

import java.util.List;

@Data
public class EncryptTableCreate {

    private Integer dataSourceId;
    private String tableName;
    private List<EncryptColumn> encryptColumns;

    @Data
    public static class EncryptColumn{

        private String columnName;
        private String dataType;
        private String comment;
        private String algorithm;
        private String secretKey;
        private boolean selected;

    }
}
