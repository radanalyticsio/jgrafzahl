package io.radanalytics.jgrafzahl;

import spark.Request;
import spark.Response;
import spark.Route;

class DataHandler implements Route {
    private DataProvider provider;

    DataHandler(DataProvider provider) {
        this.provider = provider;
    }

    public Object handle(Request req, Response res) throws Exception {
        res.type("application/json");
        String numparam = req.queryParams("n");
        int num = 10;
        if (numparam != null) {
            num = Integer.parseInt(numparam);
        }

        String ret = this.provider.getTopAsJson(num);
        return ret;
    }
}
