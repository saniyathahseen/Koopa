package com.android.koopa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MobileVarification extends BaseClass   {

EditText mobNum,otpVar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_varification);

        String [] data = getIntent().getExtras().getStringArray("Layout");





        Log.e("MobileVarification",""+data[0]);
        Log.e("MobileVarification",""+data[1]);
        Log.e("MobileVarification",""+data[2]);
        Log.e("MobileVarification",""+data[3]);
        Log.e("MobileVarification",""+data[4]);

    }




        }





