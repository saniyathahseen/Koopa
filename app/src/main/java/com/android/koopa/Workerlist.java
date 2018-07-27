package com.android.koopa;

public class Workerlist {
    private String Name;
    private String Category;
    private String imgurl;
    private String uid;
    private  String Subdistrict;
    public Workerlist() {
    }
    public Workerlist(String uid,String name,String  category, String  imgurl,String subdistrict) {
        this.uid = uid;
        this.Name = name;
        this.Category = category;
        this.imgurl=imgurl;
        this.Subdistrict=subdistrict;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSubdistrict() {
        return Subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        Subdistrict = subdistrict;
    }
}
