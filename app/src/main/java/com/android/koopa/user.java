package com.android.koopa;

import android.widget.ScrollView;

public class user {
    private String id;
    private String Name;
    private String Type;
    private String Category;
    private String District;
    private String Subdistrict;
    private String Workno;
    private String pass;
    public user() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public user(String id,String Name,String Type,String Category,String District,String Workno,String pass,String Subdistrict) {
        this.id=id;
        this.Name = Name;
        this.Type = Type;
        this.Category = Category;
        this.District = District;
        this.Subdistrict=Subdistrict;
        this.Workno = Workno;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getSubdistrict() {
        return Subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        Subdistrict = subdistrict;
    }

    public String getWorkno() {
        return Workno;
    }

    public void setWorkno(String workno) {
        Workno = workno;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
