package com.yugabyte.poc.util;

public interface YugaByteDBConfig {
    String DATABASE_NAME = "test";
    String[] JDBC_URL_LIST = {"jdbc:postgresql://10.128.0.42:5433/"+DATABASE_NAME,
                              "jdbc:postgresql://10.128.0.43:5433/"+DATABASE_NAME,
                              "jdbc:postgresql://10.128.0.44:5433/"+DATABASE_NAME};
    String DB_USER_NAME = "yugabyte";
    String DB_PASSWORD = "";
    String USER_TABLE_QUERY = "CREATE TABLE user_master(user_id int NOT NULL DEFAULT nextval('table_name_id_seq') primary key, name varchar, age int, language text)";
}
