package io.radanalytics.jgrafzahl;

import java.io.*;
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
        get("/", new IndexHandler());
    }

    private class IndexHandler implements Route {
        private String template = "";

        IndexHandler() {
            InputStream in = getClass().getResourceAsStream("/index.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            try {
                while((line = reader.readLine()) != null) {
                    this.template = this.template + "\n" + line;
                }
                this.template = this.template + "\n";
                reader.close();
            }
            catch (IOException ex) {
                System.err.println("Unexpected error: " + ex.getMessage());
                this.template = "";
            }
        }

        public Object handle(Request req, Response res) throws Exception {
            return this.template;
        }
    }
}
