package io.radanalytics.jgrafzahl;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;
import com.hubspot.jinjava.Jinjava;

class AppServer {
    private CommandLineArgs args;

    AppServer(String[] args) {
        this.args = new CommandLineArgs(args);
        get("/", new IndexHandler());
    }

    private String stringJoin(String[] strings, String delimeter) {
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
            String[] categories = {"foo", "bar"};
            String[] counts = {"10", "20"};
            return this.template
                .replace("{{ categories }}", stringJoin(categories, "\',\'"))
                .replace("{{ data }}", stringJoin(counts, ","));
        }
    }
}
