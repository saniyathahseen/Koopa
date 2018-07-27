package com.android.koopa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Splashscreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        SharedPreferences sp=getApplicationContext().getSharedPreferences("profileView", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("Uid");
        editor.remove("Name");
        editor.remove("Category");
        editor.remove("Subdistrict");
        editor.commit();
        FirebaseAuth.getInstance().signOut();
        SharedPreferences sp1=getApplicationContext().getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1=sp1.edit();
        editor1.remove("Uid");
        editor1.remove("Email");
        editor1.remove("Password");
        editor1.remove("Name");
        editor1.remove("Category");
        editor1.remove("Type");
        editor1.remove("Workno");
        editor1.remove("District");
        editor1.remove("Subdistrict");
        editor1.remove("ImageUrl");
        editor1.commit();
        TextView txt=(TextView)findViewById(R.id.skip);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int flag=1;
                Intent in= new Intent(getApplicationContext(),UnloginedPostPage.class);
//                in.putExtra("flag",flag);
                startActivity(in);
            }
        });
        Button btn=(Button)findViewById(R.id.login_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(getApplicationContext(),LoginPage.class);
                startActivity(in);
            }
        });
    }
}
