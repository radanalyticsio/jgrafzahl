package io.radanalytics.jgrafzahl;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class AppServer {
    private String[] args;

    AppServer(String[] args) {
        this.args = args;
    }

    public void run() {
        get("/", new IndexHandler());
    }

    private class IndexHandler implements Route {
        public Object handle(Request req, Response res) throws Exception {
            return "Hello";
        }
    }
}
