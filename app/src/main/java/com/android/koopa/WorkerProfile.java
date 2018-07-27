package com.android.koopa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class WorkerProfile extends BaseClass implements  View.OnClickListener{
Button book,actb;
    FirebaseDatabase database;
DatabaseReference ref;
TextView unameText,uworkText,udis,uplace;
RatingBar  urating;
ImageView img;

public String id;
 int flag;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("users");



//        Intent intent = getIntent();
//        String id =intent.getStringExtra("Uid");
//        Toast.makeText(getApplicationContext(), "got id", Toast.LENGTH_LONG).show();
//
//        Userdata userdata = new Userdata("badhusha","painting","address","tirur");
//
//        intent.putExtra("userData",userdata);
//
//        getIntent().getExtras();

//        Uid=mAuth.getCurrentUser().toString();

//        if(currentUser.getUid()==null){
//            Toast.makeText(this, "You must login for booking purpose", Toast.LENGTH_SHORT).show();
//            Intent logintent = new Intent(WorkerProfile.this, LoginPage.class);
//            startActivity(logintent);
//        }
        img=findViewById(R.id.profileImage);
        book = (Button) findViewById(R.id.bookb);
        book.setOnClickListener(this);
        actb = (Button) findViewById(R.id.actb);
        actb.setOnClickListener(this);
        unameText=(TextView)findViewById(R.id.uname);
        uworkText=(TextView)findViewById(R.id.uwork);
        udis=(TextView)findViewById(R.id.udis);
        uplace=(TextView)findViewById(R.id.uplace);
//        urating=(RatingBar)findViewById(R.id.urating);
        SharedPreferences sp=getApplicationContext().getSharedPreferences("profileView", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        String name=sp.getString("Name","");
        String category=sp.getString("Category","");
        String placee=sp.getString("Subdistrict","");
        String uid=sp.getString("Uid","");
//        String  discription=sp.getString("discription","");

        Glide.with(getApplicationContext())
                .load("https://firebasestorage.googleapis.com/v0/b/koopa-87729.appspot.com/o/images%2F"+uid+"?alt=media&token=20cd5be4-2b3e-4d46-91d5-89cf8da44f89")
                .into(img);
         unameText.setText(name);
         uworkText.setText(category);
         uplace.setText(placee);
         udis.setText("He is a well known "+category+" From "+placee+" To schedule work please click booknow button");


// Read from the database
            ref.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
//                    Userdata value = dataSnapshot.getValue(Userdata.class);
//                    Userdata userdata = new Userdata("badhusha", "painting", "address", "tirur");
//                    unameText.setText(value.getUsername());
//                    uworkText.setText(value.getUwork());
//                    udis.setText(value.getUdis());
//                    uplace.setText(value.getPlace());
//                urating.setRating(userdata.getUrating());


                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }
            });
        }
//        ref.addValueEventListener(PostListner);

//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                Userdata value= dataSnapshot.getValue(Userdata.class);
//
//
//             //   unameText.setText(""+value.getUsername()+""+value.getUrating()+""+value.getUwork()+""+value.getUdis());
//
//
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });





    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bookb ){

                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();


                    // Check if user is signed in (non-null) and update UI accordingly.
                    FirebaseUser currentUser = mAuth.getCurrentUser();
//            SharedPreferences sp2=this.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor2=sp2.edit();
//            String userid=sp2.getString("Uid", "");

                   if(currentUser==null) {

                       Intent bookin = new Intent(this, LoginPage.class);
                       startActivity(bookin);
                   }
                   else {
                       Intent bookin = new Intent(this, bookingSchedulepage.class);
                       startActivity(bookin);
                   }




        }


    }
    public void onBackPressed() {
        SharedPreferences sp=getApplicationContext().getSharedPreferences("profileView", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("Uid");
        editor.remove("Name");
        editor.remove("Category");
        editor.remove("Subdistrict");
        editor.commit();
        super.onBackPressed();
    }
}
