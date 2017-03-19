package io.radanalytics.jgrafzahl;

import static spark.Spark.*;

class AppServer {
    AppServer(String[] args) {
        DataProvider provider = new DataProvider(new CommandLineArgs(args));
        get("/", new IndexHandler(provider));
        get("/data", new DataHandler(provider));
    }
}
