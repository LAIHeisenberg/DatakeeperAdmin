package com.longmai.datakeeper.rest.dto;

import lombok.Data;


import java.util.*;

public class DBEncryptDto {

    private String dbName;
    Map<String,List<EncryptColumnDto>> encryptTableColumnMap;

    public DBEncryptDto(String dbName){
        this.dbName = dbName;
        this.encryptTableColumnMap = new HashMap<>();
    }

    public String getDbName(){
        return this.dbName;
    }

    public Set<String> getTableNameSet(){
        return Collections.unmodifiableSet(encryptTableColumnMap.keySet());
    }

    public Map<String,List<EncryptColumnDto>> getEncryptTableColumnMap(){
        return this.encryptTableColumnMap;
    }

    public void addEncryptColumn(String tableName, EncryptColumnDto encryptColumnDto){
        if (Objects.isNull(tableName) || Objects.isNull(encryptColumnDto)){
            throw new IllegalArgumentException("tableName 和 encryptColumnDto 不能为NULL");
        }
        List<EncryptColumnDto> encryptColumnlist = encryptTableColumnMap.get(tableName.toUpperCase());
        if (encryptColumnlist == null){
            encryptColumnlist = new ArrayList<>();
            encryptTableColumnMap.put(tableName.toUpperCase(), encryptColumnlist);
        }
        encryptColumnlist.add(encryptColumnDto);
    }

    @Data
    public static class EncryptColumnDto{
        private String columnName;
        private String tableName;
        private String dbName;
        private String algorithm;
        private String secretKey;
    }

}
