package io.radanalytics.jgrafzahl;

public class App {
    public static void main(String[] args) {
        AppServer server = new AppServer(args);
        server.run();
    }
}
