package io.radanalytics.jgrafzahl;

import java.io.*;
import java.util.*;
import javax.json.*;
import org.apache.spark.sql.*;

class DataProvider {
    private SparkSession spark;

    DataProvider(SparkSession spark) {
        this.spark = spark;
    }

    public static String getCategoriesFrom(List<Object> top) {
        ArrayList<String> cats = new ArrayList<String>();
        for (String c: (List<String>)top.get(0)) {
            cats.add(c);
        }
        return stringJoin(cats.toArray(new String[cats.size()]), "\",\"");
    }

    public static String getDataFrom(List<Object> top) {
        List<String> data = new ArrayList<String>();
        for (Long d: (List<Long>)top.get(1)) {
            data.add(d.toString());
        }
        return stringJoin(data.toArray(new String[data.size()]), ",");
    }

    public List<Object> getTop(int num) {
        String query = "SELECT * FROM results ORDER BY count DESC LIMIT " + Integer.toString(num);
        List<Row> results = this.spark.sql(query).collectAsList();
        List<String> categories = new ArrayList<String>();
        List<Long> counts = new ArrayList<Long>();
        for (Row r: results) {
            categories.add(r.getString(0));
            counts.add(r.getLong(1));
        }

        List<Object> ret = new ArrayList<Object>();
        ret.add(categories);
        ret.add(counts);
        return ret;
    }

    public String getTopAsJson(int num) {
        List<Object> top = this.getTop(num);
        JsonArrayBuilder categories = Json.createArrayBuilder();
        for (String c: (List<String>)top.get(0)) {
            categories.add(c);
        }
        JsonArrayBuilder data = Json.createArrayBuilder();
        data.add("counts");
        for (Long d: (List<Long>)top.get(1)) {
            data.add(d);
        }

        JsonObject model = Json.createObjectBuilder()
            .add("categories", categories)
            .add("data", Json.createArrayBuilder()
                    .add(data))
            .build();

        StringWriter stWriter = new StringWriter();
        JsonWriter jsWriter = Json.createWriter(stWriter);
        jsWriter.writeObject(model);
        jsWriter.close();
        String ret = stWriter.toString();
        return ret;
    }

    private static String stringJoin(String[] strings, String delimeter) {
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
