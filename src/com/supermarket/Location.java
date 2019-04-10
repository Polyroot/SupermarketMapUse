package com.supermarket;

public class Location {

    private String locate;
    private String coordinate;
    private String locateDesc;

    public Location(String locate, String coordinate, String locateDesc) {
        this.locate = locate;
        this.coordinate = coordinate;
        this.locateDesc = locateDesc;
    }

    public String getLocate() {
        return locate;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public String getLocateDesc() {
        return locateDesc;
    }

}
