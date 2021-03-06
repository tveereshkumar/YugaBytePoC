package com.yugabyte.poc.util;

public interface YugaByteDBConfig {
    String[] JDBC_URL_LIST = {"jdbc:postgresql://10.128.0.42:5433/",
                              "jdbc:postgresql://10.128.0.43:5433/",
                              "jdbc:postgresql://10.128.0.44:5433/"};
    String DATABASE_NAME = "test";
    String DB_USER_NAME = "yugabyte";
    String DB_PASSWORD = "";
    String USER_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS user_master(user_id int serial primary key, name varchar, age int, language text) ";
}
