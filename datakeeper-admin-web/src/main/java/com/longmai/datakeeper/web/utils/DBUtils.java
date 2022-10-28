package com.longmai.datakeeper.web.utils;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLDataType;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.protocol.a.result.ByteArrayRow;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;
import lombok.Data;

import java.sql.*;
import java.util.*;

public class DBUtils {

    private static Driver driver;

    private static String jdbcUrl = "jdbc:mysql://%s:%s/%s";

    static {
        try {
            driver = new Driver();
            DriverManager.registerDriver(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getJdbcUrl(String host, Integer port, String dbName){
        return String.format(jdbcUrl,host, port, dbName);
    }


    public static boolean testConnection(String jdbcUrl, String user, String password){
        try {
            return getConnection(jdbcUrl, user, password).isValid(15);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static Connection getConnection(String jdbcUrl, String user, String password){

        Connection connection = null;
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        try {
            connection = driver.connect(jdbcUrl, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static List<ColumnDef> introspectTable(String jdbcUrl, String user, String password, String tableName){

        Connection connection = getConnection(jdbcUrl, user, password);
        String sql = "show create table "+ tableName;
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            resultSet.first();
            String createTable = resultSet.getString("Create Table");
            MySqlCreateTableStatement createSqlStatement = (MySqlCreateTableStatement)SQLUtils.parseSingleMysqlStatement(createTable);
            createSqlStatement.getTableName();
            List<SQLColumnDefinition> columnDefinitions = createSqlStatement.getColumnDefinitions();
            List<ColumnDef> columnDefList = new ArrayList<>();
            for (SQLColumnDefinition definition : columnDefinitions){
                ColumnDef columnDef = new ColumnDef();
                String columnName = definition.getColumnName();
                columnDef.setColumnName(columnName.replace("`",""));
                SQLCharExpr comment = (SQLCharExpr) definition.getComment();
                if (Objects.nonNull(comment)){
                    columnDef.setComment(comment.getText());
                }
                SQLDataType dataType = definition.getDataType();
                columnDef.setDataType(dataType.getName());
                columnDef.setTableName(tableName);

                columnDefList.add(columnDef);
            }
            return columnDefList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }


    public static void main(String[] args){

        String jdbcUrl = getJdbcUrl("192.168.1.128", 3306, "eladmin");

        List<ColumnDef> columnDefs = introspectTable(jdbcUrl,"laiyz", "laiyz123!", "ddm_user");
        System.out.println(columnDefs);
    }

    @Data
    public static class ColumnDef {

        protected String tableName;
        protected String columnName;
        protected String dataType;
        protected String comment;

    }

}
