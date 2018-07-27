package com.android.koopa;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class

FragPoster extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private PostResultAdapter adapter;
    private List<Postlist> postlists;

    private FirebaseAuth mAuth;
    DatabaseReference mydb,myref;
    FirebaseDatabase myfb;

    String name;

    RecyclerView postresultlist;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.postcontentmain,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rc2);
        postlists = new ArrayList<>();
        adapter = new PostResultAdapter(getContext(), postlists);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        SharedPreferences sp=getContext().getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        final String name=sp.getString("Name","");

        myfb = FirebaseDatabase.getInstance();
        mydb = myfb.getReference("Posts");

        mydb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                myref=mydb.child(dataSnapshot.getKey());

                myref.addChildEventListener(new ChildEventListener() {
                    String Uid=myref.getKey();
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot1, String s) {
                        Postlist value=dataSnapshot1.getValue(Postlist.class);
                        prepareAlbums(Uid,value.description,value.date);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot1, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot1) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot1, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError1) {

                    }
                });
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


        return view;

    }
    private void prepareAlbums(final String Uid,final String description,final String date) {
        mydb = myfb.getReference("users");
        mydb.addChildEventListener(new ChildEventListener() {
                                       @Override
                                       public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                           user value=dataSnapshot.getValue(user.class);
                                           if(dataSnapshot.getKey().equalsIgnoreCase(Uid)){
                                               Postlist bh = new Postlist(Uid,description, value.getName(), date);
                                               postlists.add(bh);
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


    @Override
    public void onClick(View v) {

    }
}
