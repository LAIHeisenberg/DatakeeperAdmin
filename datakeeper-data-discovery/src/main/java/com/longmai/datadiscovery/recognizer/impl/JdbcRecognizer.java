package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class JdbcRecognizer extends AbstractRecognizer {

    private static final String JDBC_URL_SQLSERVER = "(jdbc:sqlserver://.+:\\d+)";
    private static final String JDBC_URL_ORACLE = "(jdbc:oracle:thin:@//.+:\\d+:/.*)|(jdbc:oracle:thin:@.+:\\d+:.+)";
    private static final String JDBC_URL_DB2 = "(jdbc:db2://.+(:\\d+)?/.+)|(jdbc:db2:.+)";
    private static final String JDBC_URL_MYSQL = "(jdbc:mysql://.+:\\d+/.+)";
    private static final String JDBC_URL_POSTGRESQL = "(jdbc:postgresql://.+:\\d+/.+)";
    private static final String JDBC_URL_SQLITE = "jdbc:sqlite:.+";

    public JdbcRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return JDBC_URL_MYSQL.concat("|").concat(JDBC_URL_ORACLE).concat("|").concat(JDBC_URL_SQLSERVER)
                .concat("|").concat(JDBC_URL_POSTGRESQL).concat("|").concat(JDBC_URL_DB2).concat("|").concat(JDBC_URL_SQLITE);
    }

    public static void main(String[] args){
        String text = " asdfasdfejdbc:sqlite:datakeeper-admin-web/src/main/resources/datakeeper-db.sqlite";
        JdbcRecognizer jdbcRecognizer = new JdbcRecognizer(null);
        System.out.println(jdbcRecognizer.recognize(text));
    }
}
