package io.radanalytics.jgrafzahl;

import static spark.Spark.*;
import org.apache.spark.sql.*;

class AppServer {
    AppServer(String[] args) {
        CommandLineArgs cmdline = new CommandLineArgs(args);
        SparkSession spark = new SparkSession.Builder().appName("jgrafzahl").getOrCreate();
        spark.readStream()
               .format("kafka")
                 .option("kafka.bootstrap.servers", cmdline.getServers())
                   .option("subscribe", cmdline.getTopic())
                     .load()
             .selectExpr("CAST(value AS STRING)")
               .groupBy("value")
                 .count()
             .writeStream()
               .outputMode("complete")
                 .format("memory")
                   .queryName("results")
             .start();
            
        DataProvider provider = new DataProvider(spark);
        get("/", new IndexHandler(provider));
        get("/data", new DataHandler(provider));
    }
}
