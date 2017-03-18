package io.radanalytics.jgrafzahl;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

class AppServer {
    private CommandLineArgs args;

    AppServer(String[] args) {
        this.args = new CommandLineArgs(args);
    }

    public void run() {
        get("/", new IndexHandler(this.args));
    }

    private class IndexHandler implements Route {
        private CommandLineArgs args;

        IndexHandler(CommandLineArgs args) {
            this.args = args;
        }

        public Object handle(Request req, Response res) throws Exception {
            return "servers: " + this.args.getServers() + "\ntopic: " + this.args.getTopic();
        }
    }
}
