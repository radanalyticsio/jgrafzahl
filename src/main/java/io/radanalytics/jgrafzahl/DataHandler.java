package io.radanalytics.jgrafzahl;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import org.apache.spark.sql.*;

class DataHandler implements Route {
    private DataProvider provider;
    private String template = "{\"categories\": [\"{{ categories }}\"],\"data\": [[\"counts\", {{ data }} ]]}";

    DataHandler(DataProvider provider) {
        this.provider = provider;
    }

    public Object handle(Request req, Response res) throws Exception {
        res.type("application/json");
        String numparam = req.queryParams("n");
        int num = 1;
        System.out.println("query params = " + numparam);
        if (numparam != null) {
            num = Integer.parseInt(numparam);
        }

        /* this does not work, was trying to dump out the raw lines to see
         * the format for the Row objects.
        String ret = "";
        for (Row r: this.provider.getTop(num)) {
            ret = ret + r.mkString("::") + "\n";
        }
        return ret;
        */
        return this.template
            .replace("{{ categories }}", this.provider.getCategories(num))
            .replace("{{ data }}", this.provider.getData(num));
    }
}
