package com.android.koopa;

public class District {
    private String district;
    private String id;

    public District(String district, String id) {
        this.district = district;
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
