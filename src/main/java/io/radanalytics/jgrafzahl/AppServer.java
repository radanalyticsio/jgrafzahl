package io.radanalytics.jgrafzahl;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

class AppServer {
    private CommandLineArgs args;
    private DataProvider provider;

    AppServer(String[] args) {
        this.args = new CommandLineArgs(args);
        this.provider = new DataProvider(); 
        get("/", new IndexHandler(provider));
    }

    private class IndexHandler implements Route {
        private String template = "";
        private DataProvider provider;

        IndexHandler(DataProvider provider) {
            this.provider = provider;
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
            return this.template
                .replace("{{ categories }}", this.provider.getCategories())
                .replace("{{ data }}", this.provider.getData());
        }
    }
}
