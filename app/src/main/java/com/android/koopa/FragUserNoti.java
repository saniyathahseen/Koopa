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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragUserNoti extends Fragment implements View.OnClickListener{ View view;
    DatabaseReference mydbN,myref;
    FirebaseDatabase myfb;
    private RecyclerView recyclerView1;

    private NotificationAdapter2 adapter;
    private List<Bnotification> notifications;
    TextView bworker,bdate,bsite,bstatus;

    String name;


    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.postcontentmain,container,false);
        recyclerView1 = (RecyclerView)view.findViewById(R.id.rc2);
        notifications = new ArrayList<>();
        adapter = new NotificationAdapter2(getContext(), notifications);

        RecyclerView.LayoutManager mLayouManager = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(mLayouManager);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(adapter);
        bworker=(TextView) view.findViewById(R.id.tv1name);
        bdate=(TextView) view.findViewById(R.id.tv1date);
        bsite=(TextView) view.findViewById(R.id.tv1name);
        bstatus=(TextView)view.findViewById(R.id.tv1status);


//        baccept.setOnClickListener(this);
//        bregect.setOnClickListener(this);


        myfb = FirebaseDatabase.getInstance();
        SharedPreferences sp=getContext().getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        final String uid=sp.getString("Uid","");
        mydbN = myfb.getReference("Notification").child(uid);
        mydbN.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot booking: dataSnapshot.getChildren()){
                    Bnotification noti1 = booking.getValue(Bnotification.class);
                    Log.i("Tag", noti1.getBookedworker() + " " +noti1.getWorksSite());
                    notifications.add(noti1);
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
