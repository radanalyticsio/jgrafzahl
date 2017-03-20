package io.radanalytics.jgrafzahl;

import java.io.*;
import java.util.*;
import org.apache.spark.sql.*;

class DataProvider {
    private SparkSession spark;

    DataProvider(SparkSession spark) {
        this.spark = spark;
    }

    public String getCategories(int num) {
        String[] categories = {"foo", "bar"};
        return stringJoin(Arrays.copyOfRange(categories, 0, num), "\",\"");
    }

    public String getData(int num) {
        String[] counts = {"10", "20"};
        return stringJoin(Arrays.copyOfRange(counts, 0, num), ",");
    }

    public List<Row> getTop(int num) {
        /* This does not work and needs some love, eventually we'll need to 
         * return the values and counts we are looking for. */
        String query = "SELECT * FROM results ORDER BY count DESC LIMIT " + Integer.toString(num);
        List<Row> results = this.spark.sql(query).collectAsList();
        return results;
    }

    private String stringJoin(String[] strings, String delimeter) {
        String cat = "";
        for (String s: strings) {
            if (cat == "") {
                cat = s;
            } else {
                cat = cat + delimeter + s;
            }
        }
        return cat;
    }
}
