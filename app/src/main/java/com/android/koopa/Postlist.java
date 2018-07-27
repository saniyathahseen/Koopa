package com.android.koopa;

public class Postlist {

    public String date;
    public String description;
    public String name;
    public String id;
    public String category;


    public Postlist() {
        // Default constructor required for calls to DataSnapshot.getValue(Postlist.class)
    }
    public Postlist(String id,String description,String name, String date){
        this.description = description;
        this.date = date;
        this.name = name;
        this.id = id;


    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
