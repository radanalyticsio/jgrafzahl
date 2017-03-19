package io.radanalytics.jgrafzahl;

import static spark.Spark.*;

class AppServer {
    AppServer(String[] args) {
        DataProvider provider = new DataProvider(); 
        get("/", new IndexHandler(provider));
    }
}
