package com.android.koopa;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UnloginedPostPage extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlogined_post_page);
        Fragment fra=new FragPoster();
        getSupportFragmentManager().beginTransaction().replace(R.id.unloginedcontiner,fra).commit();
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.searchb) ;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent getin=getIntent();
//                int flag=getin.getIntExtra("flag",0);

                Intent in=new Intent(getApplicationContext(),ActivitySearchScreen.class);
//                in.putExtra("int",flag);
                startActivity(in);
            }
        });
        }

    @Override
    public void onBackPressed() {
        Intent in=new Intent(getApplicationContext(),Splashscreen.class);
        startActivity(in);
        super.onBackPressed();
    }
}
