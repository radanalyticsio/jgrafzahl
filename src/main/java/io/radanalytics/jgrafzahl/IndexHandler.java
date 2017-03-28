package io.radanalytics.jgrafzahl;

import java.io.*;
import java.util.List;
import spark.Request;
import spark.Response;
import spark.Route;

class IndexHandler implements Route {
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
        int count = 10;
        List<Object> top = this.provider.getTop(count);
        return this.template
            .replace("{{ categories }}", this.provider.getCategoriesFrom(top))
            .replace("{{ data }}", this.provider.getDataFrom(top));
    }
}
