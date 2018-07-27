package com.android.koopa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  LoginPage extends BaseClass implements View.OnClickListener,View.OnFocusChangeListener{
    Button signin;
    EditText uname,password;

    private static  final String unamepattern="^.*(?=.{6,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
    private static  final String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    Matcher matcher;
    Pattern pname,pemail;

    DatabaseReference myDb;
    FirebaseDatabase db;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    String email,passtype,category,district,name,workno,pass;

    DatabaseReference mydb;
    FirebaseDatabase myfb;
    private FirebaseAuth mAuth;
    private StorageReference storage;

    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        FirebaseAuth.getInstance().signOut();

        pname=Pattern.compile(unamepattern);
        pemail=Pattern.compile(emailpattern);

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

        signin=(Button)findViewById(R.id.signin);
        uname=(EditText)findViewById(R.id.editText2);
        password=(EditText)findViewById(R.id.editText);

        uname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!validateemail(uname.getText().toString())){
                        uname.setError("Invaild email");
                    }

                }

            }
        });
            password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        if(!validatepassword(password.getText().toString())){
                            password.setError("Invaild password");
                        }

                    }
                }
            });
        TextView sign=(TextView)findViewById(R.id.textView5);

        sign.setOnClickListener(this);
        signin.setOnClickListener(this);

        progressDialog=new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance().getReference();

    }
    @Override
    public void onClick(View view) {

        if(view==signin)
        {
            userLogin();
        }
        if (view.getId()==R.id.textView5){
            Intent i=new Intent(getApplicationContext(),Registration.class);
            startActivity(i);
        }
    }
    private  void userLogin()
    {
        email=uname.getText().toString();
        pass=password.getText().toString();
        if(TextUtils.isEmpty(email)&&!validateemail(email)){
               uname.setError("please fill");
        }
        if(TextUtils.isEmpty(pass)&&!validatepassword(pass)){
          password.setError("please fill");
            return;
        }
        progressDialog.setMessage("Login ...");
       progressDialog.show();
       firebaseAuth.signInWithEmailAndPassword(email,pass)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       progressDialog.dismiss();
                       if(task.isSuccessful())
                       {
                           myfb = FirebaseDatabase.getInstance();
                           mydb = myfb.getReference("users");

                           FirebaseUser currentUser = mAuth.getCurrentUser();

                           final DatabaseReference userId=mydb.child(currentUser.getUid());
                           final String uid=userId.getKey();
                            mydb.child(uid).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    user value= dataSnapshot.getValue(user.class);

                                    saveLoginDetails(uid,email, pass, value.getType(), value.getCategory(), value.getDistrict(), value.getName(), value.getWorkno(),value.getSubdistrict(),("https://firebasestorage.googleapis.com/v0/b/koopa-87729.appspot.com/o/images%2F"+uid+"?alt=media&token=20cd5be4-2b3e-4d46-91d5-89cf8da44f89"));
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            Intent i=new Intent(getApplicationContext(),HomeUser1.class);
                            startActivity(i);
                       }
                       else
                           Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                   }
               });
    }
    private void saveLoginDetails(String uid,String email, String pass,String type,String category,String district,String name,String workno,String subdistrict,String imgurl) {
        new SharedPreferance(this).saveLoginDetails(uid,email, pass,type,category,district,name,workno,subdistrict,imgurl);
    }
    public void onBackPressed() {
        Intent in=new Intent(getApplicationContext(),Splashscreen.class);
        startActivity(in);
        super.onBackPressed();
    }
    public boolean validateemail(String email)
    {

        matcher=pemail.matcher(email);

        return matcher.matches();
    }
    public boolean validatepassword(String email)
    {

        matcher=pname.matcher(email);

        return matcher.matches();
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}
