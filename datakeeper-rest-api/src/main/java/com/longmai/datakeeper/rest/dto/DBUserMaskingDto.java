package com.longmai.datakeeper.rest.dto;

import lombok.Data;

import java.util.*;

public class DBUserMaskingDto {

    private String user;
    private String dbName;
    Map<String, List<DBUserMaskingDto.MaskingColumnDto>> maskingTableColumnMap;

    public DBUserMaskingDto(String user, String dbName){
        this.user = user;
        this.dbName = dbName;
        this.maskingTableColumnMap = new HashMap<>();
    }


    public String getDbName(){
        return this.dbName;
    }

    public String getUser(){
        return this.user;
    }

    public Set<String> getTableNameSet(){
        return Collections.unmodifiableSet(maskingTableColumnMap.keySet());
    }

    public Map<String,List<DBUserMaskingDto.MaskingColumnDto>> getMaskingTableColumnMap(){
        return this.maskingTableColumnMap;
    }

    public void addMaskingColumn(String tableName, DBUserMaskingDto.MaskingColumnDto maskingColumnDto){
        if (Objects.isNull(tableName) || Objects.isNull(maskingColumnDto)){
            throw new IllegalArgumentException("tableName 和 encryptColumnDto 不能为NULL");
        }
        List<DBUserMaskingDto.MaskingColumnDto> maskingColumnlist = maskingTableColumnMap.get(tableName);
        if (maskingColumnlist == null){
            maskingColumnlist = new ArrayList<>();
            maskingTableColumnMap.put(tableName, maskingColumnlist);
        }
        maskingColumnlist.add(maskingColumnDto);
    }

    @Data
    public static class MaskingColumnDto{
        private String columnName;
        private String tableName;
        private String dbName;
        private String maskingMethod;
        private String maskingMethodArgs;
    }

}
