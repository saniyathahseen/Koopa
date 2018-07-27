package com.android.koopa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BaseClass extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference mydb;
    FirebaseDatabase myfb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_class);
    }
}
