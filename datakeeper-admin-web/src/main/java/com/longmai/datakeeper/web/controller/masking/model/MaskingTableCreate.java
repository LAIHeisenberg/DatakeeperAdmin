package com.longmai.datakeeper.web.controller.masking.model;

import lombok.Data;

import java.util.List;

@Data
public class MaskingTableCreate {

    private Integer dataSourceId;
    private String tableName;
    private List<MaskingColumn> maskingColumns;

    @Data
    public static class MaskingColumn{

        private String columnName;
        private String dataType;
        private String comment;
        private Integer maskingLevel;
        private String maskingMethod;
        private String maskingMethodArgs;
        private boolean selected;

    }
}
