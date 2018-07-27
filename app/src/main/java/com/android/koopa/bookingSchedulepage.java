package com.android.koopa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class bookingSchedulepage extends BaseClass  implements  View.OnClickListener {
    EditText datee;
    EditText placeet;
    Button bconform, bcancel;
    String site,date;
    private FirebaseAuth mAuth;
    DatabaseReference mydb,mydbN;
    FirebaseDatabase myfb;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_schedulepage);
        datee = (EditText) findViewById(R.id.dateet);
        placeet = (EditText) findViewById(R.id.placeet);

        progressDialog=new ProgressDialog(this);

        bcancel=(Button)findViewById(R.id.bcancel);

        bconform = (Button) findViewById(R.id.bconform);
        date=datee.getText().toString();
        site=placeet.getText().toString();

        bcancel.setOnClickListener(this);
        bconform.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();







    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bconform){
            Toast.makeText(this,"SCHEDULED YOUR WORK",Toast.LENGTH_SHORT).show();
            addBookings(date,site);
            Intent in = new Intent(bookingSchedulepage.this, HomeUser1.class);
            startActivity(in);


        }
        else if(v.getId()==R.id.bcancel){
            Intent in = new Intent(bookingSchedulepage.this, HomeUser1.class);
            startActivity(in);


        }
    }

    

    private void addBookings(String date,String site) {
//
             myfb = FirebaseDatabase.getInstance();
             mydb = myfb.getReference("Work_Bookings");
             mydbN = myfb.getReference("Notification");


          SharedPreferences sp=this.getSharedPreferences("profileView", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor=sp.edit();
          String workerid=sp.getString("Uid", "");
//adding notification details
        SharedPreferences sp2=this.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2=sp2.edit();
        String userid=sp2.getString("Uid", "");
        String name=sp2.getString("Name", "");



        DatabaseReference uidw= mydbN.child(userid).push();

        uidw.child("workDate").setValue(datee.getText().toString());
        uidw.child("worksSite").setValue(placeet.getText().toString());
        uidw.child("bookedDate").setValue(new Date ().toString());
        uidw.child("status").setValue("");
        uidw.child("bookedworker").setValue(workerid);
        uidw.getKey();


        DatabaseReference uid = mydb.child(workerid).push();
//        Booking booking = new Booking(site,mAuth.getCurrentUser().getUid(),new Date().toString(),date);

        uid.child("worksSite").setValue(placeet.getText().toString());
        uid.child("workDate").setValue(datee.getText().toString());
        uid.child("bookedUser").setValue(name);
        uid.child("bookedUserId").setValue(userid);
        uid.child("bookedDate").setValue(new Date().toString());
        progressDialog.setMessage("Adding booking details...");
        progressDialog.show();
        progressDialog.dismiss();
        }


    private void retriveData (String uid) {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("bookings").child(uid);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


//                tvLoginMobile.setText(employee.getMobile());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }



        };
    }


    

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}






