package com.longmai.datakeeper.rest.dto;

import lombok.Data;
import org.springframework.util.StringUtils;

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
        if (StringUtils.isEmpty(tableName) || Objects.isNull(encryptColumnDto)){
            throw new IllegalArgumentException("tableName 和 encryptColumnDto 不能为NULL");
        }
        List<EncryptColumnDto> encryptColumnlist = encryptTableColumnMap.get(tableName);
        if (encryptColumnlist == null){
            encryptColumnlist = new ArrayList<>();
            encryptTableColumnMap.put(tableName, encryptColumnlist);
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
