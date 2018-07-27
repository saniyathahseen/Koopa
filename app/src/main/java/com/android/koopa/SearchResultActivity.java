package com.android.koopa;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WorkerlistAdapter adapter;
    private List<Workerlist> workerlists;

    String check,category,districts,subdistrict;

    private FirebaseAuth mAuth;
    DatabaseReference mydb;
    FirebaseDatabase myfb;

    RecyclerView resultlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.stateId.toolbar);
//        setSupportActionBar(toolbar);


        // initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        workerlists = new ArrayList<>();
        adapter = new WorkerlistAdapter(this, workerlists);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        myfb = FirebaseDatabase.getInstance();
        mydb = myfb.getReference("users");
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        check=extras.getString("check");
        category=extras.getString("spinnercategory");
        districts=extras.getString("spinnerdistrict");
        subdistrict=extras.getString("spinnersubdistrict");


        mydb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                user value=dataSnapshot.getValue(user.class);
                if(value.getCategory().equals(category)&&value.getDistrict().equals(districts)){
                    FirebaseUser currentUser = mAuth.getCurrentUser();

                    final DatabaseReference userId=mydb.child(currentUser.getUid());

                    prepareAlbums(userId.toString(),category,districts,subdistrict,value.getName(),value.getType());
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private int dpToPx(int i) {
        Resources r = getResources();
        float dp = 5;
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    private void prepareAlbums(String uid,String category,String districts,String subdistrict ,String name,String type) {

        int[] covers = new int[]{
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile};


        Workerlist bh = new Workerlist(uid,name, category,  "https://firebasestorage.googleapis.com/v0/b/koopa-87729.appspot.com/o/images%2F"+uid+"?alt=media&token=20cd5be4-2b3e-4d46-91d5-89cf8da44f89",subdistrict);
        workerlists.add(bh);




        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent ion=new Intent(getApplicationContext(),HomeUser1.class);
        startActivity(ion);
        super.onBackPressed();
    }
}
