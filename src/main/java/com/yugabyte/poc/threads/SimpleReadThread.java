package com.yugabyte.poc.threads;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleReadThread implements Runnable {
    private static Logger logger = Logger.getLogger(SimpleReadThread.class);

    public SimpleReadThread() {

    }

    @Override
    public void run() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://10.128.0.42:5433/test", "yugabyte", "");
            logger.info("Connected to the PostgreSQL server successfully.");

            long startTime = System.currentTimeMillis();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM user_master");
            resultSet.next();
            int count = resultSet.getInt("rowcount");
            logger.info("======================= Total Number Of Record Found : "+count+" ======================");
            resultSet.close();

            //Displaying the Time Taken for the Complete
            long endTime = System.currentTimeMillis();
            logger.info("======================= Table inserts Completed =========================");
            logger.info("Total Time : "+(endTime - startTime));
        } catch (Exception e) {
            logger.error("Error in onRun of Insert Thread", e);
        }
    }
}
