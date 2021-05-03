package com.gmyf.smt.factory.model;

import java.util.List;

public class Playlist {
    private String name;
    private List<String> tracksNames;

    public Playlist(String name, List<String> tracksNames) {
        this.name = name;
        this.tracksNames = tracksNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTracksNames() {
        return tracksNames;
    }

    public void setTracksNames(List<String> tracksNames) {
        this.tracksNames = tracksNames;
    }
}
