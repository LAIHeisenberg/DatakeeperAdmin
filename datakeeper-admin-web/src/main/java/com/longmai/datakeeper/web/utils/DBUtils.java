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
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.a.result.ByteArrayRow;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;
import com.mysql.cj.result.Field;
import javafx.scene.control.Tab;
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

    public static List<String> introspectDataBase(String jdbcUrl, String dbName, String user, String password) throws Exception{

        List<String> tableNameList = new ArrayList<>();
        Connection connection = getConnection(jdbcUrl, user, password);
        String sql = "show tables";
        Statement statement = connection.createStatement();
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();
        resultSet.first();
        while (!resultSet.isAfterLast()){
            String tableName = resultSet.getString(String.format("Tables_in_%s", dbName));
            resultSet.next();
            tableNameList.add(tableName);
        }

        return tableNameList;
    }

    public static TableResult fetchRows(String jdbcUrl, String tableName, String user, String password, Integer ... limitNum) throws Exception{
        Connection connection = getConnection(jdbcUrl, user, password);
        Integer limitArg_1 = 0;
        Integer limitArg_2 = 10;
        if (limitNum != null &&  limitNum.length == 1){
            limitArg_2 = Math.max(1, limitNum[0]);
        }
        if (limitNum != null && limitNum.length == 2){
            limitArg_1 = Math.max(limitNum[0], 0);
            limitArg_2 = Math.max(limitNum[1], 1);
        }
        String sql = new String().format("select * from %s limit %s, %s",tableName, limitArg_1, limitArg_2);
        Statement statement = connection.createStatement();
        ResultSetImpl resultSet = (ResultSetImpl) statement.executeQuery(sql);
        resultSet.first();
        TableResult tableResult = new TableResult();
        tableResult.setTableName(tableName);
        List<TableResult.Row> rows = new ArrayList<>();
        while (!resultSet.isAfterLast() && !resultSet.getRows().isEmpty()){
            ColumnDefinition columnDefinition = resultSet.getColumnDefinition();
            Field[] fields = columnDefinition.getFields();
            List<TableResult.Column> columns = new ArrayList<>();
            TableResult.Row row = new TableResult.Row();
            row.setId(resultSet.getRow());
            for (int i=0; i<fields.length; i++){
                Field field = fields[i];
                String columnLabel = field.getOriginalName();
                String value = resultSet.getString(columnLabel);
                System.out.println(columnLabel+" : "+value);
                TableResult.Column column = new TableResult.Column();
                column.setLabelName(columnLabel);
                column.setValue(value);
                column.setJavaType(field.getMysqlType().getClassName());
                column.setDataType(field.getMysqlType().getName());
                columns.add(column);
                row.setColumns(columns);
            }
            rows.add(row);
            resultSet.next();
            System.out.println("===============================================");
        }
        tableResult.setRows(rows);
        return tableResult;
    }



    public static void main(String[] args) throws Exception {
        String jdbcUrl = getJdbcUrl("192.168.1.128", 3306, "eladmin");
//        List<ColumnDef> columnDefs = introspectTable(jdbcUrl,"laiyz", "laiyz123!", "ddm_user");
//        System.out.println(columnDefs);
//        introspectDataBase(jdbcUrl,"eladmin","laiyz", "laiyz123!");


        fetchRows(jdbcUrl,"ddm_user","laiyz", "laiyz123!",100);
    }

    @Data
    public static class ColumnDef {

        protected String tableName;
        protected String columnName;
        protected String dataType;
        protected String comment;

    }

    @Data
    public static class TableResult {

        private String tableName;
        private List<Row> rows;

        @Data
        public static class Row {
            private Integer id;
            List<Column> columns;
        }

        @Data
        public static class Column{
            private String labelName;
            private String value;
            private String javaType;
            private String dataType;
        }

    }



}
