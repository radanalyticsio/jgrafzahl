package io.radanalytics.jgrafzahl;

class DataProvider {
    public String getCategories() {
        String[] categories = {"foo", "bar"};
        return stringJoin(categories, "\',\'");
    }

    public String getData() {
        String[] counts = {"10", "20"};
        return stringJoin(counts, ",");
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
