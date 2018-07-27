package com.android.koopa;

public class Subdist {
    String Subdist;
    String id;

    public Subdist(String subdist, String id) {
        Subdist = subdist;
        this.id = id;
    }

    public String getSubdist() {
        return Subdist;
    }

    public void setSubdist(String subdist) {
        Subdist = subdist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
