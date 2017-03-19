package io.radanalytics.jgrafzahl;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

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
        if (numparam != "") {
            num = Integer.parseInt(numparam);
        }

        return this.template
            .replace("{{ categories }}", this.provider.getCategories(num))
            .replace("{{ data }}", this.provider.getData(num));
    }
}
