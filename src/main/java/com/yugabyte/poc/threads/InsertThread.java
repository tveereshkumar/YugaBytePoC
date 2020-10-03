package com.yugabyte.poc.threads;

import com.yugabyte.poc.util.GlobalConstant;
import com.yugabyte.poc.util.YugaByteDBConfig;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class InsertThread implements Runnable {
    private static Logger logger = Logger.getLogger(InsertThread.class);

    public InsertThread() {

    }

    @Override
    public void run() {
        try {
            Class.forName("org.postgresql.Driver");
            Random ip = new Random();
            String ipHost = YugaByteDBConfig.JDBC_URL_LIST[Math.abs(ip.nextInt(3))]+YugaByteDBConfig.DATABASE_NAME;
            logger.info("ipHose : "+ipHost);
            Connection conn = DriverManager.getConnection(ipHost,
                                                          YugaByteDBConfig.DB_USER_NAME,
                                                          YugaByteDBConfig.DB_PASSWORD);
            logger.info("Connected to the PostgreSQL server successfully.");

            int count = GlobalConstant.INSERT_RECORDS_COUNT / GlobalConstant.NUM_THREADS;
            Random rand = new Random(100000);
            Random age = new Random(100);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i <= count; i++) {
                PreparedStatement statement = conn.prepareStatement("INSERT INTO user_master (name, age, language) VALUES (?, ?, ?)");
                statement.setString(1, "Test User "+Math.abs(rand.nextInt()));
                statement.setInt(2, age.nextInt());
                statement.setString(3, "English "+Math.abs(rand.nextInt()));
                statement.execute();
            }

            //Displaying the Time Taken for the Complete
            long endTime = System.currentTimeMillis();
            logger.info("======================= Table inserts Completed =========================");
            logger.info("Total Time : "+(endTime - startTime));
        } catch (Exception e) {
            logger.error("Error in onRun of Insert Thread", e);
        }
    }
}
