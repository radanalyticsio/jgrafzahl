package io.radanalytics.jgrafzahl;

class CommandLineArgs {
    private String servers;
    private String topic;

    CommandLineArgs(String[] args) {
        if (args.length != 2) {
            System.err.println("Unexpected number of arguments, using defaults.");
            this.servers = "localhost:9092";
            this.topic = "word-fountain";
        }
        else {
            this.servers = args[0];
            this.topic = args[1];
        }
    }

    public String getServers() {
        return this.servers;
    }

    public String getTopic() {
        return this.topic;
    }
}
