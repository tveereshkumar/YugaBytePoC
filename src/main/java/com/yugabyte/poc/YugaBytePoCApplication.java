package com.yugabyte.poc;

import com.yugabyte.poc.threads.InsertThread;
import com.yugabyte.poc.threads.SimpleReadThread;
import com.yugabyte.poc.util.GlobalConstant;
import com.yugabyte.poc.util.YugaByteDBConfig;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class YugaBytePoCApplication {
    private static Logger logger = Logger.getLogger(YugaBytePoCApplication.class);

    public static void main(String[] args) {
        try {
            //init
            init();

            //Create Table
            createTable();

            //Insert Data into table
            doInsert();

            //Perform Read Requests
            doRead();

        } catch (Exception e) {
            logger.error("Error inside the main function : ", e);
        }
    }

    private static void init() {
        try {
            //Initialize Logger
            BasicConfigurator.configure();
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            logger.error("Error inside the init function : ", e);
        }
    }

    private static void createTable() throws SQLException {
        //Set Properties for database user name & password
        Properties props = new Properties();
        props.setProperty("user", YugaByteDBConfig.DB_USER_NAME);
        props.setProperty("password", YugaByteDBConfig.DB_PASSWORD);
        try {
            Connection connection = DriverManager.getConnection(YugaByteDBConfig.JDBC_URL_LIST[0] + YugaByteDBConfig.DATABASE_NAME, props);
            Statement statement = connection.createStatement();
            statement.execute("============= DROP TABLE IF EXISTS ============");
            statement.execute(YugaByteDBConfig.USER_TABLE_QUERY);
        } catch (Exception e) {

        }
    }

    //Executes Insert into table
    private static void doInsert() {
        for (int i = 0; i < GlobalConstant.NUM_THREADS; i++) {
            Thread object = new Thread(new InsertThread());
            object.start();
        }
    }

    private static void doRead(){
        Thread thread = new Thread(new SimpleReadThread());
        thread.start();
    }
}
