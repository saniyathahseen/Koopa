package com.android.koopa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragNotification extends Fragment implements View.OnClickListener{
    View view;
    DatabaseReference mydbN,mydb;
    FirebaseDatabase myfb,myFb;
    private RecyclerView recyclerView1;
    private NotificationAdapter adapter;
    private List<Booking> bookings;
    TextView buser,bdate,bsite;
    Button baccept;
    Button bregect;
    String name;


    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.postcontentmain,container,false);
        recyclerView1 = (RecyclerView)view.findViewById(R.id.rc2);
        bookings = new ArrayList<>();
        adapter = new NotificationAdapter(getContext(), bookings);

        RecyclerView.LayoutManager mLayouManager = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(mLayouManager);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(adapter);
        buser=(TextView) view.findViewById(R.id.tvsite);
        bdate=(TextView) view.findViewById(R.id.tvdate);
        bsite=(TextView) view.findViewById(R.id.tvname);
        baccept=(Button) view.findViewById(R.id.baccept);
        bregect=(Button) view.findViewById(R.id.bregect);


        myfb = FirebaseDatabase.getInstance();
        SharedPreferences sp=getContext().getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        final String uid=sp.getString("Uid","");

        mydb = myfb.getReference("Work_Bookings").child(uid);
        mydb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot booking: dataSnapshot.getChildren()){
                    Booking booking1 = booking.getValue(Booking.class);
                    Log.i("Tag", booking1.getBookedUser() + " " +booking1.getWorksSite());
                    bookings.add(booking1);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
            return view;
        }
        @Override
    public void onClick(View v) {


    }

}
