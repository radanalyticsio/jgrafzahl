package io.radanalytics.jgrafzahl;

import java.util.Arrays;

class DataProvider {
    DataProvider(CommandLineArgs args) {
    }

    public String getCategories(int num) {
        String[] categories = {"foo", "bar"};
        return stringJoin(Arrays.copyOfRange(categories, 0, num), "\",\"");
    }

    public String getData(int num) {
        String[] counts = {"10", "20"};
        return stringJoin(Arrays.copyOfRange(counts, 0, num), ",");
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
}
