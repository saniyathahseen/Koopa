package com.android.koopa;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivitySearchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen2);
       Fragment fra=new SearchScreen();
       getSupportFragmentManager().beginTransaction().replace(R.id.search_page_frag,fra).commit();


    }
}
