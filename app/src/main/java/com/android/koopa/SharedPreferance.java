package com.android.koopa;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;

public class SharedPreferance {
    Context context;

    SharedPreferance(Context context) {
        this.context = context;
    }
    public void saveLoginDetails(String uid,String email, String password,String type, String category, String district, String name, String workno,String subdistrict,String imgurl) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Uid",uid);
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.putString("Type",type);
        editor.putString("Category",category);
        editor.putString("District",district);
        editor.putString("Name",name);
        editor.putString("Workno",workno);
        editor.putString("Subdistrict",subdistrict);
        editor.putString("ImageUrl",imgurl);
        editor.commit();
    }
    public  void getUid(String uid){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Uid", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Uid",uid);
        editor.commit();

    }
    public  void profileView(String uid,String name,String category,String subdistrict){
        SharedPreferences sharedPreferences = context.getSharedPreferences("profileView", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Uid",uid);
        editor.putString("Name",name);
        editor.putString("Category",category);
        editor.putString("Subdistrict",subdistrict);
        editor.commit();

    }
    public boolean isUserLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Email", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
        return isEmailEmpty || isPasswordEmpty;
    }
    public String getEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }
    public String getPassword() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Password", "");
    }
    public String geType() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Type", "");
    }
    public String getCategory() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Category", "");
    }
    public String getDistrict() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("District", "");
    }
    public String getName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Name", "");
    }
    public String getWorkno() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Workno", "");
    }
}
